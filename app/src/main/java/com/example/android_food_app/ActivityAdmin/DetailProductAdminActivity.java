package com.example.android_food_app.ActivityAdmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.android_food_app.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailProductAdminActivity extends AppCompatActivity {
    private TextView detail_name, detail_desc, detail_price, detail_sale, detail_popular;
    private ImageView detail_img, detail_imgOther;
    private Button btn_back;
    private FloatingActionButton delete_btn;
    private String key = "";
    private String imgUrl = "";
    private String imgOtherUrl = "";
    private String imgSliderUrl = "";

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
        delete_btn = findViewById(R.id.delete_btn);

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
            key = bundle.getString("Key");
            imgUrl = bundle.getString("imgUrl");
            imgOtherUrl = bundle.getString("imgOther");
            imgSliderUrl = bundle.getString("imgSlider");
            Glide.with(this).load(bundle.getString("imgUrl")).into(detail_img);
            Glide.with(this).load(bundle.getString("imgOther")).into(detail_imgOther);
        }

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });

    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteProduct();
                    }
                })
                .setNegativeButton("Không", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    // Phương thức xóa sản phẩm và các ảnh liên quan từ Firebase Storage và Realtime Database
    private void deleteProduct() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReferenceMain = storage.getReferenceFromUrl(imgUrl);
        StorageReference storageReferenceOther = storage.getReferenceFromUrl(imgOtherUrl);
        StorageReference storageReferenceSlider = storage.getReferenceFromUrl(imgSliderUrl);

        // Xóa imgUrl
        storageReferenceMain.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                // Xóa imgOther
                storageReferenceOther.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // Xóa imgSlider
                        storageReferenceSlider.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                // Nếu xóa thành công, xóa dữ liệu sản phẩm khỏi Realtime Database
                                databaseReference.child(key).removeValue();
                                Toast.makeText(DetailProductAdminActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), FoodPageAdminActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailProductAdminActivity.this, "Xóa ảnh slider thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailProductAdminActivity.this, "Xóa ảnh phụ thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DetailProductAdminActivity.this, "Xóa ảnh chính thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}