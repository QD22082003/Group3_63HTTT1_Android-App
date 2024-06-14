package com.example.android_food_app.FragmentAdmin;

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

import com.example.android_food_app.AdapterAdmin.HomeAdminAdapter;
import com.example.android_food_app.AdapterUser.PhotoAdapterViewPager2;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.ModelUser.Photo;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAdminFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator3;
    private PhotoAdapterViewPager2 adapter;
    private List<Photo> list;
    private List<Product1> listMonNgon_Trangchu;
    private RecyclerView rcv_trangchu;
    private HomeAdminAdapter adapter_trangchu;
    private CircleImageView category_image_monngon;
    private CircleImageView category_image_douong;
    private CircleImageView category_image_trangmieng;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeAdminFragment() {
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
    public static HomeAdminFragment newInstance(String param1, String param2) {
        HomeAdminFragment fragment = new HomeAdminFragment();
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

        listMonNgon_Trangchu = new ArrayList<>();
        listMonNgon_Trangchu = getListMonNgon_Trangchu();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        // Ánh xạ ViewPager2 và CircleIndicator3
        viewPager2 = view.findViewById(R.id.viewPager2);
        indicator3 = view.findViewById(R.id.indicator);
        rcv_trangchu = view.findViewById(R.id.rcv_trangchu);

        //khởi tạo adapter cho rcv_trangchu
        adapter_trangchu = new HomeAdminAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        rcv_trangchu.setLayoutManager(gridLayoutManager);
        rcv_trangchu.setFocusable(false);
        rcv_trangchu.setNestedScrollingEnabled(false);

        adapter_trangchu.setData(getListMonNgon_Trangchu());
        rcv_trangchu.setAdapter(adapter_trangchu);

        // Khởi tạo adapter và thiết lập cho ViewPager2
        adapter = new PhotoAdapterViewPager2(requireActivity(), list);
        viewPager2.setAdapter(adapter);
        indicator3.setViewPager(viewPager2);
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
    private List<Product1> getListMonNgon_Trangchu() {
        List<Product1> listMonNgon_Trangchu = new ArrayList<>();
        listMonNgon_Trangchu.add(new Product1(R.drawable.imgslider1, "", "Salad cá hồi", 20, 10000, "10%","có"));


        return listMonNgon_Trangchu;
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