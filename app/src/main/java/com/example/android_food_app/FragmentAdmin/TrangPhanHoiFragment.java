package com.example.android_food_app.FragmentAdmin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterAdmin.FeedbackAdminAdapter;
import com.example.android_food_app.Model.User;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class TrangPhanHoiFragment extends Fragment {
    private RecyclerView rcv_feedback;
    private FeedbackAdminAdapter mAdapter;
    private List<User> mListUser;

    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_phan_hoi_admin, container, false);
        rcv_feedback = view.findViewById(R.id.rcv_feedback);
        mAdapter = new FeedbackAdminAdapter(getContext());
        rcv_feedback.setAdapter(mAdapter);
        rcv_feedback.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        mAdapter.setData(mListUser);
        return view;
    }

    private void initData() {
        mListUser = new ArrayList<>();
        mListUser.add(new User(R.drawable.user1, "User 1", "Description 1"));
        mListUser.add(new User(R.drawable.user2, "User 2", "Description 2"));
        mListUser.add(new User(R.drawable.user3, "User 3", "Description 3"));
        mListUser.add(new User(R.drawable.user4, "User 4", "Description 4"));

        mListUser.add(new User(R.drawable.user1, "User 1", "Description 1"));
        mListUser.add(new User(R.drawable.user2, "User 2", "Description 2"));
        mListUser.add(new User(R.drawable.user3, "User 3", "Description 3"));
        mListUser.add(new User(R.drawable.user4, "User 4", "Description 4"));

        mListUser.add(new User(R.drawable.user1, "User 5", "Description 5"));
        mListUser.add(new User(R.drawable.user2, "User 6", "Description 6"));
        mListUser.add(new User(R.drawable.user3, "User 7", "Description 7"));
        mListUser.add(new User(R.drawable.user4, "User 8", "Description 8"));

        mListUser.add(new User(R.drawable.user1, "User 5", "Description 5"));
        mListUser.add(new User(R.drawable.user2, "User 6", "Description 6"));
        mListUser.add(new User(R.drawable.user3, "User 7", "Description 7"));
        mListUser.add(new User(R.drawable.user4, "User 8", "Description 8"));
    }
}
