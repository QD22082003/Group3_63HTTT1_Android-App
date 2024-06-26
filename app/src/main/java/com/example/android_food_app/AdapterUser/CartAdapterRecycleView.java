package com.example.android_food_app.AdapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;

import java.io.BufferedReader;
import java.util.List;

public class CartAdapterRecycleView extends RecyclerView.Adapter<CartAdapterRecycleView.CartViewHolder> {
    private List<Product> list;

    public CartAdapterRecycleView(List<Product> listCart) {
        this.list = listCart;
    }

    public void setDataSanPham(List<Product> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_user, parent,false);
        return new CartAdapterRecycleView.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = list.get(position);
        if (product == null) {
            return;
        }

        holder.txt_name_cart.setText(product.getName());
        holder.txt_price_cart.setText(product.getPriceNew()); // Đặt giá ban đầu

        // Load ảnh sử dụng Glide
        Glide.with(holder.itemView.getContext())
                .load(product.getImgURL())
                .into(holder.img_cart);

        // Số lượng ban đầu
        holder.txt_count.setText("1");

        // Xử lý sự kiện khi nhấn vào nút "Thêm"
        holder.btn_add.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.txt_count.getText().toString());
            count++;
            holder.txt_count.setText(String.valueOf(count));
            // Cập nhật giá
            double price = Double.parseDouble(product.getPriceNew());
            holder.txt_price_cart.setText(String.format("%.3f VND", price * count));
        });

        // Xử lý sự kiện khi nhấn vào nút "Giảm"
        holder.btn_minus.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.txt_count.getText().toString());
            if (count > 1) {
                count--;
                holder.txt_count.setText(String.valueOf(count));
                // Cập nhật giá
                double price = Double.parseDouble(product.getPriceNew());
                holder.txt_price_cart.setText(String.format("%.3f VND", price * count));
            }
        });

        // Xử lý sự kiện khi nhấn vào nút "Xóa"
        holder.btn_delete_cart.setOnClickListener(v -> {
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        });
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class  CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_cart,btn_minus,btn_add;
        private TextView txt_name_cart, txt_price_cart,txt_count;
        private Button btn_delete_cart;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cart = itemView.findViewById(R.id.img_cart);
            btn_minus = itemView.findViewById(R.id.btn_minus);
            btn_add = itemView.findViewById(R.id.btn_add);
            txt_name_cart = itemView.findViewById(R.id.txt_name_cart);
            txt_price_cart = itemView.findViewById(R.id.txt_price_cart);
            txt_count = itemView.findViewById(R.id.txt_count);
            btn_delete_cart = itemView.findViewById(R.id.btn_delete_cart);
        }
    }
}