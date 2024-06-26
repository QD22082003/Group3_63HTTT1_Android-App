package com.example.android_food_app.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.Model.Order;
import com.example.android_food_app.Model.User;
import com.example.android_food_app.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class OrderAdminAdapter extends RecyclerView.Adapter<OrderAdminAdapter.OrderViewHolder> {

    private Context mContext;
    private List<Order> mListOrder;

    public OrderAdminAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Order> list) {
        this.mListOrder = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderAdminAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_admin, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdminAdapter.OrderViewHolder holder, int position) {
        Order order = mListOrder.get(position);
        if (order == null) {
            return;
        }

        holder.txt_id_order.setText(order.getId());
        holder.txt_name_order.setText(order.getName());
        holder.txt_phone_order.setText(order.getPhone());
        holder.txt_address_order.setText(order.getAddress());
        holder.txt_date_order.setText(order.getDate());
        holder.txt_total_order.setText(formatPrice(order.getTotal()));
    }

    @Override
    public int getItemCount() {
        if (mListOrder != null) {
            return mListOrder.size();
        }
        return 0;
    }

    // anh xa id
    public class  OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_id_order;
        public TextView txt_email_order;
        public TextView txt_name_order;
        public TextView txt_phone_order;
        public TextView txt_address_order;
        public TextView txt_menu_order;
        public TextView txt_date_order;
        public TextView txt_total_order;
        public TextView txt_pay_order;
        private RadioButton rad_total_order;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id_order = itemView.findViewById(R.id.txt_id_order);
            txt_email_order = itemView.findViewById(R.id.txt_email_order);
            txt_name_order = itemView.findViewById(R.id.txt_name_order);
            txt_phone_order = itemView.findViewById(R.id.txt_phone_order);
            txt_address_order = itemView.findViewById(R.id.txt_address_order);
            txt_menu_order = itemView.findViewById(R.id.txt_menu_order);
            txt_date_order = itemView.findViewById(R.id.txt_date_order);
            txt_total_order = itemView.findViewById(R.id.txt_total_order);
            txt_pay_order = itemView.findViewById(R.id.txt_pay_order);

            rad_total_order = itemView.findViewById(R.id.rad_total_order);
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
