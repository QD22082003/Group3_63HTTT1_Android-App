package com.example.android_food_app.ActivityAdmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.android_food_app.R;

public class DetailProductAdminActivity extends AppCompatActivity {
    private TextView detail_name, detail_desc, detail_price, detail_sale, detail_popular;
    private ImageView detail_img, detail_imgOther;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        detail_name = findViewById(R.id.detail_name);
        detail_desc = findViewById(R.id.detail_desc);
        detail_price = findViewById(R.id.detail_price);
        detail_sale = findViewById(R.id.detail_sale);
        detail_popular = findViewById(R.id.detail_popular);
        detail_img = findViewById(R.id.detail_img);
        detail_imgOther = findViewById(R.id.detail_imgOther);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle= getIntent().getExtras();
        if (bundle != null) {
            detail_name.setText(bundle.getString("name"));
            detail_desc.setText(bundle.getString("desc"));
            detail_price.setText(bundle.getString("price"));
            detail_sale.setText(bundle.getString("sale"));
            detail_popular.setText(bundle.getBoolean("popular") ? "Có" : "Không");
            Glide.with(this).load(bundle.getString("imgUrl")).into(detail_img);
            Glide.with(this).load(bundle.getString("imgDetail")).into(detail_imgOther);
        }

    }
}