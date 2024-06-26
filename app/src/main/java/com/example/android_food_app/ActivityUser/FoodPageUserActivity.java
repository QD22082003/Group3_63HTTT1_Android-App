package com.example.android_food_app.ActivityUser;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.FoodUserRecycleViewAdapter;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FoodPageUserActivity extends AppCompatActivity {
    private RecyclerView rcv_food;
    private FoodUserRecycleViewAdapter adapter;
    private ImageView img_back;
    private  List<Product> list = new ArrayList<>();
    private SearchView searchView;

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
        searchView = findViewById(R.id.searchView);

        // Thiết lập SearchView để tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        //khởi tạo adapter
        adapter = new FoodUserRecycleViewAdapter(list,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_food.setLayoutManager(gridLayoutManager);

        adapter.setDataFood(list);
        rcv_food.setAdapter(adapter);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = new ArrayList<>();
        getListFood();


    }

    private void getListFood() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("products");

        // Sử dụng query để lấy các sản phẩm có trường popular = true
        myRef.orderByChild("productType").equalTo("Món ngon").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                if (product != null) {
                    list.add(product);
                    adapter.setDataFood(list); // Cập nhật dữ liệu vào adapter
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Xử lý khi có sự thay đổi dữ liệu
                Product product = snapshot.getValue(Product.class);
                if (product != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getName().equals(product.getName())) {
                            list.set(i, product);
                            break;
                        }
                    }
                    adapter.setDataFood(list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Xử lý khi có sự xóa dữ liệu
                Product product = snapshot.getValue(Product.class);
                if (product == null || list == null || list.isEmpty()) {
                    return;
                }
                for(int i = 0; i< list.size(); i++) {
                    if(product.getName() == list.get(i).getName()){
                        list.remove(list.get(i));
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Xử lý khi có sự di chuyển dữ liệu
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi có lỗi
            }
        });
    }
    private void filter(String text) {
        List<Product> filteredList = new ArrayList<>();
        for (Product item : list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.setDataFood(filteredList);
        adapter.notifyDataSetChanged();
    }


}