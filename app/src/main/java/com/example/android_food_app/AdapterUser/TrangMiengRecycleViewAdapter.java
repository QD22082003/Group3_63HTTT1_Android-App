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

public class TrangMiengRecicleViewAdapter extends RecyclerView.Adapter<TrangMiengRecicleViewAdapter.TrangMiengViewHolder >{
    private List<SanPham> listtrangmieng;
    private Context mContext;

    public TrangMiengRecicleViewAdapter(List<SanPham> listtrangmieng, Context mContext) {
        this.listtrangmieng = listtrangmieng;
        this.mContext = mContext;
    }

    public void setDataTrangMieng(List<SanPham> listtrangmieng) {
        this.listtrangmieng = listtrangmieng;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TrangMiengViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent,false);
        return new TrangMiengRecicleViewAdapter.TrangMiengViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrangMiengViewHolder holder, int position) {
        SanPham trangmieng = listtrangmieng.get(position);
        if(trangmieng == null) {
            return;
        }
        holder.imgMonNgon.setImageResource(trangmieng.getImgMonNgonID_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (trangmieng.getPhanTram_Trangchu() != null && !trangmieng.getPhanTram_Trangchu().isEmpty()) {
            holder.phanTram.setText(trangmieng.getPhanTram_Trangchu());
            holder.phanTram.setVisibility(View.VISIBLE);
        } else {
            holder.phanTram.setVisibility(View.GONE);
        }
        holder.txt_ten_mon.setText(trangmieng.getTenMon_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (trangmieng.getGiaCu_Trangchu() != null && !trangmieng.getGiaCu_Trangchu().isEmpty()) {
            holder.giaCu.setText(trangmieng.getGiaCu_Trangchu());
            holder.giaCu.setVisibility(View.VISIBLE);
            holder.gach.setVisibility(View.VISIBLE);
        } else {
            holder.giaCu.setVisibility(View.GONE);
            holder.gach.setVisibility(View.GONE);
        }
        holder.giaMoi.setText(trangmieng.getGiaMoi_Trangchu());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDoUong = new Intent(mContext, TrangChiTietUserActivity.class);
                intentDoUong.putExtra("trangmieng", trangmieng); //đảm bảo dữ liệu của sp có thể được truyền vào intent phải khai báo Serializable
                mContext.startActivity(intentDoUong);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listtrangmieng != null) {
            return  listtrangmieng.size();
        }
        return 0;
    }

    public class  TrangMiengViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonNgon;
        private TextView phanTram;
        private TextView txt_ten_mon;
        private TextView giaCu;
        private TextView giaMoi;
        private View gach;

        public TrangMiengViewHolder(@NonNull View itemView) {
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
