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

import com.example.android_food_app.ActivityUser.TrangChiTietUserActivity;
import com.example.android_food_app.ModelUser.SanPham;
import com.example.android_food_app.R;

import java.util.List;

public class DoUongRecycleViewAdapter extends RecyclerView.Adapter<DoUongRecycleViewAdapter.DoUongViewHolder> {
    private List<SanPham> listdouong;
    private Context mContext;

    public DoUongRecycleViewAdapter(List<SanPham> listdouong, Context mContext) {
        this.listdouong = listdouong;
        this.mContext = mContext;
    }

    public void setDataDoUong(List<SanPham> listdouong) {
        this.listdouong = listdouong;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DoUongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent,false);
        return new DoUongRecycleViewAdapter.DoUongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoUongViewHolder holder, int position) {
        SanPham douong = listdouong.get(position);
        if(douong == null) {
            return;
        }
        holder.imgMonNgon.setImageResource(douong.getImgMonNgonID_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (douong.getPhanTram_Trangchu() != null && !douong.getPhanTram_Trangchu().isEmpty()) {
            holder.phanTram.setText(douong.getPhanTram_Trangchu());
            holder.phanTram.setVisibility(View.VISIBLE);
        } else {
            holder.phanTram.setVisibility(View.GONE);
        }
        holder.txt_ten_mon.setText(douong.getTenMon_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (douong.getGiaCu_Trangchu() != null && !douong.getGiaCu_Trangchu().isEmpty()) {
            holder.giaCu.setText(douong.getGiaCu_Trangchu());
            holder.giaCu.setVisibility(View.VISIBLE);
            holder.gach.setVisibility(View.VISIBLE);
        } else {
            holder.giaCu.setVisibility(View.GONE);
            holder.gach.setVisibility(View.GONE);
        }
        holder.giaMoi.setText(douong.getGiaMoi_Trangchu());
        //thiết lập click vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDoUong = new Intent(mContext, TrangChiTietUserActivity.class);
                intentDoUong.putExtra("douong", douong); //đảm bảo dữ liệu của sp có thể được truyền vào intent phải khai báo Serializable
                mContext.startActivity(intentDoUong);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(listdouong != null) {
            return listdouong.size();
        }
        return 0;
    }

    public class DoUongViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonNgon;
        private TextView phanTram;
        private TextView txt_ten_mon;
        private TextView giaCu;
        private TextView giaMoi;

        private View gach;
        public DoUongViewHolder(@NonNull View itemView) {
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
