package com.example.android_food_app.FragmentAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterAdmin.FeedbackAdminAdapter;
import com.example.android_food_app.AdapterAdmin.OrderAdminAdapter;
import com.example.android_food_app.Model.Order;
import com.example.android_food_app.Model.User;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangDonHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangDonHangFragment extends Fragment {
    private RecyclerView rcv_order;
    private OrderAdminAdapter mAdapter;
    private List<Order> mListOrder;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TrangDonHangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangDonHangFragment newInstance(String param1, String param2) {
        TrangDonHangFragment fragment = new TrangDonHangFragment();
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
//        return inflater.inflate(R.layout.fragment_trang_don_hang_admin, container, false);
        View view = inflater.inflate(R.layout.fragment_trang_don_hang_admin, container, false);
        rcv_order = view.findViewById(R.id.rcv_order);
        mAdapter = new OrderAdminAdapter(getContext());
        rcv_order.setAdapter(mAdapter);
        rcv_order.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        mAdapter.setData(mListOrder);
        return view;
    }

    private void initData() {
        mListOrder = new ArrayList<>();
        mListOrder.add(new Order("1", "gianghoang150503@gmail.com", "User 1", "123456789", "SN 31, ngách 788/26, Thanh Liệt, Thanh Trì, Hà Nội", "Hamburger Bò - SL 01 Salad trộn cá ngừ - SL 02", "09-05-2024, 10:11AM", 195000, "Đã thanh toán"));
        mListOrder.add(new Order("2", "user2@example.com", "User 2", "987654321", "456 Đường DEF", "Menu 2", "2024-06-14", 250000, "Chưa thanh toán"));
    }
}