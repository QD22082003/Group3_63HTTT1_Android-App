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

public class HomeAdminAdapter extends RecyclerView.Adapter<HomeAdminAdapter.TrangChuAdminViewHolder> {
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
    public TrangChuAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_user, parent,false);
        return new TrangChuAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrangChuAdminViewHolder holder, int position) {
        Product1 product1 = list.get(position);
        if(product1 == null) {
            return;
        }
        holder.imgMonNgon.setImageResource(product1.getResourceId());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (product1.getSale() != null && !product1.getSale().isEmpty()) {
            holder.phanTram.setText(product1.getSale());
            holder.phanTram.setVisibility(View.VISIBLE);
        } else {
            holder.phanTram.setVisibility(View.GONE);
        }
        holder.txt_ten_mon.setText(product1.getTitle());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (product1.getPriceOld() > 0) {
            holder.giaCu.setText(formatPrice(product1.getPriceOld()));
            holder.giaCu.setVisibility(View.VISIBLE);
            holder.gach.setVisibility(View.VISIBLE);
        } else {
            holder.giaCu.setVisibility(View.GONE);
            holder.gach.setVisibility(View.GONE);
        }
        holder.giaMoi.setText(formatPrice(product1.getPriceNew()));

    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class TrangChuAdminViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonNgon;
        private TextView phanTram;
        private TextView txt_ten_mon;
        private TextView giaCu;
        private TextView giaMoi;

        private View gach;

        public TrangChuAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMonNgon = itemView.findViewById(R.id.img_monngon);
            phanTram = itemView.findViewById(R.id.txt_phan_tram);
            txt_ten_mon = itemView.findViewById(R.id.txt_ten_mon);
            giaCu = itemView.findViewById(R.id.txt_gia_cu);
            giaMoi = itemView.findViewById(R.id.txt_gia_moi);
            gach = itemView.findViewById(R.id.gach);

        }
    }
    // Thêm phương thức định dạng giá
    private String formatPrice(double price) {
        if (price == 0) {
            return "0";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' '); // Sử dụng khoảng trắng làm dấu phân cách nhóm số
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        return decimalFormat.format(price);
    }
}
