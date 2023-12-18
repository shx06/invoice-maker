package com.example.invoicemaker.adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.Dashboard.DashboardDataRecyclerView;
import com.example.invoicemaker.Dashboard.MainActivity;
import com.example.invoicemaker.R;
import com.example.invoicemaker.activity.InvoiceInfoActivity;
import com.example.invoicemaker.activity.SingleItemActivity;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.invoice.InvoiceDashboardActivity;
import com.example.invoicemaker.model.DataControllerModel;
import com.example.invoicemaker.model.SingleItemModel;
import com.example.invoicemaker.utils.Constants;
import com.google.gson.Gson;

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

            Log.d(TAG, "onBindViewHolder: " + invoiceDB.getRows_invoice_items_by_invoiceId(Constants.DCReferenceKey, data.get(position).getIi_id()));

//            boolean result = invoiceDB.insert_invoice_items_link_details(Constants.DCReferenceKey, data.get(position).getIi_id());
//
//            if (result) {
//                Toast.makeText(context, "Item added successfully.", Toast.LENGTH_SHORT).show();
//
//            } else {
//                Toast.makeText(context, "Failed to save item", Toast.LENGTH_SHORT).show();
//            }
        });

        holder.deleteItem.setOnClickListener(v -> {
            invoiceDB.delete_invoice_item_OneRow(data.get(position).getIi_id());
            data.remove(position);
            notifyDataSetChanged();
        });

        holder.editItem.setOnClickListener(v -> {
            Constants.Single_Item_Active = true;
            Constants.ItemID = data.get(position).getIi_id();
            Intent intent = new Intent(context, SingleItemActivity.class);
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

        TextView itemName, itemQuantity, itemPrice, editItem, deleteItem, addItem;
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
