package com.example.android_food_app.AdapterUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android_food_app.Fragment.FragmentSliderUser.PhotoFragment;
import com.example.android_food_app.ModelUser.Photo;

import java.util.List;

public class PhotoAdapterViewPager2 extends FragmentStateAdapter {
    private List<Photo> mylist;
    public PhotoAdapterViewPager2(@NonNull FragmentActivity fragmentActivity, List<Photo> list) {
        super(fragmentActivity);
        this.mylist = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //add động những fragment tương ứng với tập dữ liệu photo
        Photo photo = mylist.get(position);
        //cần hiển thị photo trong Photofragment mà mình thiết kế
        //cần truyền dữ liệu của photo sang Photofragment
        //sử dụng setArguments để chuyển dữ liệu sang
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_photo", photo);

        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments(bundle);
        return photoFragment;
    }

    @Override
    public int getItemCount() {
        if (mylist != null) {
            return mylist.size();
        }
        return 0;
    }
}
