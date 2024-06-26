package com.example.android_food_app.Fragment.FragmentBottomNavigationUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.CartAdapterRecycleView;
import com.example.android_food_app.Model.CartManager;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class CartUserFragment extends Fragment {
    private RecyclerView rcvCart;
    private CartAdapterRecycleView cartAdapter;
    private List<Product> cartProducts;  // Use the global variable here
    private TextView txt_sum;
    private Button btn_add_order;

    // Constants for arguments
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters for fragment initialization
    private String mParam1;
    private String mParam2;

    public CartUserFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of this fragment using the provided parameters
    public static CartUserFragment newInstance(String param1, String param2) {
        CartUserFragment fragment = new CartUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Initialize cartProducts here to avoid null pointer exception
        cartProducts = new ArrayList<>();  // Initialize an empty list
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_user, container, false);

        rcvCart = view.findViewById(R.id.rcv_cart);
        txt_sum = view.findViewById(R.id.txt_sum);
        btn_add_order = view.findViewById(R.id.btn_add_order);

        rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapterRecycleView(cartProducts);
        rcvCart.setAdapter(cartAdapter);

        // Calculate and display total initially
        calculateTotal();

        // Handle click event for "Tạo đơn hàng" button
        btn_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can add logic here for creating an order
                clickOpenBottomSheetDialog();
            }
        });

        return view;
    }

    private void clickOpenBottomSheetDialog() {
        // Implement logic to open bottom sheet dialog
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload cart products when fragment is resumed
        loadCartProducts();
    }

    private void loadCartProducts() {
        // Reload cart products from CartManager
        List<Product> updatedProducts = CartManager.getInstance().getCartProducts();
        cartProducts.clear();  // Clear the current list
        cartProducts.addAll(updatedProducts);  // Add all products from CartManager
        cartAdapter.notifyDataSetChanged();

        // Calculate total after loading cart products
        calculateTotal();
    }

    private void calculateTotal() {
        double total = 0.0;
        for (Product product : cartProducts) {
            // Parse product price to double
            double price = Double.parseDouble(product.getPriceNew());
            // Calculate total price
            total += price;
        }

        // Format and display total on txt_sum TextView
        txt_sum.setText(String.format("%.3f VND", total));
    }
}