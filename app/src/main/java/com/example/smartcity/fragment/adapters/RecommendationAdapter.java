package com.example.smartcity.fragment.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.QueryData;
import com.example.smartcity.fragment.interf.OnRecommendationClicked;
import com.example.smartcity.util.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ItemRecommendationViewHolder> {

    private QueryData queryData;
    private OnRecommendationClicked clicked;

    private List<Integer> ids;
    private List<Integer> indices;

    private static final int MOST = 4;

    public void setClicked(OnRecommendationClicked clicked) {
        this.clicked = clicked;
    }

    public void setQueryData(QueryData queryData) {

        ids = new ArrayList<>();
        indices = new ArrayList<>();
        this.queryData = queryData;

        sort();
        notifyDataSetChanged();
    }

    private void sort() {

        int size = queryData.getRows().size();

        for (int i = 0; i < size; ++i) {

            ids.add(queryData.getRows().get(i).getId());
        }

        for (int i = 0; i < size; ++i) {

            int max_id = ids.get(0);
            int max_index = 0;
            for (int j = 1; j < size; ++j) {

                if (ids.get(j) >= 0 && ids.get(j) > max_id) {

                    max_id = ids.get(j);
                    max_index = j;
                }
            }

            indices.add(max_index);
            ids.set(max_index, -1);
        }
    }

    @NonNull
    @Override
    public ItemRecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_recommendation, parent, false);
        ItemRecommendationViewHolder viewHolder = new ItemRecommendationViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecommendationViewHolder holder, @SuppressLint("RecyclerView") int position) {


//        if (position == queryData.getRows().size()) {
        if (position == MOST) {
            //更多
            holder.iconView.setImageResource(R.drawable.ic_more);
            holder.textView.setText("更多");
        } else {

            PicassoUtils.loadImageViewCrop2(holder.iconView.getContext(), Connectinfo.contexturl + queryData.getRows().get(indices.get(position)).getImgUrl(), holder.iconView);
            String servieName = queryData.getRows().get(indices.get(position)).getServiceName();
            if(servieName.equals("看电影")){

                servieName = "自助移车";
            }

            if(servieName.equals("活动管理")){

                servieName = "预约检车";
            }

            holder.textView.setText(servieName);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (position == queryData.getRows().size()) {
                if (position == MOST) {

                    clicked.onClick("更多");
                } else {

                    try {
                        clicked.onClick(queryData.getRows().get(indices.get(position)).getServiceName());

                    } catch (NullPointerException e) {

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        int count = 1;

        if (queryData != null && queryData.getRows() != null) {

            int size = queryData.getRows().size();
            if (size > 0) {

                count = size + 1;
            }
        }
        return count > MOST ? MOST + 1 : count;
    }

    @Override
    public int getItemViewType(int position) {

        if (queryData == null)

            return -1;
        return position == queryData.getRows().size() ? 2 : 1;


//        if (queryData != null && queryData.getRows() != null) {
//
//            return position == queryData.getRows().size() ? 2 : 1;
//        }
//        return 0;
    }

    class ItemRecommendationViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public ImageView iconView;
        public TextView textView;

        public ItemRecommendationViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            iconView = itemView.findViewById(R.id.image_icon);
            textView = itemView.findViewById(R.id.textView_title);

        }
    }
}
