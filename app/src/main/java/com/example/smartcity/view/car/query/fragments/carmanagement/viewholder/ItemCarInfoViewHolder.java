package com.example.smartcity.view.car.query.fragments.carmanagement.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;

public class ItemCarInfoViewHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public TextView plateNumberView, mainView, carTypeView, milesView, phoneNumberView;

    public ItemCarInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;

        plateNumberView = itemView.findViewById(R.id.textView_plate_number);
        mainView = itemView.findViewById(R.id.textView_main_number);
        carTypeView = itemView.findViewById(R.id.textView_car_type);
        milesView = itemView.findViewById(R.id.textView_miles);
        phoneNumberView = itemView.findViewById(R.id.textView_phone_number);

    }


}
