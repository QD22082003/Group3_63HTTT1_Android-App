package com.example.android_food_app.AdapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;

import java.util.List;

public class DetailUserAdadpter extends RecyclerView.Adapter<DetailUserAdadpter.DetailViewHolder>{
    private List<Product1> listProductOther;
    public void setDataImgOther(List<Product1> listProductOther) {
        this.listProductOther = listProductOther;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent,false);
        return new DetailUserAdadpter.DetailViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(listProductOther != null) {
            return listProductOther.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        Product1 imgother  = listProductOther.get(position);
        if(imgother == null) {
            return;
        }
        holder.img_food.setImageResource(imgother.getResourceId());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (imgother.getSale() != null && !imgother.getSale().isEmpty()) {
            holder.txt_sale.setText(imgother.getSale());
            holder.txt_sale.setVisibility(View.GONE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }
        holder.txt_name.setText(imgother.getName());
        holder.txt_name.setVisibility(View.GONE);
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (imgother.getPriceOld() != null && !imgother.getPriceOld().isEmpty()) {
            holder.txt_price_old.setText(imgother.getPriceOld());
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        } else {
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
        holder.txt_price_new.setText(imgother.getPriceNew());
        holder.txt_price_new.setVisibility(View.GONE);

    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_food;
        private TextView txt_sale;
        private TextView txt_name;
        private TextView txt_price_old;
        private TextView txt_price_new;

        private View line;
        public DetailViewHolder(@NonNull View itemView) {
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
