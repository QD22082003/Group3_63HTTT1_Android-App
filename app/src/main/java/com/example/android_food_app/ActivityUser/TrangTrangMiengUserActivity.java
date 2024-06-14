package com.example.android_food_app.ActivityUser;

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

import com.example.android_food_app.AdapterUser.TrangMiengRecycleViewAdapter;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class TrangTrangMiengUserActivity extends AppCompatActivity {
    private RecyclerView rcv_trangmieng;
    private TrangMiengRecycleViewAdapter adapter;
    private ImageView img_back;
    private  List<SanPham> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_trang_mieng_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcv_trangmieng = findViewById(R.id.rcv_trangmieng);
        img_back = findViewById(R.id.img_back);

        //khởi tạo adapter
        adapter = new TrangMiengRecycleViewAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_trangmieng.setLayoutManager(gridLayoutManager);

        adapter.setDataTrangMieng(getListTrangMieng());
        rcv_trangmieng.setAdapter(adapter);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private List<SanPham> getListTrangMieng() {
        List<SanPham> list = new ArrayList<>();
        list.add(new SanPham(R.drawable.banhtiramisu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.kembat, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.dudu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.banhtiramisu, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.kembat, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.dudu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.banhtiramisu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.dudu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.banhtiramisu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.dudu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.banhtiramisu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.dudu, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));

        return list;
    }

}