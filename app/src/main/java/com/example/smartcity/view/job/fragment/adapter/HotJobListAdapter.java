package com.example.smartcity.view.job.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.job.entities.HotJobListEntity;
import com.example.smartcity.view.job.fragment.JobListFragment;

public class HotJobListAdapter extends RecyclerView.Adapter<HotJobListAdapter.ItemHotJobViewHolder> {

    private HotJobListEntity hotJobListEntity;
    private JobListFragment jobListFragment;

    public HotJobListAdapter(JobListFragment jobListFragment) {

        this.jobListFragment = jobListFragment;
    }

    public void setData(HotJobListEntity hotJobListEntity) {

        this.hotJobListEntity = hotJobListEntity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHotJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_hot_job, parent, false);
        ItemHotJobViewHolder viewHolder = new ItemHotJobViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHotJobViewHolder holder, int position) {

        String name = hotJobListEntity.getRows().get(position).getProfessionName();
        int length = name.length();
        holder.jobNameView.setText(length > 3 ? name.substring(0, 4) : name);
        holder.itemView.setOnClickListener(view -> {

            int id = hotJobListEntity.getRows().get(position).getId();
            jobListFragment.getHotJobList(id);
        });
    }

    @Override
    public int getItemCount() {

        return hotJobListEntity != null && hotJobListEntity.getRows() != null ? (hotJobListEntity.getRows().size() > 6 ? 6 : hotJobListEntity.getRows().size()) : 0;
    }

    public class ItemHotJobViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView jobNameView;

        public ItemHotJobViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            jobNameView = itemView.findViewById(R.id.textView_hot_job);
        }
    }
}
