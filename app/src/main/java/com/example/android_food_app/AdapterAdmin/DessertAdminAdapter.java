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

import com.example.android_food_app.Model.Product;
import com.example.android_food_app.R;

import java.util.List;

public class DessertAdminAdapter extends RecyclerView.Adapter<DessertAdminAdapter.DessertViewHolder> {
    private Context mContext;
    private List<Product> mListProduct;

    public DessertAdminAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Product> list) {
        this.mListProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin, parent, false);
        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }

        // Chuyển đổi byte array sang Bitmap và set vào ImageView
        if (product.getImgUrl() != null) {
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(product.getImgUrl(), 0, product.getImgUrl().length);
            holder.imgUrl.setImageBitmap(bitmap1);
        } else {
            holder.imgUrl.setImageResource(R.drawable.imgslider1); // Placeholder image if no image is available
        }

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

        // Kiểm tra hiển thị phần giá cũ khi có giảm giá
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

    public class DessertViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUrl;
        private TextView txt_name, txt_price_old, txt_price_new, txt_popular, txt_desc;

        private TextView txt_sale;
        private View line;
        private ImageView img_edit, img_trash;

        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUrl = itemView.findViewById(R.id.imgUrl);
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


