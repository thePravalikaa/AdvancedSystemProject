package com.bookstore.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.BillingAddress;
import com.bookstore.domain.Book;
import com.bookstore.domain.CartItem;
import com.bookstore.domain.Order;
import com.bookstore.domain.Payment;
import com.bookstore.domain.ShippingAddress;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.CartItemService;
import com.bookstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartItemService cartItemService;

	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
			BillingAddress billingAddress, Payment payment, String shippingMethod, User user) {
		/*
		 * List<BillingAddress> billingAddress1=new ArrayList<BillingAddress>();
		 * List<Payment> payment1=new ArrayList<Payment>(); List<ShippingAddress>
		 * shippingAddress1=new ArrayList<ShippingAddress>();
		 * billingAddress1.add(billingAddress); shippingAddress1.add(shippingAddress);
		 * payment1.add(payment);
		 */

		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		// order.setInStock(inStock);

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		boolean outofstock = false;
		for (CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			if (cartItem.getOutofStockOrdered() > 0) {
				outofstock = true;
			}
			if (book.getInStockNumber() <= cartItem.getQty()) {
				book.setInStockNumber(0);
			} else {
				book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
			}

		}
		if (outofstock) {
			order.setBackOrder(true);
		}
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);

		return order;
	}

	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}

}
