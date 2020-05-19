package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Book;
import com.adminportal.domain.CartItem;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.BookService;
import com.adminportal.service.CartItemService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CartItemService cartItemService;

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> findAll() {

		return (List<Book>) bookRepository.findAll();
	}

	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

	public void removeOne(Long id) {
		Book bookDtls = findOne(id);
		List<CartItem> cartItems = cartItemService.findByBook(bookDtls);
		if (cartItems.size() > 0) {

			cartItemService.removeCartItem(cartItems.get(0));
		}
		bookRepository.delete(id);
	}
}
