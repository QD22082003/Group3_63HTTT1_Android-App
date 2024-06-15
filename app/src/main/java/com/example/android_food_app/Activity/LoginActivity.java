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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_food_app.ActivityAdmin.HomeAdminActivity;
import com.example.android_food_app.ActivityUser.HomeUserActivity;
import com.example.android_food_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    Button loginButton;
    TextView textViewRegister, textForgotPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Thiết lập EdgeToEdge nếu cần thiết (loại bỏ nếu không sử dụng)
        // EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.loginButton);
        textViewRegister = findViewById(R.id.textViewRegister);
        textForgotPassword = findViewById(R.id.textForgotPassword);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        // Bắt sự kiện khi người dùng nhấn vào biểu tượng mắt
        inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (inputPassword.getRight() - inputPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
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

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang kiểm tra...");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDangNhap();
            }
        });

        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onClickDangNhap() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ email và mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                            dbRef.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        Long role = snapshot.child("role").getValue(Long.class);
                                        if (role != null && role == 0) { // role == 0 là admin
                                            Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Intent intent = new Intent(LoginActivity.this, HomeUserActivity.class);
                                            startActivity(intent);
                                        }
                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d(TAG, "get failed with ", error.toException());
                                }
                            });
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin đăng nhập.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
