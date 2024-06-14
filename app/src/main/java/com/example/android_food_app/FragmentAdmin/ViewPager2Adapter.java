package com.example.android_food_app.FragmentAdmin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TrangChuMonNgonFragment();
            case 1:
                return new TrangPhanHoiFragment();
            case 2:
                return new TrangDonHangFragment();
            case 3:
                return new TrangTaiKhoanFragment();
            default:
                return new TrangChuMonNgonFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
