package com.example.invoicemaker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.model.CurrencyModel;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder>{

    List<CurrencyModel> data;
    Context context;
    String positionIndicator;

    public CurrencyAdapter(List<CurrencyModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = inflater.inflate(R.layout.single_language_layout, parent, false);
        return new CurrencyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyAdapter.ViewHolder holder, int position) {
        holder.countryName.setText(data.get(position).getCountry_name());
        holder.countryCurrency.setText(data.get(position).getCurrency_symbol());
        holder.countrySymbol.setText(data.get(position).getCountry_symbol());

        holder.itemLayout.setOnClickListener(v -> {
            positionIndicator = data.get(position).getCountry_name();
            notifyDataSetChanged();
        });


        if(data.get(position).getCountry_name().equals(positionIndicator)) {
            holder.itemLayout.setBackgroundColor(context.getResources().getColor(R.color.light_grey));
            holder.countryName.setTextColor(context.getResources().getColor(R.color.theme_blue));
            holder.countryCurrency.setTextColor(context.getResources().getColor(R.color.theme_blue));
            holder.countrySymbol.setTextColor(context.getResources().getColor(R.color.theme_blue));
            holder.indicator.setBackgroundColor(context.getResources().getColor(R.color.theme_blue));
        }else {
            holder.itemLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.countryName.setTextColor(context.getResources().getColor(R.color.black));
            holder.countryCurrency.setTextColor(context.getResources().getColor(R.color.black));
            holder.countrySymbol.setTextColor(context.getResources().getColor(R.color.black));
            holder.indicator.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName, countrySymbol, countryCurrency;
        LinearLayout itemLayout;
        View indicator;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
            countrySymbol = itemView.findViewById(R.id.country_symbol);
            countryCurrency = itemView.findViewById(R.id.country_currency);
            itemLayout = itemView.findViewById(R.id.item_layout);
            indicator = itemView.findViewById(R.id.indicator);
        }
    }
}
