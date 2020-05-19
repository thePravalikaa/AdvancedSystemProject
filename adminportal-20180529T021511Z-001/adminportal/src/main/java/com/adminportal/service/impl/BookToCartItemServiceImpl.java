package com.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.repository.BookToCartItemRepository;
import com.adminportal.service.BookToCartItemService;

@Service
public class BookToCartItemServiceImpl implements BookToCartItemService{
	
	@Autowired
	private BookToCartItemRepository bookToCartItemRepository;

	@Override
	public void removeBookToCartItem(Long bookToCartItem) {
		bookToCartItemRepository.delete(bookToCartItem);
		
	}

}
