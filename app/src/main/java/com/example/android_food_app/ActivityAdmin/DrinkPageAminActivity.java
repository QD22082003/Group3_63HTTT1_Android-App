package com.example.android_food_app.ActivityAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterAdmin.DrinkAdminAdapter;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DrinkPageAminActivity extends AppCompatActivity {
    private RecyclerView rcv_drink;
    private DrinkAdminAdapter drinkAdminAdapter;
    private FloatingActionButton fab_add;
    private ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drink_page_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        rcv_drink = findViewById(R.id.rcv_drink);
        fab_add = findViewById(R.id.fab_add);
        imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Ấn icon add để vào trang Thêm
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinkPageAminActivity.this, AddDrinkAdminActivity.class);
                startActivity(intent);
            }
        });

        drinkAdminAdapter = new DrinkAdminAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_drink.setLayoutManager(linearLayoutManager);
        drinkAdminAdapter.setData(getListDrink());
        rcv_drink.setAdapter(drinkAdminAdapter);
    }

    private List<Product1> getListDrink() {
        List<Product1> list = new ArrayList<>();
        list.add(new Product1(R.drawable.douong1admin, "Product 1", "Description 1 test hello good morning bye", "10000 VNĐ", "5000 VNĐ", "Giảm 10%", "Có"));
        list.add(new Product1(R.drawable.douong2admin, "Product 2", "Description 2 test hello good morning bye", "0", "40000 VNĐ", "", "Không"));
        list.add(new Product1(R.drawable.douong3admin, "Product 3", "Description 3 test hello good morning bye", "", "30000 VNĐ", "", "Không"));
        list.add(new Product1(R.drawable.douong4admin, "Product 4", "Description 4 test hello good morning bye Description 4 test hello good morning bye Description 4 test hello good morning bye", "240000 VNĐ", "200000 VNĐ", "Giảm 20%", "Có"));
        list.add(new Product1(R.drawable.douong1admin, "Product 5", "Description 5 test hello good morning bye", "30000 VNĐ", "20000 VNĐ", "Giảm 30%", "Có"));
        return list;
    }
}