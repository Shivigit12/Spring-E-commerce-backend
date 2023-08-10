package com.shivicodes.ecommerce.service;

import com.shivicodes.ecommerce.dao.CustomerRepository;
import com.shivicodes.ecommerce.dto.Purchase;
import com.shivicodes.ecommerce.dto.PurchaseResponse;
import com.shivicodes.ecommerce.entity.Customer;
import com.shivicodes.ecommerce.entity.Order;
import com.shivicodes.ecommerce.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class CheckoutServiceImpl implements CheckoutService{
    private CustomerRepository customerRepository;
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // retrieve the order info from dto
        Order order = purchase.getOrder();
        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        //populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);
        //save to the database
        customerRepository.save(customer);
        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //generate a random UUID number
        return UUID.randomUUID().toString();
    }
}
