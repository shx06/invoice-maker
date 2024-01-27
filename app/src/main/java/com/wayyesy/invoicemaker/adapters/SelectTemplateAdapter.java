package com.wayyesy.invoicemaker.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wayyesy.invoicemaker.R;

import com.wayyesy.invoicemaker.activity.TemplateSelectionActivity;
import com.wayyesy.invoicemaker.model.SelectTemplateModel;

import java.util.List;

public class SelectTemplateAdapter extends RecyclerView.Adapter<SelectTemplateAdapter.ViewHolder> {
    List<SelectTemplateModel> selectTemplateList;
    Context context;
    Activity activity;



    public SelectTemplateAdapter(List<SelectTemplateModel> selectTemplateList, Context context, Activity activity) {
        this.selectTemplateList = selectTemplateList;
        this.context = context;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_template_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ClickItem(position);

        holder.template_image.setImageResource(selectTemplateList.get(position).getImage());


    }


    @Override
    public int getItemCount() {
        return selectTemplateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView template_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            template_image = itemView.findViewById(R.id.template_image);


        }

        void ClickItem(int position) {
            itemView.setOnClickListener(v -> {
                ((TemplateSelectionActivity) context).TemplateSelection(selectTemplateList.get(position).getTemplate_id());
            });
        }
    }


}


