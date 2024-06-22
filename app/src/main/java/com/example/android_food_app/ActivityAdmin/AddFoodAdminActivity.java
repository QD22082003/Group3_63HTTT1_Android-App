package com.example.android_food_app.ActivityAdmin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddFoodAdminActivity extends AppCompatActivity {
    private EditText edt_name, edt_desc, edt_price, edt_sale;
    private ImageView imgUrl, imgSlider, imgOther;
    private Uri uriMainImage, uriSliderImage, uriOtherImage;
    private String imgMainURL, imgSliderURL, imgOtherURL;
    private RadioButton rad_popular1;
    private Button btn_add;
    private ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_food_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // anh xa
        edt_name = findViewById(R.id.edt_name);
        edt_desc = findViewById(R.id.edt_desc);
        edt_price = findViewById(R.id.edt_price);
        edt_sale = findViewById(R.id.edt_sale);
        imgUrl = findViewById(R.id.imgUrl);
        imgSlider = findViewById(R.id.imgSlider);
        imgOther = findViewById(R.id.imgOther);
        rad_popular1 = findViewById(R.id.rad_popular1);
        btn_add = findViewById(R.id.btn_add);
        imgBack = findViewById(R.id.imgBack);

        // ActivityResultLauncher cho ảnh chính
        ActivityResultLauncher<Intent> mainImageResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uriMainImage = data.getData();
                            imgUrl.setImageURI(uriMainImage);
                        } else {
                            Toast.makeText(AddFoodAdminActivity.this, "Không có hình ảnh nào được chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // ActivityResultLauncher cho ảnh slider
        ActivityResultLauncher<Intent> sliderImageResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uriSliderImage = data.getData();
                            imgSlider.setImageURI(uriSliderImage);
                        } else {
                            Toast.makeText(AddFoodAdminActivity.this, "Không có hình ảnh nào được chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // ActivityResultLauncher cho ảnh khác
        ActivityResultLauncher<Intent> otherImageResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uriOtherImage = data.getData();
                            imgOther.setImageURI(uriOtherImage);
                        } else {
                            Toast.makeText(AddFoodAdminActivity.this, "Không có hình ảnh nào được chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        imgUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                mainImageResultLauncher.launch(photoPicker);
            }
        });

        imgSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                sliderImageResultLauncher.launch(photoPicker);
            }
        });

        imgOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                otherImageResultLauncher.launch(photoPicker);
            }
        });

        // add data
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMainImage();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Hàm upload ảnh chính lên Firebase
    private void saveMainImage() {
        if (uriMainImage == null) {
            Toast.makeText(AddFoodAdminActivity.this, "Vui lòng chọn hình ảnh chính", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Save Images")
                .child(uriMainImage.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(AddFoodAdminActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_admin_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uriMainImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imgMainURL = urlImage.toString();
                saveSliderImage(dialog);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(AddFoodAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm upload ảnh slider lên Firebase
    private void saveSliderImage(AlertDialog dialog) {
        if (uriSliderImage == null) {
            Toast.makeText(AddFoodAdminActivity.this, "Vui lòng chọn hình ảnh slider", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Save Images")
                .child(uriSliderImage.getLastPathSegment());

        storageReference.putFile(uriSliderImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imgSliderURL = urlImage.toString();
                saveOtherImage(dialog);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(AddFoodAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Hàm upload ảnh khác lên Firebase
    private void saveOtherImage(AlertDialog dialog) {
        if (uriOtherImage == null) {
            Toast.makeText(AddFoodAdminActivity.this, "Vui lòng chọn hình ảnh khác", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Save Images")
                .child(uriOtherImage.getLastPathSegment());

        storageReference.putFile(uriOtherImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imgOtherURL = urlImage.toString();
                uploadData(dialog);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(AddFoodAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Hàm upload dữ liệu sản phẩm lên Firebase
    private void uploadData(AlertDialog dialog) {
        try {
            String name = edt_name.getText().toString().trim();
            String desc = edt_desc.getText().toString().trim();
            String price = edt_price.getText().toString().trim();
            String sale = edt_sale.getText().toString().trim();
            Boolean popular  = rad_popular1.isChecked(); // Lấy giá trị từ RadioButton

            // Kiểm tra các trường dữ liệu
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập tên sản phẩm");
            } else if (desc.isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập mô tả sản phẩm");
            } else if (price.isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập giá sản phẩm");
            }

            // Tính priceNew nếu có giảm giá
            double priceOld = Double.parseDouble(price.replaceAll("[^\\d]", ""));
            double priceNew = priceOld; // Giá mặc định sẽ là giá cũ
            if (!sale.isEmpty()) {
                int salePercent = Integer.parseInt(sale.replaceAll("[^\\d]", ""));
                priceNew = priceOld * (100 - salePercent) / 100;
            }
            // Format priceNew để lưu vào Firebase
            DecimalFormat formatter = new DecimalFormat("###,###");
            String priceNewFormatted = formatter.format(priceNew);

            // TH đk đúng, upload dữ liệu
            Product product = new Product(name, desc, price, priceNewFormatted, sale, imgMainURL, imgSliderURL, popular, imgOtherURL);

            // Tạo ID trên thời gian hiện tại
            String id = generateProductId();
            FirebaseDatabase.getInstance().getReference("products").child(id)
                    .setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddFoodAdminActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(AddFoodAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IllegalArgumentException e) {
            dialog.dismiss();
            Toast.makeText(AddFoodAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            dialog.dismiss();
            Toast.makeText(AddFoodAdminActivity.this, "Đã xảy ra lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFoodAdminActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_admin_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    // Hàm tạo ID sản phẩm dựa trên thời gian
    private String generateProductId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
        return sdf.format(new Date());
    }
}
