package com.example.android_food_app.ActivityUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.AdapterUser.DetailUserAdadpter;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class DetailPageUserActivity extends AppCompatActivity {
    private RecyclerView rcv_detail;
    private DetailUserAdadpter adapter;
    private ImageView img_back;
    private ImageView img_detail;
    private TextView txt_name_detail;
    private TextView txt_price_new_detail;
    private TextView txt_price_old_chitiet;
    private TextView txt_desc_detail;
    private View line;
    private Button btn_them;
    private Button btn_huybo;
    private Button btn_themvaogiohang;
    private ImageView img_chitiet_bottomsheet;
    private TextView txt_ten_chitiet_bottomsheet, txt_gia_chitiet_bottomsheet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_page_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcv_detail = findViewById(R.id.rcv_detail);
        img_back = findViewById(R.id.img_back);
        img_detail = findViewById(R.id.img_detail);
        txt_name_detail = findViewById(R.id.txt_name_detail);
        txt_price_new_detail = findViewById(R.id.txt_price_new_detail);
        txt_price_old_chitiet = findViewById(R.id.txt_price_old_chitiet);
        txt_desc_detail = findViewById(R.id.txt_desc_detail);
        img_back = findViewById(R.id.img_back);
        line = findViewById(R.id.line);
        btn_them = findViewById(R.id.btn_them);



        //khởi tạo adapter
        adapter = new DetailUserAdadpter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_detail.setLayoutManager(gridLayoutManager);

        adapter.setDataImgOther(getListImgOther());
        rcv_detail.setAdapter(adapter);
        rcv_detail.setNestedScrollingEnabled(false);
        rcv_detail.setFocusable(false);

        // Nhận Intent
        Intent intent = getIntent();
        Product1 product = null;

        if (intent.hasExtra("drink_recycleview")) {
            product = (Product1) intent.getSerializableExtra("drink_recycleview");
        } else if (intent.hasExtra("food_recycleview")) {
            product = (Product1) intent.getSerializableExtra("food_recycleview");
        } else if (intent.hasExtra("dessert_recycleview")) {
            product = (Product1) intent.getSerializableExtra("dessert_recycleview");
        } else if (intent.hasExtra("home_recycleview_product")) {
            product = (Product1) intent.getSerializableExtra("home_recycleview_product");
        }

        if (product != null) {
            img_detail.setImageResource(product.getResourceId());
            txt_name_detail.setText(product.getName());
            txt_price_new_detail.setText(product.getPriceNew());
            if (product.getPriceOld() != null && !product.getPriceOld().isEmpty()) {
                txt_price_old_chitiet.setText(product.getPriceOld());
                txt_price_old_chitiet.setVisibility(View.VISIBLE);
            } else {
                txt_price_old_chitiet.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
            }
        }

        //lắng nghe khi click vào button thêm vào giỏ hàng
        Product1 finalProduct = product;
        btn_them.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickOpenBottomSheetDialog(finalProduct);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private List<Product1> getListImgOther() {
        List<Product1> list = new ArrayList<>();
        list.add(new Product1(R.drawable.imgslider1, "Salad cá hồi", "", "60 000 VND", "40 000 VND", "Giảm 10 %", ""));
        return list;
    }
    private void clickOpenBottomSheetDialog(Product1 product) {
        View viewBottomDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_dialog_chitiet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DetailPageUserActivity.this);
        bottomSheetDialog.setContentView(viewBottomDialog);
        bottomSheetDialog.show();

        btn_huybo = viewBottomDialog.findViewById(R.id.btn_huybo);
        btn_themvaogiohang = viewBottomDialog.findViewById(R.id.btn_themvaogiohang);
        img_chitiet_bottomsheet = viewBottomDialog.findViewById(R.id.img_chitiet_bottomsheet);
        txt_gia_chitiet_bottomsheet = viewBottomDialog.findViewById(R.id.txt_gia_chitiet_bottomsheet);
        txt_ten_chitiet_bottomsheet = viewBottomDialog.findViewById(R.id.txt_ten_chitiet_bottomsheet);

        img_chitiet_bottomsheet.setImageResource(product.getResourceId());
        img_chitiet_bottomsheet.setVisibility(View.VISIBLE);
        txt_gia_chitiet_bottomsheet.setText(product.getName());
        img_chitiet_bottomsheet.setVisibility(View.VISIBLE);
        txt_ten_chitiet_bottomsheet.setText(product.getPriceNew());
        img_chitiet_bottomsheet.setVisibility(View.VISIBLE);

        btn_huybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        btn_themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailPageUserActivity.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}