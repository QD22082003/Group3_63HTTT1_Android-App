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

import com.example.android_food_app.AdapterUser.ChiTietRecycleViewAdadpter;
import com.example.android_food_app.AdapterUser.DoUongRecycleViewAdapter;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class TrangChiTietUserActivity extends AppCompatActivity {
    private RecyclerView rcv_chitiet;
    private ChiTietRecycleViewAdadpter adapter;
    private ImageView img_back;
    private ImageView img_chi_tiet;
    private TextView txt_tenmon_chitiet;
    private TextView txt_gia_chitiet;
    private TextView txt_gia_cu_chitiet;
    private TextView txt_mota_chitiet;
    private View gach;
    private Button btn_them;
    private Button btn_huybo;
    private Button btn_themvaogiohang;
    private ImageView img_chitiet_bottomsheet;
    private TextView txt_ten_chitiet_bottomsheet, txt_gia_chitiet_bottomsheet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chi_tiet_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcv_chitiet = findViewById(R.id.rcv_chitiet);
        img_back = findViewById(R.id.img_back);
        img_chi_tiet = findViewById(R.id.img_chi_tiet);
        txt_tenmon_chitiet = findViewById(R.id.txt_tenmon_chitiet);
        txt_gia_chitiet = findViewById(R.id.txt_gia_chitiet);
        txt_gia_cu_chitiet = findViewById(R.id.txt_gia_cu_chitiet);
        txt_mota_chitiet = findViewById(R.id.txt_mota_chitiet);
        img_back = findViewById(R.id.img_back);
        gach = findViewById(R.id.gach);
        btn_them = findViewById(R.id.btn_them);



        //khởi tạo adapter
        adapter = new ChiTietRecycleViewAdadpter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_chitiet.setLayoutManager(gridLayoutManager);

        adapter.setDataHinhAnh(getListHinhAnh());
        rcv_chitiet.setAdapter(adapter);
        rcv_chitiet.setNestedScrollingEnabled(false);
        rcv_chitiet.setFocusable(false);

        // Nhận Intent
        Intent intent = getIntent();
        SanPham sanPham = null;

        if (intent.hasExtra("douong")) {
            sanPham = (SanPham) intent.getSerializableExtra("douong");
        } else if (intent.hasExtra("monngon")) {
            sanPham = (SanPham) intent.getSerializableExtra("monngon");
        } else if (intent.hasExtra("trangmieng")) {
            sanPham = (SanPham) intent.getSerializableExtra("trangmieng");
        } else if (intent.hasExtra("trangchu_menu")) {
            sanPham = (SanPham) intent.getSerializableExtra("trangchu_menu");
        }

        if (sanPham != null) {
            img_chi_tiet.setImageResource(sanPham.getImgMonNgonID_Trangchu());
            txt_tenmon_chitiet.setText(sanPham.getTenMon_Trangchu());
            txt_gia_chitiet.setText(sanPham.getGiaMoi_Trangchu());
            if (sanPham.getGiaCu_Trangchu() != null && !sanPham.getGiaCu_Trangchu().isEmpty()) {
                txt_gia_cu_chitiet.setText(sanPham.getGiaCu_Trangchu());
                txt_gia_cu_chitiet.setVisibility(View.VISIBLE);
            } else {
                txt_gia_cu_chitiet.setVisibility(View.GONE);
                gach.setVisibility(View.GONE);
            }
        }

        //lắng nghe khi click vào button thêm vào giỏ hàng
        SanPham finalSanPham = sanPham;
        btn_them.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickOpenBottomSheetDialog(finalSanPham);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private List<SanPham> getListHinhAnh() {
        List<SanPham> list = new ArrayList<>();
        list.add(new SanPham(R.drawable.saladcahoi, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.saladcahoi, "", "Salad cá hồi", "", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.saladcahoi, "Giảm 10%", "Salad cá hồi", "69 000 VNĐ", "59 000 VNĐ"));
        list.add(new SanPham(R.drawable.saladcahoi, "", "Salad cá hồi", "", "59 000 VNĐ"));

        return list;
    }
    private void clickOpenBottomSheetDialog(SanPham sanPham) {
        View viewBottomDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_dialog_chitiet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TrangChiTietUserActivity.this);
        bottomSheetDialog.setContentView(viewBottomDialog);
        bottomSheetDialog.show();

        btn_huybo = viewBottomDialog.findViewById(R.id.btn_huybo);
        btn_themvaogiohang = viewBottomDialog.findViewById(R.id.btn_themvaogiohang);
        img_chitiet_bottomsheet = viewBottomDialog.findViewById(R.id.img_chitiet_bottomsheet);
        txt_gia_chitiet_bottomsheet = viewBottomDialog.findViewById(R.id.txt_gia_chitiet_bottomsheet);
        txt_ten_chitiet_bottomsheet = viewBottomDialog.findViewById(R.id.txt_ten_chitiet_bottomsheet);

        img_chitiet_bottomsheet.setImageResource(sanPham.getImgMonNgonID_Trangchu());
        img_chitiet_bottomsheet.setVisibility(View.VISIBLE);
        txt_gia_chitiet_bottomsheet.setText(sanPham.getTenMon_Trangchu());
        img_chitiet_bottomsheet.setVisibility(View.VISIBLE);
        txt_ten_chitiet_bottomsheet.setText(sanPham.getGiaMoi_Trangchu());
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
                Toast.makeText(TrangChiTietUserActivity.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}