package com.example.android_food_app.AdapterAdmin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_food_app.FragmentAdmin.TrangDonHangFragment;
import com.example.android_food_app.FragmentAdmin.TrangMonNgonFragment;
import com.example.android_food_app.FragmentAdmin.TrangPhanHoiFragment;
import com.example.android_food_app.FragmentAdmin.TrangTaiKhoanFragment;


public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TrangMonNgonFragment();
            case 1:
                return new TrangPhanHoiFragment();
            case 2:
                return new TrangDonHangFragment();
            case 3:
                return new TrangTaiKhoanFragment();
            default:
                return new TrangMonNgonFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
