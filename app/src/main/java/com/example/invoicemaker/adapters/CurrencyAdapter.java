package com.example.invoicemaker.adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
import com.example.invoicemaker.utils.Constants;
import com.google.gson.Gson;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder>{

    List<CurrencyModel> data;
    Context context;
    String positionIndicator;
    InvoiceDB invoiceDB;


    public CurrencyAdapter(List<CurrencyModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        invoiceDB = new InvoiceDB(context);
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

            Cursor cur = invoiceDB.getRows_currency(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {

                    boolean result = invoiceDB.update_currency_details(Constants.DCReferenceKey,
                            data.get(position).getCountry_name(), data.get(position).getCurrency_symbol(), data.get(position).getCountry_symbol());


                    if (!result) {
                        Toast.makeText(context, "Failed to update language", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                boolean result = invoiceDB.insert_currency_details(Constants.DCReferenceKey,
                        data.get(position).getCountry_name(), data.get(position).getCurrency_symbol(), data.get(position).getCountry_symbol());


                if (!result) {
                    Toast.makeText(context, "Failed to set language!!!", Toast.LENGTH_SHORT).show();
                }
            }


            cur.close();

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
