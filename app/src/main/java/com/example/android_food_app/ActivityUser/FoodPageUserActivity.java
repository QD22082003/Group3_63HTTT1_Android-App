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

import com.example.android_food_app.AdapterUser.FoodUserAdapter;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class FoodPageUserActivity extends AppCompatActivity {
    private RecyclerView rcv_mongngon;
    private FoodUserAdapter adapter;
    private ImageView img_back;
    private  List<SanPham> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_page_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcv_mongngon = findViewById(R.id.rcv_monngon);
        img_back = findViewById(R.id.img_back);

        //khởi tạo adapter
        adapter = new FoodUserAdapter(list,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_mongngon.setLayoutManager(gridLayoutManager);

        adapter.setDataMonngon(getListMonNgon());
        rcv_mongngon.setAdapter(adapter);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private List<SanPham> getListMonNgon() {
        List<SanPham> list = new ArrayList<>();
        list.add(new SanPham(R.drawable.imgslider1, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider2, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider3, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider4, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider1, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider2, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider3, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider4, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider1, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider2, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider3, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.imgslider4, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));

        return list;
    }
}