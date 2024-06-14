package com.example.android_food_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_food_app.R;

public class OpenPageActivity extends AppCompatActivity {
    Button loginButton;
    TextView textViewRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.loginButton);
        textViewRegister = findViewById(R.id.textViewRegister);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OpenPageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finish current activity so it's not in the back stack
            }
        }, 5000);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpenPageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpenPageActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}