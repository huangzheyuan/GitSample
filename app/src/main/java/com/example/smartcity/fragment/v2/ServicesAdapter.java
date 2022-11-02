package com.example.smartcity.fragment.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.R;
import com.example.smartcity.baseinfo.Connectinfo;
import com.example.smartcity.bean.QueryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceItemViewHolder> {

    private OnServiceSelected onServiceSelected;
    private List<QueryBean> list;

    public void setOnServiceSelected(OnServiceSelected onServiceSelected){

        this.onServiceSelected = onServiceSelected;
    }
    public void setData(List<QueryBean> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_service, parent, false);
        ServiceItemViewHolder holder = new ServiceItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceItemViewHolder holder, int position) {

        QueryBean queryBean = list.get(position);
        String url = Connectinfo.contexturl + queryBean.getImgUrl();
        String service = queryBean.getServiceName();

        if(service.equals("看电影")){
            service = "自助移车";
        }
        if(service.equals("活动管理")){
            service = "预约检车";
        }

        Picasso.get().load(url).placeholder(R.drawable.circle).into(holder.imageView);

        holder.textView.setText(service);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String serviceName = queryBean.getServiceName();
                onServiceSelected.serviceSelected(serviceName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected class ServiceItemViewHolder extends RecyclerView.ViewHolder{

        public View itemView;
        public ImageView imageView;
        public TextView textView;

        public ServiceItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.imageView_service);
            textView = itemView.findViewById(R.id.textView_service_name);
        }
    }
    public interface OnServiceSelected{
        void serviceSelected(String serviceName);
    }
}
