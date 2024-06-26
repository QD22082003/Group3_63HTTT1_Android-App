package com.example.android_food_app.ActivityUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.OrderUserAdapter;
import com.example.android_food_app.Model.Customer;
import com.example.android_food_app.R;

public class CreateOrderActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_CUSTOMER = 1;
    TextView txtTotalAmount;
    private RecyclerView rcvCreateOrder;
    private OrderUserAdapter orderUserAdapter;
    private Button btnCreateOrder, btn_customer;
    private EditText edt_name_order, edt_phone_order, edt_address_order;
    @SuppressLint("DefaultLocale")
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
        btnCreateOrder = findViewById(R.id.btnCreateOrder);
        btn_customer = findViewById(R.id.btn_customer);
        edt_name_order = findViewById(R.id.edt_name_order);
        edt_phone_order = findViewById(R.id.edt_phone_order);
        edt_address_order = findViewById(R.id.edt_address_order);
        // Get the total amount from the Intent
        double totalAmount = getIntent().getDoubleExtra("TOTAL_AMOUNT", 0.0);
        txtTotalAmount.setText(String.format("%.3f VND", totalAmount));

        rcvCreateOrder.setLayoutManager(new LinearLayoutManager(this));
        orderUserAdapter = new OrderUserAdapter(this);
        rcvCreateOrder.setAdapter(orderUserAdapter);

        btn_customer.setOnClickListener(v -> {
            Intent intent = new Intent(CreateOrderActivity.this, ShipmentDetailPageActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_CUSTOMER);
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_CUSTOMER && resultCode == RESULT_OK && data != null) {
            Customer selectedCustomer = (Customer) data.getSerializableExtra("selected_customer");
            if (selectedCustomer != null) {
                edt_name_order.setText(selectedCustomer.getName());
                edt_phone_order.setText(selectedCustomer.getPhone());
                edt_address_order.setText(selectedCustomer.getAddress());
            }
        }
    }
}
