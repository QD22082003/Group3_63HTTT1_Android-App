package com.example.android_food_app.ActivityUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.ShipAddressRecycleviewAdapter;
import com.example.android_food_app.Model.Customer;
import com.example.android_food_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShipmentDetailPageActivity extends AppCompatActivity {
    private RecyclerView rcv_address;
    private Button btn_add_address;
    private ShipAddressRecycleviewAdapter adapter;
    private List<Customer> customerList;
    private ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shipment_detail_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgBack = findViewById(R.id.imgBack);  // Sửa lỗi ID ở đây
        rcv_address = findViewById(R.id.rcv_address);
        btn_add_address = findViewById(R.id.btn_add_address);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_address.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_address.addItemDecoration(dividerItemDecoration);

        customerList = new ArrayList<>();
        adapter = new ShipAddressRecycleviewAdapter(customerList, new ShipAddressRecycleviewAdapter.IClickListener() {
            @Override
            public void onClickUpdateItem(Customer customer) {

            }

            @Override
            public void onClickDeleteItem(Customer customer) {

            }
        });
        rcv_address.setAdapter(adapter);


        btn_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipmentDetailPageActivity.this, AddShipmentActivity.class);
                startActivity(intent);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getListUserFromRealtimeDatabase();
    }

    private void getListUserFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customers");

        // Lấy thông tin người dùng hiện tại từ Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentEmail = currentUser.getEmail();
            myRef.orderByChild("emailuser").equalTo(currentEmail).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    try {
                        Customer customer = snapshot.getValue(Customer.class);
                        if (customer != null) {
                            customerList.add(customer);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (DatabaseException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}