package com.example.android_food_app.Fragment.FragmentBottomNavigationUser;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_food_app.Model.Feedback;
import com.example.android_food_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FeedbackUserFragment extends Fragment {

    private EditText comment, email;
    private Button btnAddFeedback;
    private DatabaseReference counterRef;
    private ProgressDialog progressDialog;

    public FeedbackUserFragment() {
        // Required empty public constructor
    }

    public static FeedbackUserFragment newInstance(String param1, String param2) {
        FeedbackUserFragment fragment = new FeedbackUserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // handle arguments
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback_user, container, false);

        comment = view.findViewById(R.id.comment);
        email = view.findViewById(R.id.email);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Đang kiểm tra...");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            email.setText(userEmail);
        }
        btnAddFeedback = view.findViewById(R.id.btn_add_feedback);

        // Tham chiếu tới counter trong Firebase
        counterRef = FirebaseDatabase.getInstance().getReference("counter");

        btnAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeedback();
            }
        });

        return view;
    }

    private void addFeedback() {
        String userComment = comment.getText().toString().trim();

        if (userComment.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference feedbacksRef = FirebaseDatabase.getInstance().getReference("feedbacks");

            // Lấy giá trị counter hiện tại và tăng lên
            counterRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Long counter = snapshot.getValue(Long.class);
                    if (counter == null) {
                        counter = 0L;
                    }
                    long newId = counter + 1;

                    // Cập nhật giá trị counter mới
                    counterRef.setValue(newId);

                    // Tạo đối tượng feedback với ID mới
                    Feedback feedback = new Feedback(String.valueOf(newId), userId, userComment);

                    // Lưu phản hồi vào Firebase với ID mới
                    feedbacksRef.child(String.valueOf(newId)).setValue(feedback).addOnCompleteListener(task -> {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Phản hồi đã được gửi", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Gửi phản hồi thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Lỗi khi cập nhật phản hồi", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "Người dùng không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
