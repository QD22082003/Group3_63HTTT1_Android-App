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

public class MonNgonRecycleViewAdapter extends RecyclerView.Adapter<MonNgonRecycleViewAdapter.MonNgonViewHolder> {
    private List<SanPham> listmonngon;
    private Context mContext;

    public MonNgonRecycleViewAdapter(List<SanPham> listmonngon, Context mContext) {
        this.listmonngon = listmonngon;
        this.mContext = mContext;
    }

    public void setDataMonngon(List<SanPham> listmonngon) {
        this.listmonngon = listmonngon;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonNgonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent,false);
        return new MonNgonRecycleViewAdapter.MonNgonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonNgonViewHolder holder, int position) {
        SanPham monngon = listmonngon.get(position);
        if(monngon == null) {
            return;
        }
        holder.imgMonNgon.setImageResource(monngon.getImgMonNgonID_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá khi có
        if (monngon.getPhanTram_Trangchu() != null && !monngon.getPhanTram_Trangchu().isEmpty()) {
            holder.phanTram.setText(monngon.getPhanTram_Trangchu());
            holder.phanTram.setVisibility(View.VISIBLE);
        } else {
            holder.phanTram.setVisibility(View.GONE);
        }
        holder.txt_ten_mon.setText(monngon.getTenMon_Trangchu());
        // Kiểm tra và hiển thị phần giảm giá và giá cũ khi có
        if (monngon.getGiaCu_Trangchu() != null && !monngon.getGiaCu_Trangchu().isEmpty()) {
            holder.giaCu.setText(monngon.getGiaCu_Trangchu());
            holder.giaCu.setVisibility(View.VISIBLE);
            holder.gach.setVisibility(View.VISIBLE);
        } else {
            holder.giaCu.setVisibility(View.GONE);
            holder.gach.setVisibility(View.GONE);
        }
        holder.giaMoi.setText(monngon.getGiaMoi_Trangchu());

        //thiết lập click vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TrangChiTietUserActivity.class);
                intent.putExtra("monngon", monngon); //đảm bảo dữ liệu của sp có thể được truyền vào intent phải khai báo Serializable
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(listmonngon != null) {
            return listmonngon.size();
        }
        return 0;
    }

    public  class MonNgonViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonNgon;
        private TextView phanTram;
        private TextView txt_ten_mon;
        private TextView giaCu;
        private TextView giaMoi;
        private View gach;
        public MonNgonViewHolder(@NonNull View itemView) {

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
