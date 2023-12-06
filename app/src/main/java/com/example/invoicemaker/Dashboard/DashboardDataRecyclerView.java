package com.example.invoicemaker.Dashboard;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.invoice.InvoiceDashboardActivity;
import com.example.invoicemaker.model.DataControllerModel;
import com.google.gson.Gson;


import java.util.List;

public class DashboardDataRecyclerView extends RecyclerView.Adapter<DashboardDataRecyclerView.ViewHolder> {

    List<DataControllerModel> data;
    private Context context;
    int invoiceId;
    InvoiceDB invoiceDB;

    public DashboardDataRecyclerView(List<DataControllerModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardDataRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        invoiceDB = new InvoiceDB(context);
        View view = inflater.inflate(R.layout.single_dashboard_data_layout, parent, false);
        return new DashboardDataRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardDataRecyclerView.ViewHolder holder, int position) {

        Cursor p_cur_inv_info = invoiceDB.getRows_invoice_info(data.get(position).getDc_id());
        Cursor p_cur_inv_client = invoiceDB.getRows_client(data.get(position).getDc_id());

        if (p_cur_inv_info.getCount() > 0) {
            while (p_cur_inv_info.moveToNext()) {
                invoiceId = p_cur_inv_info.getInt(0);
                holder.invoice_no.setText(p_cur_inv_info.getString(3));
                holder.invoice_date.setText(p_cur_inv_info.getString(4));
            }
        }

        if (p_cur_inv_client.getCount() > 0) {
            while (p_cur_inv_client.moveToNext()) {
                holder.invoice_client_name.setText(p_cur_inv_client.getString(2));
            }
        }

        p_cur_inv_info.close();
        p_cur_inv_client.close();

//        holder.cardView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, InvoiceDashboardActivity.class);
//            context.startActivity(intent);
//        });

        holder.edit.setOnClickListener(v -> {
            ((MainActivity)context).forEdit(data.get(position).getDc_id(), invoiceId);
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView invoice_no, invoice_date, invoice_amt, invoice_client_name, invoice_total_bill, invoice_status;
        CardView cardView;
        Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoice_no = itemView.findViewById(R.id.invoice_no);
            invoice_date = itemView.findViewById(R.id.date);
            invoice_amt = itemView.findViewById(R.id.amount_paid);
            invoice_client_name = itemView.findViewById(R.id.client);
            invoice_total_bill = itemView.findViewById(R.id.total_bill);
            invoice_status = itemView.findViewById(R.id.status);

            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

            cardView = itemView.findViewById(R.id.item_layout);
        }

    }


}
