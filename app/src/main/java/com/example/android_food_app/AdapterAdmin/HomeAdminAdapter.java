package com.example.android_food_app.AdapterAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class HomeAdminAdapter extends RecyclerView.Adapter<HomeAdminAdapter.HomeAdminViewHolder> {

    private List<Product1> list;
    private FragmentActivity activity;

    public HomeAdminAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    public void setData(List<Product1> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin2, parent, false);
        return new HomeAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdminViewHolder holder, int position) {
        Product1 product1 = list.get(position);
        if (product1 == null) {
            return;
        }

        holder.img_food.setImageResource(product1.getResourceId());
        holder.txt_name.setText(product1.getName());

        holder.txt_price_old.setText(product1.getPriceOld());
        holder.txt_price_new.setText(product1.getPriceNew());


        // Kiểm tra và hiển thị phần giảm giá khi có
        if (product1.getSale() != null && !product1.getSale().isEmpty()) {
            holder.txt_sale.setText(product1.getSale());
            holder.txt_sale.setVisibility(View.VISIBLE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }

        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (product1.getPriceOld() != null && !product1.getPriceOld().isEmpty() && !product1.getPriceOld().equals("0")) {
            holder.txt_price_old.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class HomeAdminViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_food;
        private TextView txt_sale, txt_name, txt_price_old, txt_price_new;
        private View line;

        public HomeAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            img_food = itemView.findViewById(R.id.img_food);
            txt_sale = itemView.findViewById(R.id.txt_sale);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_price_old = itemView.findViewById(R.id.txt_price_old);
            txt_price_new = itemView.findViewById(R.id.txt_price_new);
            line = itemView.findViewById(R.id.line);
        }
    }


}

