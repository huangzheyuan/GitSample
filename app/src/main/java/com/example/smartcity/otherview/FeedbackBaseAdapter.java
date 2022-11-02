package com.example.smartcity.otherview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.bean.FeedbackBean;

import java.util.List;

public class FeedbackBaseAdapter extends RecyclerView.Adapter<FeedbackBaseAdapter.MyHolder> {
    private List<FeedbackBean> mfeedbackList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    //定义一个设置点击监听器的方法
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public FeedbackBaseAdapter(List<FeedbackBean> mfeedbackList) {
        this.mfeedbackList = mfeedbackList;

    }

    public FeedbackBaseAdapter(List<FeedbackBean> mfeedbackList, Context mContext) {
        this.mfeedbackList = mfeedbackList;
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_list_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        FeedbackBean feedbackBean = mfeedbackList.get(position);
////        holder.userid.setText("反馈用户id:" + String.valueOf(feedbackBean.getUserId()));
        holder.title.setText("反馈标题:" +  feedbackBean.getTitle());
        holder.content.setText("反馈内容：" + feedbackBean.getContent());
////        holder.createtime.setText("反馈时间：" + feedbackBean.getCreateTime());
//        //如果设置了回调，则设置点击事件
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    mItemClickListener.onItemClick(holder.itemView, position);
//                    mItemClickListener.onItemClick(holder.itemView, holder.getAbsoluteAdapterPosition());
                    mItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mfeedbackList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        //        TextView userid;
        TextView title;
        TextView content;
//        TextView createtime;

        public MyHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_feedback_title);
            content = view.findViewById(R.id.tv_feedback_content);
//            createtime = view.findViewById(R.id.tv_feedbackcreatetime);
        }
    }

}

