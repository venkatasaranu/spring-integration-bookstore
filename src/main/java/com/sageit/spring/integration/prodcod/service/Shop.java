package com.sageit.spring.integration.prodcod.service;

import org.springframework.integration.annotation.Gateway;

import com.sageit.spring.integration.prodcod.domain.Order;

/**
 * Gateway interface. Provides facade to clients of the process pipeline
 * Will place all Orders into the 'orders' channel
 * 
 * @author BruceWayne
 *
 */
public interface Shop {

	@Gateway(requestChannel="ordersChannel")
	void placeOrder(Order order);

}