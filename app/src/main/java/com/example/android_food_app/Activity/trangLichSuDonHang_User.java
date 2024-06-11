package com.example.android_food_app.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_food_app.R;

public class trangLichSuDonHang_User extends AppCompatActivity {
    TextView orderIdTextView, orderDateTextView, orderAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_lich_su_don_hang_user);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Order ID
        orderIdTextView = findViewById(R.id.order_id);
        String orderIdText = "Order ID: 123456";
        orderIdTextView.setText(getStyledText(orderIdText, "#9A9797", "#000000"));

        // Order Date
        orderDateTextView = findViewById(R.id.order_date);
        String orderDateText = "Order Date: 2024-05-22";
        orderDateTextView.setText(getStyledText(orderDateText, "#9A9797", "#000000"));

        // Total Amount
        orderAmountTextView = findViewById(R.id.order_amount);
        String orderAmountText = "Total Amount: $99.99";
        orderAmountTextView.setText(getStyledText(orderAmountText, "#9A9797", "#000000"));
    }

    private SpannableString getStyledText(String text, String colorBeforeColon, String colorAfterColon) {
        int colonIndex = text.indexOf(":");

        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(colorBeforeColon)), 0, colonIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(colorAfterColon)), colonIndex + 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new LeadingMarginSpan.Standard(16), colonIndex + 1, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
