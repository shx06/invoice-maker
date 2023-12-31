package com.wayyeasy.invoicemaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wayyeasy.invoicemaker.R;
import com.wayyeasy.invoicemaker.db.InvoiceDB;
import com.wayyeasy.invoicemaker.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;

public class SingleItemActivity extends AppCompatActivity {

    TextInputEditText name, price, quantity, unit, discount, tax;
    Button save_next_btn;
    InvoiceDB invoiceDB;
    TextView toolbarHeader;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("New Item");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());

        name = findViewById(R.id.item_name);
        price = findViewById(R.id.item_price);
        quantity = findViewById(R.id.item_quantity);
        unit = findViewById(R.id.item_unit);
        discount = findViewById(R.id.item_discount);
        tax = findViewById(R.id.item_tax);
        save_next_btn = findViewById(R.id.save_next_btn);

        invoiceDB = new InvoiceDB(getApplicationContext());

        if (Constants.Single_Item_Active) {
            save_next_btn.setText("Update");
            toolbarHeader.setText("Update Item");

            itemId = getIntent().getIntExtra("itemId", 0);
            name.setText(getIntent().getStringExtra("itemName"));
            price.setText(getIntent().getStringExtra("itemPrice"));
            quantity.setText(getIntent().getStringExtra("itemQuantity"));
            unit.setText(getIntent().getStringExtra("itemUnit"));
            discount.setText(getIntent().getStringExtra("itemDiscount"));
            tax.setText(getIntent().getStringExtra("itemTax"));
        }

        save_next_btn.setOnClickListener(view -> {

            boolean result = false;

            result = savePersonalDetails();


            if (result) {
                finish();
            }

        });
    }

    private boolean savePersonalDetails() {

        boolean result;

        if (!(Constants.Single_Item_Active)) {

            result = invoiceDB.insert_invoice_item_details(Constants.DCReferenceKey,
                    name.getText().toString(), price.getText().toString(), quantity.getText().toString(),
                    unit.getText().toString(), discount.getText().toString(), tax.getText().toString());


            if (result) {

                if (Constants.IsInvoiceItem) {

                    Cursor cursor = invoiceDB.get_last_insertedRows_invoice_items();

                    if (cursor.moveToFirst()) {

                        try {

                            String dataColumn1 = cursor.getString(cursor.getColumnIndex("ii_id"));

                            boolean result2 = invoiceDB.insert_invoice_items_link_details(Constants.DCReferenceKey, Integer.valueOf(dataColumn1));

                            if (result2) {
                                Toast.makeText(this, "Item added successfully.", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(this, "Failed to save item", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(this, "Failed to add item: -> " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    cursor.close();

                }

                Constants.Invoice_item_Active = true;
                boolean u_result = invoiceDB.update_data_controller(Constants.DCReferenceKey, Constants.Flag);
                if (u_result) {

                } else {
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
            }

        } else {

            result = invoiceDB.update_invoice_item_details(Integer.valueOf(itemId),
                    name.getText().toString(), price.getText().toString(), quantity.getText().toString(),
                    unit.getText().toString(), discount.getText().toString(), tax.getText().toString());

            if (result) {
                //  Toast.makeText(CreateBioDataPDActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                System.out.println("Updated");
            } else {
                Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show();
            }


        }

        return result;
    }
}