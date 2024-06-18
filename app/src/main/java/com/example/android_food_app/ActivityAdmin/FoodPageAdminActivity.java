package com.example.android_food_app.ActivityAdmin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.android_food_app.AdapterAdmin.FoodAdminAdapter;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodPageAdminActivity extends AppCompatActivity {
    private RecyclerView rcv_food;
    private FoodAdminAdapter foodAdminAdapter;
    private FloatingActionButton fab_add;
    private ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_page_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        rcv_food = findViewById(R.id.rcv_food);
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
                Intent intent = new Intent(FoodPageAdminActivity.this, AddFoodAdminActivity.class);
                startActivity(intent);
            }
        });


        foodAdminAdapter = new FoodAdminAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_food.setLayoutManager(linearLayoutManager);
        foodAdminAdapter.setData(getListFood());
        rcv_food.setAdapter(foodAdminAdapter);

    }

    // Phương thức để lấy danh sách sản phẩm
    private List<Product> getListFood() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Product 1", "Description 1", "100000 VNĐ", "50000 VNĐ", "Giảm 10%",  getImageBytes(R.drawable.imgslider1), null, true, null));
        list.add(new Product(2, "Product 2", "Description 2", "0", "400000 VNĐ", "",  getImageBytes(R.drawable.imgslider2), null, false, null));
        return list;
    }

    private byte[] getImageBytes(int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}