package com.example.android_food_app.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.List;

public class DrinkAdminAdapter extends RecyclerView.Adapter<DrinkAdminAdapter.DrinkViewHolder> {

    private Context mContext;
    private List<Product1> mListProduct1;

    public DrinkAdminAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Product1> list) {
        this.mListProduct1 = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Product1 product1 = mListProduct1.get(position);
        if (product1 == null) {
            return;
        }

        holder.img.setImageResource(product1.getResourceId());
        holder.txt_name.setText(product1.getName());

        holder.txt_price_old.setText(product1.getPriceOld());
        holder.txt_price_new.setText(product1.getPriceNew());

        holder.txt_popular.setText(product1.getPopular());
        holder.txt_desc.setText(product1.getDesc());

        // Kiểm tra hiển thị phần giảm giá khi có
        if (product1.getSale() != null && !product1.getSale().isEmpty()) {
            holder.txt_sale.setText(product1.getSale());
            holder.txt_sale.setVisibility(View.VISIBLE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }

        // Kiểm tra hiển thị phần giá cũ khi có giảm giá
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
        if (mListProduct1 != null) {
            return mListProduct1.size();
        }
        return 0;
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt_name, txt_price_old, txt_price_new, txt_popular, txt_desc;
        private TextView txt_sale;
        private View line;
        private ImageView img_edit, img_trash;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_price_old = itemView.findViewById(R.id.txt_price_old);
            txt_price_new = itemView.findViewById(R.id.txt_price_new);
            txt_popular = itemView.findViewById(R.id.txt_popular);
            txt_desc = itemView.findViewById(R.id.txt_desc);


            txt_sale = itemView.findViewById(R.id.txt_sale);
            line = itemView.findViewById(R.id.line);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_trash = itemView.findViewById(R.id.img_trash);
        }
    }


}

