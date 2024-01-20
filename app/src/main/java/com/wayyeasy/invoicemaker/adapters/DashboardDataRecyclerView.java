package com.wayyeasy.invoicemaker.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wayyeasy.invoicemaker.MainActivity;
import com.wayyeasy.invoicemaker.R;
import com.wayyeasy.invoicemaker.db.InvoiceDB;
import com.wayyeasy.invoicemaker.model.DataControllerModel;


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

        holder.delete.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            // Set the message show for the Alert time
            builder.setMessage("Are you sure you want to delete this invoice?");

            // Set Alert Title
            builder.setTitle("Alert !");

            builder.setCancelable(false);

            builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> {

                invoiceDB.delete_company_OneRow(data.get(position).getDc_id());
                invoiceDB.delete_client_OneRow(data.get(position).getDc_id());
                invoiceDB.delete_invoice_item_link_by_dcId(data.get(position).getDc_id());
                invoiceDB.delete_currency_OneRow(data.get(position).getDc_id());
                invoiceDB.delete_discount_by_dcId(data.get(position).getDc_id());


                invoiceDB.delete_invoice_info_OneRow(data.get(position).getDc_id());
                invoiceDB.delete_data_controller_OneRow(data.get(position).getDc_id());
                data.remove(position);
                notifyDataSetChanged();

            });

            builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();

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
