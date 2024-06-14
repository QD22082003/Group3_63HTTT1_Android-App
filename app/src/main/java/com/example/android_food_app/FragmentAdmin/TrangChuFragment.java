package com.example.android_food_app.FragmentAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android_food_app.ActivityAdmin.TrangDoUongAdminActivity;
import com.example.android_food_app.ActivityAdmin.TrangDoanhThuAdminActivity;
import com.example.android_food_app.ActivityAdmin.TrangMonNgonAdminActivity;
import com.example.android_food_app.ActivityAdmin.TrangTrangMiengAdminActivity;
import com.example.android_food_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangMonNgonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
//        return inflater.inflate(R.layout.fragment_trang_chu_admin, container, false);
        View view = inflater.inflate(R.layout.fragment_trang_chu_admin, container, false);
        Button btnMonNgon = view.findViewById(R.id.btnMonNgon);
        Button btnDoUong = view.findViewById(R.id.btnDoUong);
        Button btnTrangMieng = view.findViewById(R.id.btnTrangMieng);
        btnMonNgon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrangMonNgonAdminActivity.class);
                startActivity(intent);
            }
        });

        btnDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrangDoUongAdminActivity.class);
                startActivity(intent);
            }
        });

        btnTrangMieng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrangTrangMiengAdminActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}