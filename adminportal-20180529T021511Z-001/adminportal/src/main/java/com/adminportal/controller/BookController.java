package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Book;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.domain.User;
import com.adminportal.service.BookService;
import com.adminportal.service.CartItemService;
import com.adminportal.service.OrderService;
import com.adminportal.service.UserService;
import com.adminportal.utility.MailConstructor;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{add}", method = RequestMethod.GET)
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}

	@RequestMapping(value = "/{saveBook}", method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {

		bookService.save(book);

		MultipartFile bookImage = book.getBookImage();

		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:bookList";
	}

	@RequestMapping("/bookInfo")
	public String bookInfo(@RequestParam("id") Long id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);

		return "bookInfo";
	}

	@RequestMapping("/updateBook")
	public String updateBook(@RequestParam("id") Long id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);

		return "updateBook";
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		
		bookService.save(book);

		MultipartFile bookImage = book.getBookImage();
		
		List<CartItem> cartItemList = cartItemService.findByBook(book);
		int bookQty = book.getInStockNumber();

		for (int i = 0; i < cartItemList.size(); i++) {

			CartItem cartItem = cartItemList.get(i);
			if (cartItem.getOrder() != null) {
				Order order = orderService.findOne(cartItem.getOrder().getId());
				if (order.getOutOfStock() > 0) {
					if (bookQty >= order.getOutOfStock()) {
						
						int newstock = bookQty - order.getOutOfStock();
						int newOrderStock = order.getOutOfStock();

						bookQty = newstock;
						User user = userService.findById(order.getUser().getId());
						BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice())
								.multiply(new BigDecimal(newOrderStock));
						bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal latestOrderTotal = order.getOrderTotal().add(bigDecimal).setScale(2,
								BigDecimal.ROUND_HALF_UP);
						Date oldOrderedDate = order.getOrderDate();
						int newQty = order.getStockOrdered() + newOrderStock;
						CartItem cartItm = new CartItem();
						cartItm = cartItem;
						cartItm.setQty(newOrderStock);
						cartItm.setSubtotal(bigDecimal);
						List<CartItem> cartItemList1 = new ArrayList<>();
						cartItemList1.add(cartItm);
						order.setCartItemList(cartItemList1);
						order.setOrderDate(Calendar.getInstance().getTime());
						order.setOrderTotal(bigDecimal);
						mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));

						order.setOrderTotal(latestOrderTotal);
						order.setOrderDate(oldOrderedDate);
						order.setStockOrdered(newQty);
						order.setOutOfStock(0);
						orderService.save(order);
						cartItem.setQty(newQty);
						cartItem.setSubtotal(latestOrderTotal);
						cartItemService.save(cartItem);
					} else if (bookQty < order.getOutOfStock()) {
						/*
						 * int newstock = order.getOutOfStock() - bookQty;
						 * order.setOutOfStock(newstock); bookQty=0; orderService.save(order);
						 */}
				}
			}
		}
		
		book.setInStockNumber(bookQty);
		bookService.save(book);

		if (!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";

				Files.delete(Paths.get("src/main/resources/static/image/book/" + name));

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "redirect:/book/bookInfo?id=" + book.getId();
	}

	@RequestMapping("/bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookService.findAll();
		for(Book book:bookList) {
			if(!(book.getInStockNumber()>0)) {
				book.setActive(false);
			}
		}
		model.addAttribute("bookList", bookList);
		return "bookList";

	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("id") String id, Model model) {
		bookService.removeOne(Long.parseLong(id.substring(8)));
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);

		return "redirect:/book/bookList";
	}

}
