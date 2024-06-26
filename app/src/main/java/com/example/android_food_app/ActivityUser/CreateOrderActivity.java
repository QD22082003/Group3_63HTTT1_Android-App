package com.example.android_food_app.ActivityUser;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.OrderUserAdapter;
import com.example.android_food_app.R;

public class CreateOrderActivity extends AppCompatActivity {
    TextView txtTotalAmount;
    private RecyclerView rcvCreateOrder;
    private OrderUserAdapter orderUserAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        rcvCreateOrder = findViewById(R.id.rcv_create_order);
        // Get the total amount from the Intent
        double totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0.0);
        txtTotalAmount.setText(String.format("%.3f VND", totalAmount));

        rcvCreateOrder.setLayoutManager(new LinearLayoutManager(this));
        orderUserAdapter = new OrderUserAdapter(this);
        rcvCreateOrder.setAdapter(orderUserAdapter);
    }
}
