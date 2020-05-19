package com.adminportal.service.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Book;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.repository.OrderRepository;
import com.adminportal.service.BillingAddressService;
import com.adminportal.service.CartItemService;
import com.adminportal.service.OrderService;
import com.adminportal.service.PaymentService;
import com.adminportal.service.ShippingAddressService;
import com.adminportal.utility.MailConstructor;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private BillingAddressService billingAddressService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ShippingAddressService shippingAddressService;

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findAll() {
		return (List<Order>) orderRepository.findAll();
	}

	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}

	public void removeOne(Long id) {

		Order orderDtls = findOne(id);

		List<CartItem> cartItems = cartItemService.findByOrder(orderDtls);
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			Book book = cartItem.getBook();
			if (book != null) {
				book.setInStockNumber(book.getInStockNumber() + orderDtls.getStockOrdered());
			}
			cartItemService.removeCartItem(cartItemService.findById(cartItem.getId()));

		}

		billingAddressService.removeBillingAddress(orderDtls.getBillingAddress());
		shippingAddressService.removeShippingAddress(orderDtls.getShippingAddress());
		paymentService.removePaymentService(orderDtls.getPayment());
		orderRepository.delete(id);

		mailSender.send(mailConstructor.constructOrderConfirmationEmail(orderDtls.getUser(), orderDtls, Locale.ENGLISH,
				"", ""));

	}

}
