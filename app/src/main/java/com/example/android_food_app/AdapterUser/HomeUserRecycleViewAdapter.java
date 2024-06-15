package com.example.android_food_app.AdapterUser;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.ActivityUser.DetailPageUserActivity;
import com.example.android_food_app.Model.Product1;
import com.example.android_food_app.R;

import java.util.List;

public class HomeUserRecycleViewAdapter extends RecyclerView.Adapter<HomeUserRecycleViewAdapter.ProductViewHolder> {
    private List<Product1> list;
    private FragmentActivity activity;


    public HomeUserRecycleViewAdapter(FragmentActivity activity) {
        this.activity = activity;

    }


    public void setDataProduct(List<Product1> list) {
        this.list = list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product1 product = list.get(position);
        if(product == null) {
            return;
        }
        holder.img_food.setImageResource(product.getResourceId());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (product.getSale() != null && !product.getSale().isEmpty()) {
            holder.txt_sale.setText(product.getSale());
            holder.txt_sale.setVisibility(View.VISIBLE);
        } else {
            holder.txt_sale.setVisibility(View.GONE);
        }
        holder.txt_name.setText(product.getName());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (product.getPriceOld() != null && !product.getPriceOld().isEmpty()) {
            holder.txt_price_old.setText(product.getPriceOld());
            holder.txt_price_old.setVisibility(View.VISIBLE);
            holder.txt_price_old.setVisibility(View.VISIBLE);
        } else {
            holder.txt_price_old.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
        holder.txt_price_new.setText(product.getPriceNew());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrangchu = new Intent(activity, DetailPageUserActivity.class);
                intentTrangchu.putExtra("home_recycleview_product", product); //đảm bảo dữ liệu của sp có thể được truyền vào intent phải khai báo Serializable
                activity.startActivity(intentTrangchu);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_food;
        private TextView txt_sale;
        private TextView txt_name;
        private TextView txt_price_old;
        private TextView txt_price_new;

        private View line;

        public ProductViewHolder(@NonNull View itemView) {
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
