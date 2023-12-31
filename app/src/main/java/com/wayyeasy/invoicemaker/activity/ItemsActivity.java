package com.wayyeasy.invoicemaker.activity;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.wayyeasy.invoicemaker.R;
import com.wayyeasy.invoicemaker.adapters.ItemsAdapter;
import com.wayyeasy.invoicemaker.db.InvoiceDB;
import com.wayyeasy.invoicemaker.model.SingleItemModel;
import com.wayyeasy.invoicemaker.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    CardView newItemLayout;
    InvoiceDB invoiceDB;
    RecyclerView itemsRecyclerView;
    ItemsAdapter itemsAdapter;
    List<SingleItemModel> dataItemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Add Item");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());

        newItemLayout = findViewById(R.id.new_item_layout);
        itemsRecyclerView = findViewById(R.id.items_recycler_view);

        invoiceDB = new InvoiceDB(getApplicationContext());

        newItemLayout.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: "+Constants.IsInvoiceItem);
            Constants.Single_Item_Active = false;
            startActivity(new Intent(getApplicationContext(), SingleItemActivity.class));
            if(Constants.IsInvoiceItem) {
                finish();
            }
        });

        FetchDataAndSet();

        ItemsRecyclerView();
    }

    private void ItemsRecyclerView() {
        itemsAdapter = new ItemsAdapter(dataItemsList, this);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        itemsRecyclerView.setAdapter(itemsAdapter);
    }

    private void FetchDataAndSet() {
        dataItemsList = new ArrayList<>();

        Cursor m_cur = invoiceDB.getAllRows_invoice_item();

        if (m_cur.getCount() > 0) {
            while (m_cur.moveToNext()) {

                dataItemsList.add(new SingleItemModel(m_cur.getInt(0), m_cur.getInt(1),
                        m_cur.getString(2), m_cur.getString(3), m_cur.getString(4),
                        m_cur.getString(5), m_cur.getString(6), m_cur.getString(7)));
            }

        }
        m_cur.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Constants.InvoiceReferenceKey != -1) {
            FetchDataAndSet();
            ItemsRecyclerView();
        }
    }
}