package com.example.android_food_app.AdapterUser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.ActivityUser.DetailPageUserActivity;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;

import java.util.List;

public class DessertUserRecycleViewAdapter extends RecyclerView.Adapter<DessertUserRecycleViewAdapter.DessertViewHolder >{
    private List<Product1> list_dessert;
    private Context mContext;

    public DessertUserRecycleViewAdapter(List<Product1> list_dessert, Context mContext) {
        this.list_dessert = list_dessert;
        this.mContext = mContext;
    }

    public void setDataDessert(List<Product1> list_dessert) {
        this.list_dessert = list_dessert;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent,false);
        return new DessertUserRecycleViewAdapter.DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertViewHolder holder, int position) {
        Product1 dessert = list_dessert.get(position);
        if(dessert == null) {
            return;
        }
        holder.img_food.setImageResource(dessert.getResourceId());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (dessert.getSale() != null && !dessert.getSale().isEmpty()) {
            holder.txt_sale.setText(dessert.getSale());
            holder.txt_sale.setVisibility(View.VISIBLE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }
        holder.txt_name.setText(dessert.getName());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (dessert.getPriceOld() != null && !dessert.getPriceOld().isEmpty()) {
            holder.txt_price_old.setText(dessert.getPriceOld());
            holder.txt_price_old.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
        holder.txt_price_new.setText(dessert.getPriceNew());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDoUong = new Intent(mContext, DetailPageUserActivity.class);
                intentDoUong.putExtra("dessert_recycleview", dessert); //đảm bảo dữ liệu của sp có thể được truyền vào intent phải khai báo Serializable
                mContext.startActivity(intentDoUong);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list_dessert != null) {
            return  list_dessert.size();
        }
        return 0;
    }

    public class  DessertViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_food;
        private TextView txt_sale;
        private TextView txt_name;
        private TextView txt_price_old;
        private TextView txt_price_new;

        private View line;
        public DessertViewHolder(@NonNull View itemView) {
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
