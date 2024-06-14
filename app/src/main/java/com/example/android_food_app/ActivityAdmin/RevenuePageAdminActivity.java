package com.example.android_food_app.ActivityAdmin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterAdmin.RevenueAdminAdapter;
import com.example.android_food_app.Model.Order;
import com.example.android_food_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RevenuePageAdminActivity extends AppCompatActivity {
    private EditText edt_from_date, edt_to_date;
    private ImageButton imgBack;
    private RecyclerView rcv_revenue;
    private RevenueAdminAdapter revenueAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_revenue_page_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        edt_from_date = findViewById(R.id.edt_from_date);
        edt_to_date = findViewById(R.id.edt_to_date);
        imgBack = findViewById(R.id.imgBack);
        rcv_revenue = findViewById(R.id.rcv_revenue);

        edt_from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(edt_from_date);
            }
        });

        edt_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(edt_to_date);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        revenueAdminAdapter = new RevenueAdminAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_revenue.setLayoutManager(linearLayoutManager);
        revenueAdminAdapter.setData(getListRevenue());
        rcv_revenue.setAdapter(revenueAdminAdapter);

    }

    private List<Order> getListRevenue() {
        List<Order> list = new ArrayList<>();
        list.add(new Order("12345678", "09-05-2024, 10:12AM", 195000));
        list.add(new Order("23453523", "15-05-2024, 11:11AM", 222000));
        list.add(new Order("34523454", "19-05-2024, 06:16AM", 333000));
        list.add(new Order("21354345", "29-05-2024, 12:12AM", 666000));
        list.add(new Order("21354345", "29-05-2024, 12:12AM", 666000));
        list.add(new Order("21354345", "29-05-2024, 12:12AM", 666000));
        return list;
    }

    private void showDatePickerDialog(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RevenuePageAdminActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(Calendar.YEAR, selectedYear);
                    calendar.set(Calendar.MONTH, selectedMonth);
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy, hh:mm a", Locale.getDefault());
                    String date = dateFormat.format(calendar.getTime());
                    editText.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }
}