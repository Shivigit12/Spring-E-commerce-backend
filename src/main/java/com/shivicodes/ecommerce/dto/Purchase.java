package com.shivicodes.ecommerce.dto;

import com.shivicodes.ecommerce.entity.Address;
import com.shivicodes.ecommerce.entity.Customer;
import com.shivicodes.ecommerce.entity.Order;
import com.shivicodes.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
