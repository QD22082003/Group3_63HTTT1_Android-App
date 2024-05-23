package com.example.android_food_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Adapter.UserAdapterDescAdmin;
import com.example.android_food_app.Model.User;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class TrangPhanHoiFragment extends Fragment {
    private RecyclerView rcv_user_desc;
    private UserAdapterDescAdmin userAdapterDescAdmin;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TrangPhanHoiFragment() {
        // Required empty public constructor
    }

    public static TrangPhanHoiFragment newInstance(String param1, String param2) {
        TrangPhanHoiFragment fragment = new TrangPhanHoiFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_phan_hoi_admin, container, false);

        rcv_user_desc = view.findViewById(R.id.rcv_user_desc);
        userAdapterDescAdmin = new UserAdapterDescAdmin(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_user_desc.setLayoutManager(linearLayoutManager);

        userAdapterDescAdmin.setData(getListUser());
        rcv_user_desc.setAdapter(userAdapterDescAdmin);

        return view;
    }

    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.ic_account, "giangnguyen123@gmail.com", "Đồ ăn vừa miệng lắm!"));
        list.add(new User(R.drawable.ic_account, "daobui123@gmail.com", "Giao hàng nhanh lắm <3 !!"));
        list.add(new User(R.drawable.ic_account, "todoan212003@gmail.com", "ok đấy"));
        list.add(new User(R.drawable.ic_account, "dphuongha212003@gmail.com", "Đồ ăn ngon, hợp khẩu vị"));
        list.add(new User(R.drawable.ic_account, "qa62@gmail.com", "Đồ ăn hơi ít!"));
        list.add(new User(R.drawable.ic_account, "tt25@gmail.com", "Nice to meet you!"));
        list.add(new User(R.drawable.ic_account, "qdbgymfit62@gmail.com", "Đồ ăn hơi nhiều!"));

        list.add(new User(R.drawable.ic_account, "giangnguyen123@gmail.com", "Đồ ăn vừa miệng lắm!"));
        list.add(new User(R.drawable.ic_account, "daobui123@gmail.com", "Giao hàng nhanh lắm <3 !!"));
        list.add(new User(R.drawable.ic_account, "todoan212003@gmail.com", "ok đấy"));
        list.add(new User(R.drawable.ic_account, "dphuongha212003@gmail.com", "Đồ ăn ngon, hợp khẩu vị"));
        list.add(new User(R.drawable.ic_account, "qa62@gmail.com", "Đồ ăn hơi ít!"));
        list.add(new User(R.drawable.ic_account, "tt25@gmail.com", "Nice to meet you!"));
        list.add(new User(R.drawable.ic_account, "qdbgymfit62@gmail.com", "Đồ ăn hơi nhiều!"));

        list.add(new User(R.drawable.ic_account, "giangnguyen123@gmail.com", "Đồ ăn vừa miệng lắm!"));
        list.add(new User(R.drawable.ic_account, "daobui123@gmail.com", "Giao hàng nhanh lắm <3 !!"));
        list.add(new User(R.drawable.ic_account, "todoan212003@gmail.com", "ok đấy"));
        list.add(new User(R.drawable.ic_account, "dphuongha212003@gmail.com", "Đồ ăn ngon, hợp khẩu vị"));
        list.add(new User(R.drawable.ic_account, "qa62@gmail.com", "Đồ ăn hơi ít!"));
        list.add(new User(R.drawable.ic_account, "tt25@gmail.com", "Nice to meet you!"));
        list.add(new User(R.drawable.ic_account, "qdbgymfit62@gmail.com", "Đồ ăn hơi nhiều!"));

        return list;
    }
}
