package com.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.service.BookService;
import com.adminportal.service.OrderService;

@Controller
public class ResourceController {

	@Autowired
	private BookService bookService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/book/removeList", method = RequestMethod.POST)
	public String removeList(@RequestBody ArrayList<String> bookIdList, Model model) {

		for (String id : bookIdList) {
			String bookId = id.substring(8);
			bookService.removeOne(Long.parseLong(bookId));
		}

		return "delete success";
	}

	@RequestMapping(value = "/order/removeList", method = RequestMethod.POST)
	public String removeOrderList(@RequestBody ArrayList<String> orderIdList, Model model) {

		for (String id : orderIdList) {
			orderService.removeOne(Long.parseLong(id.substring(8)));
		}

		return "Order(s) got deleted and confirmation mail has been sent to User";
	}
}
