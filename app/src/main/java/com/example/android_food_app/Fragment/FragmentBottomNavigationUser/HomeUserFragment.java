package com.example.android_food_app.Fragment.FragmentBottomNavigationUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_food_app.ActivityUser.DrinkPageUserActivity;
import com.example.android_food_app.ActivityUser.FoodPageUserActivity;
import com.example.android_food_app.ActivityUser.DessertPageUserActivity;
import com.example.android_food_app.AdapterUser.HomeUserAdapter;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.ModelUser.Photo;
import com.example.android_food_app.AdapterUser.PhotoAdapterViewPager2;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeUserFragment extends Fragment {
    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator3;
    private PhotoAdapterViewPager2 adapter;
    private List<Photo> list;
    private List<Product1> list_product;
    private RecyclerView rcv_trangchu;
    private HomeUserAdapter adapter_trangchu;
    private CircleImageView category_image_food;
    private CircleImageView category_image_drink;
    private CircleImageView category_image_dessert;

    // Handler để cập nhật giao diện người dùng từ các luồng nền
    private Handler handler = new Handler(Looper.getMainLooper());

    // Runnable là một giao diện đơn giản có một phương thức duy nhất là run().
    // Nó đại diện cho một tác vụ có thể thực thi.
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == list.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
            handler.postDelayed(this, 3000); // Lặp lại runnable mỗi 3 giây
        }
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeUserFragment() {
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
    public static HomeUserFragment newInstance(String param1, String param2) {
        HomeUserFragment fragment = new HomeUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo danh sách ảnh
        list = new ArrayList<>();
        list = getListPhoto();

        list_product = new ArrayList<>();
        list_product = getListProduct();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);

        // Ánh xạ ViewPager2 và CircleIndicator3
        viewPager2 = view.findViewById(R.id.viewPager2);
        indicator3 = view.findViewById(R.id.indicator);
        rcv_trangchu = view.findViewById(R.id.rcv_trangchu);


        // Khởi tạo adapter và thiết lập cho ViewPager2
        adapter = new PhotoAdapterViewPager2(requireActivity(), list);
        viewPager2.setAdapter(adapter);
        indicator3.setViewPager(viewPager2);

        //Ánh xạ CircleImageView
        category_image_food = view.findViewById(R.id.category_image_food);
        category_image_drink = view.findViewById(R.id.category_image_drink);
        category_image_dessert = view.findViewById(R.id.category_image_dessert);


        //bắt sự kiện khi ấn vào CircleImageView
        category_image_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_monngon = new Intent(getActivity(), FoodPageUserActivity.class);
                startActivity(intent_monngon);

            }
        });

        category_image_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_douong = new Intent(getActivity(), DrinkPageUserActivity.class);
                startActivity(intent_douong);

            }
        });

        category_image_dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_trangmieng = new Intent(getActivity(), DessertPageUserActivity.class);
                startActivity(intent_trangmieng);

            }
        });

        //khởi tạo adapter cho rcv_trangchu
        adapter_trangchu = new HomeUserAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        rcv_trangchu.setLayoutManager(gridLayoutManager);
        rcv_trangchu.setFocusable(false);
        rcv_trangchu.setNestedScrollingEnabled(false);

        adapter_trangchu.setDataProduct(getListProduct());
        rcv_trangchu.setAdapter(adapter_trangchu);


        // Handler và runnable sẽ thực hiện khi ViewPager2 chuyển page
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Remove runnable cũ và post runnable mới
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

        // Bắt đầu handler lần đầu tiên
        handler.postDelayed(runnable, 3000);

        return view;
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.imgslider1));
        list.add(new Photo(R.drawable.imgslider2));
        list.add(new Photo(R.drawable.imgslider3));
        list.add(new Photo(R.drawable.imgslider4));
        // Thêm log để kiểm tra dữ liệu
        Log.d("TrangChuFragment", "Data size: " + list.size());
        return list;
    }

    private List<Product1> getListProduct() {
        List<Product1> listProduct = new ArrayList<>();
        listProduct.add(new Product1(R.drawable.imgslider1, "Salad cá hồi", "", "60 000 VND", "40 000 VND", "Giảm 10 %", ""));
        return listProduct;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}
