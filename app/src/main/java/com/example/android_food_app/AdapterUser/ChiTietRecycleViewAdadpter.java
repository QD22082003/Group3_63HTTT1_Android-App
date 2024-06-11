package com.example.android_food_app.AdapterUser;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;

import java.util.List;

public class ChiTietRecycleViewAdadpter extends RecyclerView.Adapter<ChiTietRecycleViewAdadpter.ChiTietViewHolder>{
    private List<SanPham> listhinhanhkhac;
    public void setDataHinhAnh(List<SanPham> listhinhanhkhac) {
        this.listhinhanhkhac = listhinhanhkhac;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChiTietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent,false);
        return new ChiTietRecycleViewAdadpter.ChiTietViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(listhinhanhkhac != null) {
            return listhinhanhkhac.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietViewHolder holder, int position) {
        SanPham monngon = listhinhanhkhac.get(position);
        if(monngon == null) {
            return;
        }
        holder.imgMonNgon.setImageResource(monngon.getImgMonNgonID_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (monngon.getPhanTram_Trangchu() != null && !monngon.getPhanTram_Trangchu().isEmpty()) {
            holder.phanTram.setText(monngon.getPhanTram_Trangchu());
            holder.phanTram.setVisibility(View.GONE);
        } else {
            holder.phanTram.setVisibility(View.GONE);
        }
        holder.txt_ten_mon.setText(monngon.getTenMon_Trangchu());
        holder.txt_ten_mon.setVisibility(View.GONE);
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (monngon.getGiaCu_Trangchu() != null && !monngon.getGiaCu_Trangchu().isEmpty()) {
            holder.giaCu.setText(monngon.getGiaCu_Trangchu());
            holder.giaCu.setVisibility(View.GONE);
            holder.gach.setVisibility(View.GONE);
        } else {
            holder.giaCu.setVisibility(View.GONE);
            holder.gach.setVisibility(View.GONE);
        }
        holder.giaMoi.setText(monngon.getGiaMoi_Trangchu());
        holder.giaMoi.setVisibility(View.GONE);

    }

    public class ChiTietViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonNgon;
        private TextView phanTram;
        private TextView txt_ten_mon;
        private TextView giaCu;
        private TextView giaMoi;
        private View gach;

        public ChiTietViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMonNgon = itemView.findViewById(R.id.img_monngon);
            phanTram = itemView.findViewById(R.id.txt_phan_tram);
            txt_ten_mon = itemView.findViewById(R.id.txt_ten_mon);
            giaCu = itemView.findViewById(R.id.txt_gia_cu);
            giaMoi = itemView.findViewById(R.id.txt_gia_moi);
            gach = itemView.findViewById(R.id.gach);
        }
    }
}
