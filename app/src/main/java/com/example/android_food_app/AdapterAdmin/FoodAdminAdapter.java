package com.example.android_food_app.AdapterAdmin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;


import java.util.List;

public class FoodAdminAdapter extends RecyclerView.Adapter<FoodAdminAdapter.FoodViewHolder> {

    private Context mContext;
    private List<Product> mListProduct;

//    public FoodAdminAdapter(List<Product> mListProduct) {
//        this.mListProduct = mListProduct;
//    }

//    public FoodAdminAdapter(Context mContext) {
//        this.mContext = mContext;
//    }

    public FoodAdminAdapter(Context mContext, List<Product> mListProduct) {
        this.mContext = mContext;
        this.mListProduct = mListProduct;
    }

    public void setData(List<Product> list) {
        this.mListProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }

        Glide.with(mContext).load(mListProduct.get(position).getImgURL()).into(holder.imgUrl);

        holder.txt_name.setText(product.getName());
        holder.txt_price_old.setText(product.getPriceOld());
        holder.txt_price_new.setText(product.getPriceNew());
        holder.txt_popular.setText(product.getPopular() ? "Có" : "Không");
        holder.txt_desc.setText(product.getDesc());

        // Kiểm tra hiển thị phần giảm giá khi có
        if (product.getSale() != null && !product.getSale().isEmpty()) {
            holder.txt_sale.setText(product.getSale());
            holder.txt_sale.setVisibility(View.VISIBLE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }

//        // Kiểm tra hiển thị phần giá cũ khi có giảm giá
        if (product.getPriceOld() != null && !product.getPriceOld().isEmpty() && !product.getPriceOld().equals("0")) {
            holder.txt_price_old.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_name, txt_desc, txt_price_old, txt_price_new, txt_popular;
        private TextView txt_sale;
        private ImageView imgUrl;
        private View line;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_price_old = itemView.findViewById(R.id.txt_price_old);
            txt_sale = itemView.findViewById(R.id.txt_sale);
            txt_price_new = itemView.findViewById(R.id.txt_price_new);
            txt_popular = itemView.findViewById(R.id.txt_popular);
            imgUrl = itemView.findViewById(R.id.imgUrl);

            line = itemView.findViewById(R.id.line);
        }
    }


}

