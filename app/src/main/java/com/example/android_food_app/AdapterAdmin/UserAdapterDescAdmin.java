package com.example.android_food_app.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Model.User;
import com.example.android_food_app.R;

import java.util.List;

public class UserAdapterDescAdmin extends RecyclerView.Adapter<UserAdapterDescAdmin.UserViewHolder> {
    private Context mContext;
    private List<User> mListUser;

    public UserAdapterDescAdmin(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<User> list) {
        this.mListUser = list;
        notifyDataSetChanged(); // load du lieu vao useradapter
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_desc_admin, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUser.get(position);
        if (user == null) {
            return;
        }

        holder.imgUser.setImageResource(user.getResourceId());
        holder.txtName.setText(user.getName());
        holder.txtDesc.setText(user.getDesc());
    }

    @Override
    public int getItemCount() {
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    // anh xa id
    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUser;
        private TextView txtName, txtDesc;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            txtName = itemView.findViewById(R.id.txtName);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }

    }
}
