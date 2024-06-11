package com.example.android_food_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_food_app.ActivityAdmin.TrangChuMonNgon_AdminActivity;
import com.example.android_food_app.ActivityUser.TrangChuUserActivity;
import com.example.android_food_app.R;

public class trangDangNhapActivity extends AppCompatActivity {
    EditText editTextEmailAddress, editTextPassword;
    Button loginButton;
    TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_dang_nhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.loginButton);
        textViewRegister = findViewById(R.id.textViewRegister);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangDangNhapActivity.this, TrangChuUserActivity.class);
                startActivity(intent);
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangDangNhapActivity.this, trangDangKy.class);
                startActivity(intent);
            }
        });

    }
}