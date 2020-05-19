package com.adminportal.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/orderList")
	public String orderList(Model model) {

		List<Order> orderList = orderService.findAll();
		for (int i = 0; i < orderList.size(); i++) {

			Order order = orderList.get(i);

			if (order.getCartItemList().size() > 0) {

				CartItem cartItemDtls = order.getCartItemList().get(0);

				order.setBookAuthorName(cartItemDtls.getBook().getAuthor());
				order.setBookCategory(cartItemDtls.getBook().getCategory());
				order.setBookTitle(cartItemDtls.getBook().getTitle());
				order.setStockOrdered(cartItemDtls.getQty());
				int stockNumber = 0;
				int inStock = 0;

				inStock = cartItemDtls.getQty() + cartItemDtls.getBook().getInStockNumber();
				stockNumber = cartItemDtls.getOutofStockOrdered();
				if (order.getInStock() > 0) {
					int inStockNum = 0;
					inStockNum = order.getInStock();
					inStock = inStockNum;
					stockNumber = order.getOutOfStock();
					order.setStockOrdered(Math.abs(cartItemDtls.getQty()));
				} else {
					order.setStockOrdered(Math.abs(cartItemDtls.getQty()));
				}

				order.setOutOfStock(stockNumber);
				order.setInStock(inStock);

			}

			order.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderService.save(order);
		}

		model.addAttribute("orderList", orderList);
		return "orderList";

	}

	@RequestMapping("/remove")
	public String remove(@ModelAttribute("id") String id, Model model) {
		orderService.removeOne(Long.parseLong(id));
		List<Order> orderList = orderService.findAll();
		model.addAttribute("orderList", orderList);

		return "redirect:/order/orderList";
	}

	@RequestMapping("/searchList")
	public String searchOrder(Model model) {

		return "searchOrder";
	}

	@RequestMapping("/searchOrders")
	public String searchBook(@ModelAttribute("startDate") String startDate, @ModelAttribute("endDate") String endDate,
			Principal principal, Model model) {
		List<Order> orderList = orderService.findAll();

		List<Order> ordersList = new ArrayList<>();

		model.addAttribute("orderList", orderList);
		for (Order order : orderList) {

			if (!order.isBackOrder()) {
				continue;
			}

			java.util.Date beforefmtDate = order.getOrderDate();

			String date = beforefmtDate.toString().substring(0, 10);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-dd-mm");
			try {
				java.util.Date sdate = sdf.parse(startDate);
				java.util.Date edate = sdf.parse(endDate);
				java.util.Date newDate = sdf1.parse(date);

				if ((sdate.before(newDate) && edate.after(newDate))
						|| (sdate.equals(newDate) && sdate.before(newDate))) {
					ordersList.add(order);
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}

		model.addAttribute("orderList", ordersList);

		return "searchOrder";
	}

}
