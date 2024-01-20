package com.wayyeasy.invoicemaker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wayyeasy.invoicemaker.model.TopicsModel;
import com.wayyeasy.invoicemaker.R;
import java.util.ArrayList;

public class DashboardTopicsAdapter extends RecyclerView.Adapter<DashboardTopicsAdapter.ViewHolder>{

    private ArrayList<TopicsModel> data;
    private Context context;

    public DashboardTopicsAdapter(ArrayList<TopicsModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardTopicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_topic_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardTopicsAdapter.ViewHolder holder, int position) {
        holder.layout.setOnClickListener(v->{
            Toast.makeText(context, data.get(position).getTopicName(), Toast.LENGTH_SHORT).show();
        });
        holder.topic.setText(data.get(position).getTopicName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView topic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.topic_layout);
            topic = itemView.findViewById(R.id.topic_name);
        }
    }
}
