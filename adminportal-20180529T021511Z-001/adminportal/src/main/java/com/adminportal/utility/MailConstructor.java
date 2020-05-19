package com.adminportal.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.adminportal.domain.Order;
import com.adminportal.domain.User;

@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;

	@Autowired
	private TemplateEngine templateEngine;

	public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user,
			String password) {

		String url = contextPath + "/newUser?token=" + token;
		String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"
				+ password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Pravalika's Independent Bookstore - New User");
		email.setText(url + message);
		email.setFrom(env.getProperty("support.email"));
		return email;

	}

	public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - " + order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("admnportal5228@gmail.com"));
			}
		};

		return messagePreparator;
	}

	public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order, Locale locale, String subject,
			String text) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				if (!subject.equals("")) {
					email.setSubject("New Book Stock(s) Available!!");
				} else {
					email.setSubject("Order Cancellation - " + order.getId());
				}
				if (!text.equals("")) {
					email.setText("New Book stock(s) with title: " + order.getBookTitle()
							+ " has arrived and will be shipped");
				} else {
					email.setText("Your order with order number: " + order.getId()
							+ " was cancelled. Your money will be refunded in 10 business days. If you have any additional concerns, Please reply back through this email. Thank you!!!", true);
				}
				email.setFrom(new InternetAddress("admnportal5228@gmail.com"));
			}
		};

		return messagePreparator;
	}
}
