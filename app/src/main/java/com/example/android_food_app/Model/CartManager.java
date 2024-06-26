package com.example.android_food_app.Model;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartProducts;

    private CartManager() {
        cartProducts = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cartProducts.add(product);
    }

    public List<Product> getCartProducts() {
        return cartProducts;
    }
}
