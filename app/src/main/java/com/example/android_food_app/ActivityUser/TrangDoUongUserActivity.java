package com.example.android_food_app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Adapter.DoUongRecycleViewAdapter;
import com.example.android_food_app.Adapter.MonNgonRecycleViewAdapter;
import com.example.android_food_app.Model.SanPham;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class TrangDoUongUserActivity extends AppCompatActivity {

    private RecyclerView rcv_douong;
    private DoUongRecycleViewAdapter adapter;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_do_uong_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcv_douong = findViewById(R.id.rcv_douong);
        img_back = findViewById(R.id.img_back);

        //khởi tạo adapter
        adapter = new DoUongRecycleViewAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_douong.setLayoutManager(gridLayoutManager);

        adapter.setDataDoUong(getListDoUong());
        rcv_douong.setAdapter(adapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<SanPham> getListDoUong() {
        List<SanPham> list = new ArrayList<>();
        list.add(new SanPham(R.drawable.nuocchanh, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocduahau, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocchanh, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocduahau, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocchanh, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.logocategory_douong, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocduahau, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.logocategory_douong, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocduahau, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.logocategory_douong, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.nuocduahau, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.logocategory_douong, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));

        return list;
    }
}