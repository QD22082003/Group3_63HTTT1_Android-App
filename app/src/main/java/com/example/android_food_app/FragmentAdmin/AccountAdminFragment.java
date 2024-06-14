package com.example.android_food_app.FragmentAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android_food_app.Activity.LoginActivity;
import com.example.android_food_app.ActivityAdmin.RevenuePageAdminActivity;
import com.example.android_food_app.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountAdminFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountAdminFragment() {
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
    public static AccountAdminFragment newInstance(String param1, String param2) {
        AccountAdminFragment fragment = new AccountAdminFragment();
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
//        return inflater.inflate(R.layout.fragment_trang_tai_khoan_admin, container, false);

        View view = inflater.inflate(R.layout.fragment_account_admin, container, false);
        LinearLayout revenueLayout = view.findViewById(R.id.revenue_layout);
        LinearLayout logoutLayout = view.findViewById(R.id.logout);
        revenueLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RevenuePageAdminActivity.class);
                startActivity(intent);
            }
        });
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}