package com.example.smartcity.fragment.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;

import java.util.List;

public class ServiceTypesAdapter extends RecyclerView.Adapter<ServiceTypesAdapter.ServiceTypesViewHolder> {

    private List<String> types;
    public int lastSelectedPosition = -1;
    private LayoutInflater layoutInflater;
    private OnServiceTypeChanged onServiceTypeChanged;

    public ServiceTypesAdapter() {

    }

    public void SetOnServiceTypeChanged(OnServiceTypeChanged onServiceTypeChanged){

        this.onServiceTypeChanged = onServiceTypeChanged;
    }

    public void setData(List<String> types) {
        this.types = types;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_service_type, parent, false);
        ServiceTypesViewHolder holder = new ServiceTypesViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceTypesViewHolder holder, int position) {

        holder.radioButton.setText(types.get(position));
        holder.radioButton.setChecked(lastSelectedPosition == position);

        if(lastSelectedPosition == -1 && position == 0){

            holder.radioButton.setChecked(true);
        }
    }


    @Override
    public int getItemCount() {
        return types == null ? 0 : types.size();
    }

    protected class ServiceTypesViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public RadioButton radioButton;

        public ServiceTypesViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            this.radioButton = itemView.findViewById(R.id.radio_service_type);

            View.OnClickListener clickListener = view -> {

                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();

                if(view == radioButton){

                    RadioButton rb = (RadioButton) view;
                    String type = rb.getText().toString();
                    onServiceTypeChanged.typeChanged(type);
                }
            };

            itemView.setOnClickListener(clickListener);
            radioButton.setOnClickListener(clickListener);
        }
    }

    public interface OnServiceTypeChanged{

        void typeChanged(String type);
    }
}
