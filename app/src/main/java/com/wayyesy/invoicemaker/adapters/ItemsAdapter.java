package com.wayyesy.invoicemaker.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.wayyesy.invoicemaker.R;
import com.wayyesy.invoicemaker.activity.SingleItemActivity;
import com.wayyesy.invoicemaker.db.InvoiceDB;
import com.wayyesy.invoicemaker.model.SingleItemModel;
import com.wayyesy.invoicemaker.utils.Constants;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<SingleItemModel> data;
    private Context context;
    InvoiceDB invoiceDB;

    public ItemsAdapter(List<SingleItemModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        invoiceDB = new InvoiceDB(context);
        View view = inflater.inflate(R.layout.single_items_design, parent, false);
        return new ItemsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {

        holder.itemName.setText(data.get(position).getItemName());
        holder.itemQuantity.setText(data.get(position).getItemQuantity());
        holder.itemPrice.setText(data.get(position).getItemPrice());

        holder.mainLayout.setOnClickListener(v -> {
            int handleVisibility = (holder.actionLayout.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
            Drawable handleExpandIcon = (handleVisibility == View.VISIBLE) ? context.getDrawable(R.drawable.ic_arrow_up) : context.getDrawable(R.drawable.ic_arrow_down);

            holder.handleDisplay.setImageDrawable(handleExpandIcon);

            TransitionManager.beginDelayedTransition(holder.mainLayout, new AutoTransition());
            holder.actionLayout.setVisibility(handleVisibility);
        });

        holder.addItem.setOnClickListener(v -> {

            Cursor c = invoiceDB.getRows_invoice_items_by_invoiceId(Constants.DCReferenceKey, data.get(position).getIi_id());

            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    if (c.getInt(0) > 0) {
                        Toast.makeText(context, "Item already added to this invoice.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }

            boolean result = invoiceDB.insert_invoice_items_link_details(Constants.DCReferenceKey, data.get(position).getIi_id());

            if (result) {
                Toast.makeText(context, "Item added successfully.", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Failed to save item", Toast.LENGTH_SHORT).show();
            }
        });

        holder.deleteItem.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage("Are you sure you want to delete this item ?");
            builder.setTitle("Alert !");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

                invoiceDB.delete_invoice_item_link_by_itemId(data.get(position).getIi_id());
                invoiceDB.delete_invoice_item_OneRow(data.get(position).getIi_id());
                data.remove(position);
                notifyDataSetChanged();

                dialog.cancel();
            });

            builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        holder.editItem.setOnClickListener(v -> {
            Constants.Single_Item_Active = true;
            Intent intent = new Intent(context, SingleItemActivity.class);
            intent.putExtra("itemId", data.get(position).getIi_id());
            intent.putExtra("itemName", data.get(position).getItemName());
            intent.putExtra("itemPrice", data.get(position).getItemPrice());
            intent.putExtra("itemQuantity", data.get(position).getItemQuantity());
            intent.putExtra("itemUnit", data.get(position).getItemUnit());
            intent.putExtra("itemDiscount", data.get(position).getItemDisc());
            intent.putExtra("itemTax", data.get(position).getItemTax());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemQuantity, itemPrice;
        LinearLayout editItem, deleteItem, addItem;
        ImageView handleDisplay;
        LinearLayout actionLayout, mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemPrice = itemView.findViewById(R.id.item_price);
            handleDisplay = itemView.findViewById(R.id.handle_display);

            editItem = itemView.findViewById(R.id.edit_item);
            deleteItem = itemView.findViewById(R.id.delete_item);
            addItem = itemView.findViewById(R.id.add_item);

            actionLayout = itemView.findViewById(R.id.action_layout);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
