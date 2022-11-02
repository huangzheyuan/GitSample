package com.example.smartcity.view.car.query.fragments.checkfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.car.query.entities.CarInfoListEntity;

public class CarCheckListAdapter extends RecyclerView.Adapter<CarCheckListAdapter.ItemCarCheckViewHolder> {

    private CarInfoListEntity carInfoEntity;
    public int lastSelectedPosition = -1;

    public void setData(CarInfoListEntity carInfoEntity) {

        this.carInfoEntity = carInfoEntity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemCarCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_car_check, parent, false);
        ItemCarCheckViewHolder viewHolder = new ItemCarCheckViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCarCheckViewHolder holder, int position) {

        CarInfoListEntity.RowsBean carInfo = carInfoEntity.getRows().get(position);
        holder.plateView.setText(carInfo.getPlateNo());
        holder.radio.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {

        return carInfoEntity != null && carInfoEntity.getRows() != null ? carInfoEntity.getRows().size() : 0;
    }

    public class ItemCarCheckViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView plateView;
        public RadioButton radio;

        public ItemCarCheckViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            plateView = itemView.findViewById(R.id.textView_plate);
            radio = itemView.findViewById(R.id.radioButton);

            View.OnClickListener clickListener = view -> {

                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();
            };

            itemView.setOnClickListener(clickListener);
            radio.setOnClickListener(clickListener);
        }

    }



}
