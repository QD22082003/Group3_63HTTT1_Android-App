package com.example.android_food_app.Fragment.FragmentBottomNavigationUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android_food_app.AdapterUser.CartAdapterRecycleView;
import com.example.android_food_app.Model.CartManager;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartUserFragment extends Fragment {
    private RecyclerView rcvCart;
    private CartAdapterRecycleView cartAdapter;
    private List<Product> cartProducts = new ArrayList<>();
    private TextView txt_sum;
    private Button btn_add_order;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GioHangFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        List<Product> cartProducts = CartManager.getInstance().getCartProducts();
        cartAdapter = new CartAdapterRecycleView(cartProducts);
        rcvCart.setAdapter(cartAdapter);

        // Xử lý sự kiện khi click vào nút "Tạo đơn hàng"
        btn_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để tính tổng tiền và xử lý logic tạo đơn hàng ở đây
                calculateTotal(); // Tính tổng tiền
                // Xử lý logic để tạo đơn hàng (có thể gọi phương thức hoặc chuyển sang màn hình tạo đơn hàng)
                // Ví dụ: startAddOrderActivity();
            }
        });



        return view;
    }

    public void onResume() {
        super.onResume();
        // Load lại danh sách sản phẩm từ CartManager khi fragment hiển thị lại
        loadCartProducts();
    }
    private void loadCartProducts() {
        // Lấy danh sách sản phẩm từ CartManager
        cartProducts.clear();
        cartProducts.addAll(CartManager.getInstance().getCartProducts());
        // Cập nhật lại adapter khi có thay đổi
        cartAdapter.notifyDataSetChanged();
    }
    // Phương thức để tính tổng tiền của giỏ hàng
    private void calculateTotal() {
        double total = 0.0;
        for (Product product : cartProducts) {
            // Lấy giá của từng sản phẩm
            double price = Double.parseDouble(product.getPriceNew()); // Chuyển đổi giá sản phẩm sang dạng double (nếu cần)

            // Tính tổng tiền của từng sản phẩm và cộng dồn vào biến tổng
            total +=  price;
        }

        // Hiển thị tổng tiền đã tính được lên TextView txt_sum
        txt_sum.setText(String.format("%.3f VND", total)); // Định dạng và hiển thị tổng tiền theo đơn vị VND (Ví dụ)
    }


}