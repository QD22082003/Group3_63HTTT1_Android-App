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

import com.example.android_food_app.AdapterUser.DessertUserAdapter;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class DessertPageUserActivity extends AppCompatActivity {
    private RecyclerView rcv_dessert;
    private DessertUserAdapter adapter;
    private ImageView img_back;
    private  List<Product1> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dessert_page_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcv_dessert = findViewById(R.id.rcv_dessert);
        img_back = findViewById(R.id.img_back);

        //khởi tạo adapter
        adapter = new DessertUserAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_dessert.setLayoutManager(gridLayoutManager);

        adapter.setDataDessert(getListDessert());
        rcv_dessert.setAdapter(adapter);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private List<Product1> getListDessert() {
        List<Product1> list = new ArrayList<>();
        list.add(new Product1(R.drawable.imgslider1, "Salad cá hồi", "", "60 000 VND", "40 000 VND", "Giảm 10 %", ""));
        return list;
    }

}