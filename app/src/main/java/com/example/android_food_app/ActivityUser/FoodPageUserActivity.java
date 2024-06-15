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
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class FoodPageUserActivity extends AppCompatActivity {
    private RecyclerView rcv_food;
    private FoodUserAdapter adapter;
    private ImageView img_back;
    private  List<Product1> list = new ArrayList<>();

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
        rcv_food = findViewById(R.id.rcv_food);
        img_back = findViewById(R.id.img_back);

        //khởi tạo adapter
        adapter = new FoodUserAdapter(list,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_food.setLayoutManager(gridLayoutManager);

        adapter.setDataFood(getListFood());
        rcv_food.setAdapter(adapter);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private List<Product1> getListFood() {
        List<Product1> list = new ArrayList<>();
        list.add(new Product1(R.drawable.imgslider1, "Salad cá hồi", "", "60 000 VND", "40 000 VND", "Giảm 10 %", ""));
        list.add(new Product1(R.drawable.imgslider2, "Salad cá hồi", "", "60 000 VND", "40 000 VND", "Giảm 10 %", ""));
        return list;
    }
}