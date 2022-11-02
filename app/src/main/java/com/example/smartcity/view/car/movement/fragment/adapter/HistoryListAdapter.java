package com.example.smartcity.view.car.movement.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.car.movement.entities.History;
import com.example.smartcity.view.car.query.fragments.carmanagement.viewholder.ItemCarInfoViewHolder;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryItemViewHolder> {

    private History history;

    public void setData(History history) {

        this.history = history;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_car_movement_history, parent, false);
        HistoryItemViewHolder viewHolder = new HistoryItemViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {

        String plate = history.getRows().get(position).getPlateNo();
        String tel = history.getRows().get(position).getTel();
        String address = history.getRows().get(position).getAddress();

        holder.plateView.setText("车牌号：" + plate);
        holder.telView.setText("车主电话" + tel);
        holder.addressView.setText("地址：" + address);
    }

    @Override
    public int getItemCount() {

        return history != null && history.getRows() != null ? history.getRows().size() : 0;
    }

    protected class HistoryItemViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView plateView, telView, addressView;

        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            plateView = itemView.findViewById(R.id.textViev_plate);
            telView = itemView.findViewById(R.id.textView_tel);
            addressView = itemView.findViewById(R.id.textView_address);
        }
    }
}
