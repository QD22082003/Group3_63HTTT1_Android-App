package com.example.android_food_app.AdapterAdmin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_food_app.ActivityAdmin.DetailProductAdminActivity;
import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;


import java.text.DecimalFormat;
import java.util.List;

public class FoodAdminAdapter extends RecyclerView.Adapter<FoodAdminAdapter.FoodViewHolder> {

    private Context mContext;
    private List<Product> mListProduct;


    public FoodAdminAdapter(Context mContext, List<Product> mListProduct) {
        this.mContext = mContext;
        this.mListProduct = mListProduct;
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
        holder.txt_name.setText(mListProduct.get(position).getName());
        holder.txt_desc.setText(mListProduct.get(position).getDesc());
        holder.txt_sale.setText(mListProduct.get(position).getSale());
        holder.txt_price_old.setText(mListProduct.get(position).getPriceOld());
        holder.txt_popular.setText(mListProduct.get(position).getPopular() ? "Có" : "Không");

        try {
            String priceOldStr = product.getPriceOld().replaceAll("[^\\d]", ""); // Remove non-numeric characters
            double priceOld = Double.parseDouble(priceOldStr);

            // Check if sale is provided
            if (!product.getSale().isEmpty()) {
                int salePercent = Integer.parseInt(product.getSale().replaceAll("[^\\d]", ""));
                double priceNew = priceOld * (100 - salePercent) / 100;

                DecimalFormat formatter = new DecimalFormat("###,###");
                String priceNewFormatted = formatter.format(priceNew);
                holder.txt_price_new.setText(priceNewFormatted);
                holder.line.setVisibility(View.VISIBLE);
            } else {
                holder.txt_price_new.setVisibility(View.GONE);
                holder.line.setVisibility(View.GONE);
                holder.txt_sale.setVisibility(View.GONE);
                holder.txt_sale0.setVisibility(View.GONE);
                holder.txt_sale1.setVisibility(View.GONE);
                holder.txt_title_price_new.setVisibility(View.GONE);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Product product = mListProduct.get(position);

                Intent intent = new Intent(mContext, DetailProductAdminActivity.class);
                intent.putExtra("imgUrl", product.getImgURL());
                intent.putExtra("imgDetail", product.getImgURLOther());
                intent.putExtra("name", product.getName());
                intent.putExtra("desc", product.getDesc());
                intent.putExtra("price", product.getPriceOld());
                intent.putExtra("sale", product.getSale());
                intent.putExtra("popular", product.getPopular());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_name, txt_desc, txt_sale, txt_price_old, txt_price_new, txt_popular, txt_sale0, txt_sale1, txt_title_price_new;
        private CardView recCard;
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
            recCard = itemView.findViewById(R.id.recCard);

            line = itemView.findViewById(R.id.line);
            txt_sale0 = itemView.findViewById(R.id.txt_sale0);
            txt_sale1 = itemView.findViewById(R.id.txt_sale1);
            txt_title_price_new = itemView.findViewById(R.id.txt_title_price_new);
        }
    }


}

