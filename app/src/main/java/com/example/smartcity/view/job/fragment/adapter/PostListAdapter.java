package com.example.smartcity.view.job.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.view.job.entities.PostListEntity;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ItemPostViewHolder> {

    private PostListEntity postListEntity;

    public void setData(PostListEntity postListEntity) {

        this.postListEntity = postListEntity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_post, parent, false);
        ItemPostViewHolder viewHolder = new ItemPostViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPostViewHolder holder, int position) {

        PostListEntity.RowsBean post = postListEntity.getRows().get(position);

        holder.postNameView.setText("职位名称：" + post.getPostName());
        holder.companyName.setText("公司名称：" + post.getCompanyName());
        holder.moneyView.setText("薪  酬：" + post.getMoney() + "/月");

    }

    @Override
    public int getItemCount() {
        return postListEntity != null && postListEntity.getRows() != null ? postListEntity.getRows().size() : 0;
    }

    public class ItemPostViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public TextView postNameView;
        public TextView companyName;
        public TextView moneyView;

        public ItemPostViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            postNameView = itemView.findViewById(R.id.textView_postname);
            companyName = itemView.findViewById(R.id.textView_company_name);
            moneyView = itemView.findViewById(R.id.textView_money);
        }
    }
}
