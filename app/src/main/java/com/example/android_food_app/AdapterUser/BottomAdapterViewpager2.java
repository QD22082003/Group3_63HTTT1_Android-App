package com.example.android_food_app.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_food_app.Fragment.FragmentBottomNavigationUser.GioHangFragment;
import com.example.android_food_app.Fragment.FragmentBottomNavigationUser.LienHeFragment;
import com.example.android_food_app.Fragment.FragmentBottomNavigationUser.PhanHoiFragment;
import com.example.android_food_app.Fragment.FragmentBottomNavigationUser.TaiKhoanFragment;
import com.example.android_food_app.Fragment.FragmentBottomNavigationUser.TrangChuFragment;

public class BottomAdapterViewpager2 extends FragmentStateAdapter {
    public BottomAdapterViewpager2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new TrangChuFragment();
            case 1: return new GioHangFragment();
            case 2: return new PhanHoiFragment();
            case 3: return new LienHeFragment();
            case 4: return new TaiKhoanFragment();
            default: return new TrangChuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
