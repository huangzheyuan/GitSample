package com.example.smartcity.view.house.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.view.house.HouseActivity;
import com.example.smartcity.view.house.HouseDetailActivity;
import com.example.smartcity.view.house.entities.HouseListEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HouseListAdpater extends RecyclerView.Adapter<HouseListAdpater.ItemHouseViewHolder> {


    private HouseListEntity houseListEntity;
    private List<HouseListEntity.RowsBean> houseList;

    public void setData(HouseListEntity houseListEntity){

        this.houseListEntity = houseListEntity;
        houseList = houseListEntity.getRows();
        notifyDataSetChanged();
    }

    public void setData(List<HouseListEntity.RowsBean> houseList){

        this.houseList = houseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_house, parent, false);
        ItemHouseViewHolder viewHolder = new ItemHouseViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHouseViewHolder holder, int position) {

        HouseListEntity.RowsBean house = houseList.get(position);

        String url = Connectinfo.contexturl +  house.getPic();

        Picasso.get().load(url).placeholder(R.drawable.ic_house_holder).into(holder.houseIconView);
        holder.houseView.setText( "房源：" + house.getSourceName());
        holder.priceView.setText( "价格：" + house.getPrice());
        holder.phoneView.setText( "电话：" + house.getTel());

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(holder.itemView.getContext(), HouseDetailActivity.class);
            intent.putExtra("house", house);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return houseList != null ? houseList.size(): 0;
    }

    public class ItemHouseViewHolder extends RecyclerView.ViewHolder{

        public View itemView;
        public ImageView houseIconView;
        public TextView houseView, priceView, phoneView;

        public ItemHouseViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            houseIconView = itemView.findViewById(R.id.imageView_house_icon);
            houseView =itemView.findViewById(R.id.textView_house);
            priceView = itemView.findViewById(R.id.textView_price);
            phoneView = itemView.findViewById(R.id.textView_phone);
        }
    }
}
