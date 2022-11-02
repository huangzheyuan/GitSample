package com.example.smartcity.view.car.query.fragments.mycheck.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.car.query.entities.MyCheckListEntity;


public class MyCheckListAdapter extends RecyclerView.Adapter<MyCheckListAdapter.ItemMyCheckViewHolder> {

    private MyCheckListEntity myCheckListEntity;

    public void setData(MyCheckListEntity myCheckListEntity) {

        this.myCheckListEntity = myCheckListEntity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemMyCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_mycheck, parent, false);
        ItemMyCheckViewHolder viewHolder = new ItemMyCheckViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMyCheckViewHolder holder, int position) {

        MyCheckListEntity.RowsBean myCheck = myCheckListEntity.getRows().get(position);


        String plateNum = myCheck.getPlateNo();
        String address = myCheck.getPlaceName();

        holder.plateView.setText("车牌：" + (plateNum == null ? "N/A" : plateNum));
        holder.dateTimeView.setText("日期：" + myCheck.getAptTime());
        holder.addressView.setText("地址：" + (address == null ? "N/A" : address));
    }

    @Override
    public int getItemCount() {
        return myCheckListEntity != null && myCheckListEntity.getRows() != null ? myCheckListEntity.getRows().size() : 0;
    }

    public class ItemMyCheckViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView plateView, dateTimeView, addressView;

        public ItemMyCheckViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            plateView = itemView.findViewById(R.id.textView_plate);
            dateTimeView = itemView.findViewById(R.id.textView_date_time);
            addressView = itemView.findViewById(R.id.textView_address);
        }
    }
}
