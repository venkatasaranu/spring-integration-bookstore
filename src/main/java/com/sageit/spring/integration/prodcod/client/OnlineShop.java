package com.sageit.spring.integration.prodcod.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sageit.spring.integration.prodcod.domain.Book;
import com.sageit.spring.integration.prodcod.domain.MusicCD;
import com.sageit.spring.integration.prodcod.domain.Order;
import com.sageit.spring.integration.prodcod.domain.OrderItem;
import com.sageit.spring.integration.prodcod.domain.Software;
import com.sageit.spring.integration.prodcod.service.Shop;


/**
 * @author venkatasaranu
 *
 */
public class OnlineShop {

	private static final Logger log = Logger.getLogger(OnlineShop.class);
	
	public static void main(String[] args) {

		//Get hold of spring context
		AbstractApplicationContext context =
			new ClassPathXmlApplicationContext("/META-INF/com/prodcod/shop.xml");

		//Inject in Shop instance (Gateway)
		Shop shop = (Shop) context.getBean("shop");
		
		final Order order = createOrder();
		
		
		log.debug("*** [OnlineShop] ****");
		shop.placeOrder(order);
		
		context.close();
	}

	/*
	 * Create a dummy order
	 */
	private static Order createOrder() {
		Book book = new Book("The Java Virtual Machine Specification", "Addison-Wesley", new BigDecimal("31.05"), 1996, "Tim Lindholm");
		MusicCD cd = new MusicCD("Dangerous", "Epic Records", new BigDecimal("100"), 1991, "Michael Jackson");
		Software macos = new Software("Mavericks", "Apple Inc", new BigDecimal("100"), 2014, "10.9.3");
		
		OrderItem bookItems = new OrderItem(book);
		
		OrderItem cdItems = new OrderItem(cd);
		
		OrderItem swItems = new OrderItem(macos);
		
		final List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(bookItems);
		orderItems.add(cdItems);
		orderItems.add(swItems);
		
		Order order = new Order();
		order.setOrderItems(orderItems);
		log.debug("Order: " + order);

		return order;
	}
}


