package com.example.smartcity.view.car.query.fragments.checkfragment.dialog.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.car.query.entities.AddressListEntity;
import com.example.smartcity.view.car.query.fragments.checkfragment.dialog.AddressDetailActivity;
import com.example.smartcity.view.car.query.fragments.checkfragment.dialog.AddressListDialogFragment;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ItemAddressViewHolder> {


    private AddressListEntity addressListEntity;
    private AddressListDialogFragment fragment;

    public AddressListAdapter() {
    }

    public AddressListAdapter(AddressListDialogFragment fragment) {
        this.fragment = fragment;
    }

    public void setData(AddressListEntity addressListEntity) {

        this.addressListEntity = addressListEntity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_address, parent, false);
        ItemAddressViewHolder viewHolder = new ItemAddressViewHolder(itemView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddressViewHolder holder, int position) {

        AddressListEntity.RowsBean address = addressListEntity.getRows().get(position);

        holder.placeView.setText(address.getPlaceName());
        holder.remarkView.setText("检车时间：" + address.getRemarks());
        holder.addressView.setText("检车地址：" + address.getAddress());
        holder.phoneView.setText("联系电话：" + address.getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment.afterSelectPlace(address);
            }
        });

        holder.detailButton.setOnClickListener(view -> {

            int id = address.getId();
            Intent intent = new Intent(view.getContext(), AddressDetailActivity.class);
            intent.putExtra("id", id);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return addressListEntity != null && addressListEntity.getRows() != null ? addressListEntity.getRows().size() : 0;
    }

    public class ItemAddressViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView placeView, remarkView, addressView, phoneView, detailButton;

        public ItemAddressViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            placeView = itemView.findViewById(R.id.textView_place);
            remarkView = itemView.findViewById(R.id.textView_remark);
            addressView = itemView.findViewById(R.id.editText_place);
            phoneView = itemView.findViewById(R.id.textView_phone_number);
            detailButton = itemView.findViewById(R.id.button_detail);
        }
    }
}
