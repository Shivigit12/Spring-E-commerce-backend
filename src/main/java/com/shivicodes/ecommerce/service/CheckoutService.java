package com.shivicodes.ecommerce.service;

import com.shivicodes.ecommerce.dto.Purchase;
import com.shivicodes.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
