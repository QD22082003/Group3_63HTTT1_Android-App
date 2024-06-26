package com.example.android_food_app.Model;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private static CartManager instance;
    private Map<String, Integer> cartProducts; // key = firebase_generated_id, value = quantity

    private CartManager() {
        cartProducts = new HashMap<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        String productId = product.getName(); // Assuming product.getId() returns Firebase generated ID as String
        Integer quantity = cartProducts.getOrDefault(productId, 0);
        cartProducts.put(productId, quantity + 1);
    }

    public void removeProduct(Product product) {
        String productId = product.getName(); // Assuming product.getId() returns Firebase generated ID as String
        Integer quantity = cartProducts.getOrDefault(productId, 0);
        if (quantity > 0) {
            cartProducts.put(productId, quantity - 1);
        }
    }

    public void clearCart() {
        cartProducts.clear();
    }

    public Map<String, Integer> getCartProducts() {
        return cartProducts;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        // Assuming Product class has getPrice() method
        for (Map.Entry<String, Integer> entry : cartProducts.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            // Replace with actual implementation for your Product class
            double price = getProductPrice(productId);
            totalPrice += price * quantity;
        }
        return totalPrice;
    }

    private double getProductPrice(String productId) {
        // Replace with actual logic to fetch price of product by productId
        // Example:
        // Product product = YourProductRepository.getProduct(productId);
        // return product.getPrice();
        // For demonstration purpose, returning a fixed price
        return 10.0; // Replace with actual logic to get price
    }

    public boolean isEmpty() {
        return cartProducts.isEmpty();
    }
}
