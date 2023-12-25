package com.example.invoicemaker.adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.activity.SingleItemActivity;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.invoice.InvoiceDashboardActivity;
import com.example.invoicemaker.model.SingleItemInvoiceLinkedModel;
import com.example.invoicemaker.utils.Constants;

import java.text.DecimalFormat;
import java.util.List;

public class InvoiceItemsAdapter extends RecyclerView.Adapter<InvoiceItemsAdapter.ViewHolder> {

    private final List<SingleItemInvoiceLinkedModel> data;
    private Context context;
    InvoiceDB invoiceDB;
    double netItemsPrice = 0.0;

    public InvoiceItemsAdapter(List<SingleItemInvoiceLinkedModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public InvoiceItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        invoiceDB = new InvoiceDB(context);
        View view = inflater.inflate(R.layout.single_items_design_added, parent, false);
        return new InvoiceItemsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceItemsAdapter.ViewHolder holder, int position) {

        Cursor cur = invoiceDB.getRows_invoice_item_byId(data.get(position).getInvoice_item_id());

        if (cur.getCount() > 0) {

            double netItemPrice = 0.0, totalItemPrice = 0.0;

            while (cur.moveToNext()) {
                holder.itemName.setText(cur.getString(2));
                holder.itemPrice.setText(cur.getString(4) + " X " + Constants.InvoiceCurrencySymbol + cur.getString(3));

                totalItemPrice = Double.parseDouble(cur.getString(3)) * Double.parseDouble(cur.getString(4));

                double extra = (((Double.parseDouble(cur.getString(7)) / 100) * totalItemPrice) - ((Double.parseDouble(cur.getString(6)) / 100) * totalItemPrice));

                netItemPrice = extra + totalItemPrice;
                netItemsPrice += netItemPrice;

                if(cur.getString(6) != null && Double.valueOf(cur.getString(6)) > 0) {
                    holder.invoiceDiscountText.setVisibility(View.VISIBLE);
                    holder.itemDiscount.setVisibility(View.VISIBLE);
                    holder.itemDiscount.setText("- " + Constants.InvoiceCurrencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(cur.getString(6)) / 100 * totalItemPrice));
                }

                if(cur.getString(7) != null && Double.valueOf(cur.getString(7)) > 0) {
                    holder.invoiceTaxText.setVisibility(View.VISIBLE);
                    holder.itemTax.setVisibility(View.VISIBLE);
                    holder.itemTax.setText("+ " + Constants.InvoiceCurrencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(cur.getString(7)) / 100 * totalItemPrice));
                }

                holder.itemFinalPrice.setText(new DecimalFormat("##.##").format(netItemPrice));
            }
        }

        Constants.TotalInvoicePrice = netItemsPrice;

        ((InvoiceDashboardActivity) context).updateInvoiceFromAdapter();

        cur.close();

        holder.deleteItem.setOnClickListener(v -> {
            invoiceDB.delete_invoice_item_link_by_id(data.get(position).getIim_id());
            data.remove(position);
            notifyDataSetChanged();

            ((InvoiceDashboardActivity) context).fetchInvoiceData();
        });

        holder.editItem.setOnClickListener(v -> {
            Cursor cur1 = invoiceDB.getRows_invoice_item_byId(data.get(position).getInvoice_item_id());

            if (cur1.getCount() > 0) {
                while (cur1.moveToNext()) {
                    Constants.Single_Item_Active = true;
                    Intent intent = new Intent(context, SingleItemActivity.class);
                    intent.putExtra("itemId", data.get(position).getInvoice_item_id());
                    intent.putExtra("itemName", cur1.getString(2));
                    intent.putExtra("itemPrice", cur1.getString(3));
                    intent.putExtra("itemQuantity", cur1.getString(4));
                    intent.putExtra("itemUnit", cur1.getString(5));
                    intent.putExtra("itemDiscount", cur1.getString(6));
                    intent.putExtra("itemTax", cur1.getString(7));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPrice, itemDiscount, itemTax, itemFinalPrice, invoiceTaxText, invoiceDiscountText;
        ImageView editItem, deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemDiscount = itemView.findViewById(R.id.item_discount);
            invoiceDiscountText = itemView.findViewById(R.id.discount_text);
            itemTax = itemView.findViewById(R.id.item_tax);
            itemFinalPrice = itemView.findViewById(R.id.item_final_price);
            invoiceTaxText = itemView.findViewById(R.id.tax_text);

            editItem = itemView.findViewById(R.id.edit_item);
            deleteItem = itemView.findViewById(R.id.delete_item);
        }
    }
}
