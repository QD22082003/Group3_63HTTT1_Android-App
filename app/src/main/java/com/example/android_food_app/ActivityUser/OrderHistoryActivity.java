package com.example.android_food_app.ActivityUser;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.OrderHistoryAdapter;
import com.example.android_food_app.Model.Order;
import com.example.android_food_app.R;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOrderHistory;
    private OrderHistoryAdapter orderHistoryAdapter;
    private List<Order> orderList;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewOrderHistory = findViewById(R.id.rcv_order);
        orderList = new ArrayList<>();
        orderHistoryAdapter = new OrderHistoryAdapter(this);  // Đúng tên lớp

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOrderHistory.setLayoutManager(layoutManager);
        recyclerViewOrderHistory.setAdapter(orderHistoryAdapter);

        imgBack = findViewById(R.id.imgBack);  // Sửa lỗi ID ở đây
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Giả sử bạn có một phương thức để lấy lịch sử đặt hàng, như fetchOrderHistory()
        fetchOrderHistory();
    }

    private void fetchOrderHistory() {
        // Điền danh sách orderList với các đối tượng Order
        // Đây chỉ là ví dụ. Thay thế điều này bằng logic lấy dữ liệu thực.
        orderList.add(new Order("1", "example1@gmail.com", "John Doe", "123456789", "123 Street", "Pizza", "2023-06-15", 20.5, "Paid"));
        orderList.add(new Order("2", "example2@gmail.com", "Jane Doe", "987654321", "456 Avenue", "Burger", "2023-06-16", 15.0, "Paid"));

        orderHistoryAdapter.setData(orderList);
    }
}
