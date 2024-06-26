package com.example.android_food_app.ActivityAdmin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RevenuePageAdminActivity extends AppCompatActivity {
    private EditText edt_from_date, edt_to_date;
    private ImageButton imgBack;
    private RecyclerView rcv_revenue;
    private RevenueAdminAdapter revenueAdminAdapter;
    private TextView totalRevenue;
    private List<Order> allOrders;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

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
        totalRevenue = findViewById(R.id.totalRevenue);

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
        allOrders = getListRevenue();
        revenueAdminAdapter.setData(allOrders);
        rcv_revenue.setAdapter(revenueAdminAdapter);

        updateTotalRevenue(allOrders);
    }

    private List<Order> getListRevenue() {
        List<Order> list = new ArrayList<>();
//        list.add(new Order("12345678", "09-05-2024", 195000));
//        list.add(new Order("23453523", "15-05-2024", 222000));
//        list.add(new Order("34523454", "19-05-2024", 333000));
//        list.add(new Order("23453523", "15-05-2024", 222000));
//        list.add(new Order("34523454", "19-06-2024", 100000));
//        list.add(new Order("23453523", "15-06-2024", 400000));
//        list.add(new Order("34523454", "19-06-2024", 500000));
        return list;
    }

    private void updateTotalRevenue(List<Order> list) {
        double total = 0;
        for (Order order : list) {
            total += order.getTotal();
        }

        totalRevenue.setText(formatPrice(total));
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

                    String date = dateFormat.format(calendar.getTime());
                    editText.setText(date);
                    filterOrders();
                }, year, month, day);
        datePickerDialog.show();
    }

    private void filterOrders() {
        String fromDateString = edt_from_date.getText().toString();
        String toDateString = edt_to_date.getText().toString();

        if (fromDateString.isEmpty() || toDateString.isEmpty()) {
            revenueAdminAdapter.setData(allOrders);
            updateTotalRevenue(allOrders);
            return;
        }

        try {
            Date fromDate = dateFormat.parse(fromDateString);
            Date toDate = dateFormat.parse(toDateString);

            Log.d("RevenuePageAdminActivity", "From date: " + fromDateString);
            Log.d("RevenuePageAdminActivity", "To date: " + toDateString);

            List<Order> filteredList = new ArrayList<>();
            for (Order order : allOrders) {
                Date orderDate = dateFormat.parse(order.getDate());
                if (orderDate != null && !orderDate.before(fromDate) && !orderDate.after(toDate)) {
                    filteredList.add(order);
                }
            }

            Log.d("RevenuePageAdminActivity", "Filtered list size: " + filteredList.size());

            revenueAdminAdapter.setData(filteredList);
            updateTotalRevenue(filteredList);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Thêm phương thức định dạng giá
    private String formatPrice(double price) {
        if (price == 0) {
            return "0 VND";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' '); // Sử dụng khoảng trắng làm dấu phân cách nhóm số
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        return decimalFormat.format(price) + " VND";
    }
}
