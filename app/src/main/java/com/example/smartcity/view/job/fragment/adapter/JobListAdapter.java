package com.example.smartcity.view.job.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.job.fragment.JobListFragment;
import com.example.smartcity.view.job.entities.JobListEntity;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ItemJobViewHolder> {


    private JobListEntity jobListEntity;
    private JobListFragment jobListFragment;

    public void setData(JobListEntity jobListEntity) {
        this.jobListEntity = jobListEntity;
        notifyDataSetChanged();
    }
    //  todo 智慧
    public JobListAdapter(JobListFragment jobListFragment){
        this.jobListFragment = jobListFragment;
    }

    @NonNull
    @Override
    public ItemJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_job_list, parent, false);
        ItemJobViewHolder viewHolder = new ItemJobViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemJobViewHolder holder, int position) {

        JobListEntity.RowsBean job = jobListEntity.getRows().get(position);
        holder.jobNameView.setText("职位名称："+job.getProfessionName());
        holder.dutyView.setText("职位描述："+job.getObligation());
        holder.addressView.setText("单位地址："+job.getAddress());
        holder.wageView.setText("薪  酬："+job.getSalary() + "/月");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jobListFragment.showJobDetail(job);

            }
        });
    }

    @Override
    public int getItemCount() {

        return jobListEntity != null && jobListEntity.getRows() != null ? jobListEntity.getRows().size() : 0;
    }

    public class ItemJobViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView jobNameView, dutyView, addressView, wageView;

        public ItemJobViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            jobNameView = itemView.findViewById(R.id.textView_job_name);
            dutyView = itemView.findViewById(R.id.textView_duty);
            addressView = itemView.findViewById(R.id.textView_address);
            wageView = itemView.findViewById(R.id.textView_money);
        }
    }
}
