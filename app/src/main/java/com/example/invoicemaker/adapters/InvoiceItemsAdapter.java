package com.example.invoicemaker.adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.invoicemaker.model.SingleItemInvoiceLinkedModel;
import com.example.invoicemaker.model.SingleItemModel;
import com.example.invoicemaker.utils.Constants;
import com.google.gson.Gson;

import java.util.List;

public class InvoiceItemsAdapter extends RecyclerView.Adapter<InvoiceItemsAdapter.ViewHolder> {

    private List<SingleItemInvoiceLinkedModel> data;
    private Context context;
    InvoiceDB invoiceDB;
    SingleItemModel singleItemModel;

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

            while (cur.moveToNext()) {
                holder.itemName.setText(cur.getString(2));
                holder.itemQuantity.setText(cur.getString(4) + " x " + "Rs. " + cur.getString(3));
                holder.itemPrice.setText("Total: " + Float.valueOf(cur.getString(3))*Float.valueOf(cur.getString(4)));

                singleItemModel = new SingleItemModel();
                singleItemModel.setItemName(cur.getString(2));
                singleItemModel.setItemPrice(cur.getString(3));
                singleItemModel.setItemQuantity(cur.getString(4));
                singleItemModel.setItemUnit(cur.getString(5));
                singleItemModel.setItemDisc(cur.getString(6));
                singleItemModel.setItemTax(cur.getString(7));
            }
        }

        cur.close();

        holder.deleteItem.setOnClickListener(v-> {
            invoiceDB.delete_invoice_item_OneRow(data.get(position).getIim_id());
            data.remove(position);
            notifyDataSetChanged();
        });

        holder.editItem.setOnClickListener(v-> {
            Constants.Single_Item_Active = true;
            Constants.ItemID = singleItemModel.getIi_id();
            Intent intent = new Intent(context, SingleItemActivity.class);
            intent.putExtra("itemName", singleItemModel.getItemName());
            intent.putExtra("itemPrice", singleItemModel.getItemPrice());
            intent.putExtra("itemQuantity", singleItemModel.getItemQuantity());
            intent.putExtra("itemUnit", singleItemModel.getItemUnit());
            intent.putExtra("itemDiscount", singleItemModel.getItemDisc());
            intent.putExtra("itemTax", singleItemModel.getItemTax());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemQuantity, itemPrice;
        ImageView editItem, deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity_price);
            itemPrice = itemView.findViewById(R.id.item_total_price);

            editItem = itemView.findViewById(R.id.edit_item);
            deleteItem = itemView.findViewById(R.id.delete_item);
        }
    }
}
