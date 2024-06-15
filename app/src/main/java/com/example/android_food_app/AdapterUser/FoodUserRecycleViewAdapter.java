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

public class FoodUserRecycleViewAdapter extends RecyclerView.Adapter<FoodUserRecycleViewAdapter.FoodViewHolder> {
    private List<Product1> list_food;
    private Context mContext;

    public FoodUserRecycleViewAdapter(List<Product1> list_food, Context mContext) {
        this.list_food = list_food;
        this.mContext = mContext;
    }

    public void setDataFood(List<Product1> list_food) {
        this.list_food = list_food;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent,false);
        return new FoodUserRecycleViewAdapter.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Product1 food = list_food.get(position);
        if(food == null) {
            return;
        }
        holder.img_food.setImageResource(food.getResourceId());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (food.getSale() != null && !food.getSale().isEmpty()) {
            holder.txt_sale.setText(food.getSale());
            holder.txt_sale.setVisibility(View.VISIBLE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }
        holder.txt_name.setText(food.getName());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (food.getPriceOld() != null && !food.getPriceOld().isEmpty()) {
            holder.txt_price_old.setText(food.getPriceOld());
            holder.txt_price_old.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
        holder.txt_price_new.setText(food.getPriceNew());

        //thiết lập click vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailPageUserActivity.class);
                intent.putExtra("food_recycleview", food); //đảm bảo dữ liệu của sp có thể được truyền vào intent phải khai báo Serializable
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(list_food != null) {
            return list_food.size();
        }
        return 0;
    }

    public  class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_food;
        private TextView txt_sale;
        private TextView txt_name;
        private TextView txt_price_old;
        private TextView txt_price_new;

        private View line;
        public FoodViewHolder(@NonNull View itemView) {

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
