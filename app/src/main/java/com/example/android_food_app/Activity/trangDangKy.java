package com.example.android_food_app.Activity;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_food_app.ActivityUser.TrangChuUserActivity;
import com.example.android_food_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class trangDangKy extends AppCompatActivity {
    Button registerButton;
    TextView textViewLogin;
    EditText inputEmail, inputPassword, inputPhone, inputAddress, inputName, inputPassword2;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_dang_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        registerButton = findViewById(R.id.registerButton);
        textViewLogin = findViewById(R.id.textViewLogin);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (inputPassword.getRight() - inputPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Xử lý khi nhấn vào biểu tượng mắt
                        if (inputPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
                            // Hiển thị mật khẩu
                            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        } else {
                            // Ẩn mật khẩu
                            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        inputPassword2 = findViewById(R.id.inputPassword2);
        inputPassword2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (inputPassword2.getRight() - inputPassword2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Xử lý khi nhấn vào biểu tượng mắt
                        if (inputPassword2.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
                            // Hiển thị mật khẩu
                            inputPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        } else {
                            // Ẩn mật khẩu
                            inputPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        progressDialog = new ProgressDialog(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDangKy();
            }
        });
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangDangKy.this, trangDangNhapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onClickDangKy() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String password2 = inputPassword2.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            Toast.makeText(trangDangKy.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(password2)) {
            Toast.makeText(trangDangKy.this, "Mật khẩu xác nhận không trùng khớp.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Lưu thông tin người dùng vào Realtime Database
                            FirebaseUser user = auth.getCurrentUser();
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("email", email);
                            userInfo.put("role", 1); // Mặc định là user

                            dbRef.child("users").child(user.getUid()).setValue(userInfo)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d(TAG, "User profile created for " + user.getUid());
                                        Intent intent = new Intent(trangDangKy.this, TrangChuUserActivity.class);
                                        startActivity(intent);
                                    })
                                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(trangDangKy.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
