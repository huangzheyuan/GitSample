package com.example.smartcity.view.car.query.fragments.carmanagement.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;
import com.example.smartcity.view.car.query.fragments.carmanagement.activities.CarInfoActivity;
import com.example.smartcity.view.car.query.fragments.carmanagement.viewholder.ItemCarInfoViewHolder;

public class CarListAdapter extends RecyclerView.Adapter<ItemCarInfoViewHolder> {

    private CarInfoListEntity carInfoEntity;

    public void setData(CarInfoListEntity carInfoEntity) {
        this.carInfoEntity = carInfoEntity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemCarInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_car_info, parent, false);
        ItemCarInfoViewHolder viewHolder = new ItemCarInfoViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCarInfoViewHolder holder, int position) {

        final CarInfoListEntity.RowsBean row = carInfoEntity.getRows().get(position);
        holder.plateNumberView.setText("车牌：" + row.getPlateNo());
        holder.mainView.setText("车架：" + row.getEngineNo());
        holder.carTypeView.setText("类型：" + row.getType());
//        holder.milesView.setText("公里数：" + row.getMileage());
//        holder.phoneNumberView.setText("电话：" + row.getPhone());
        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), CarInfoActivity.class);
            intent.putExtra("car", row);
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return carInfoEntity.getRows() == null ? 0 : carInfoEntity.getRows().size();
    }
}
