package com.wayyeasy.invoicemaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.wayyeasy.invoicemaker.R;
import com.wayyeasy.invoicemaker.db.InvoiceDB;
import com.wayyeasy.invoicemaker.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SingleItemActivity extends AppCompatActivity {

    TextInputEditText name, price, quantity, unit, discount, tax;
    TextInputLayout nameLayout, priceLayout, quantityLayout;
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

        ViewHandler();

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

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null) {
                    quantityLayout.setHelperText("Item quantity is required");
                    quantityLayout.requestFocus();
                } else {
                    quantityLayout.setHelperText("");

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        save_next_btn.setOnClickListener(view -> {

            boolean result = false;

            if (name != null && name.length() > 0) {
                if (price != null && price.length() > 0) {
                    if (quantity != null && quantity.length() > 0) {

                        result = savePersonalDetails();

                    } else {
                        quantityLayout.setHelperText("Item quantity is required");
                        quantityLayout.requestFocus();
                    }
                } else {
                    priceLayout.setHelperText("Item price is required");
                    priceLayout.requestFocus();
                }
            } else {
                nameLayout.setHelperText("Item name is required");
                nameLayout.requestFocus();
            }

            if (result) {
                finish();
            }

        });
    }

    private boolean savePersonalDetails() {

        boolean result;


        String Name = Objects.requireNonNull(name.getText()).toString();
        String Price = Objects.requireNonNull(price.getText()).toString();
        String Quantity = Objects.requireNonNull(quantity.getText()).toString();
        String Unit = Objects.requireNonNull(unit.getText()).toString();
        String Discount = Objects.requireNonNull(discount.getText()).toString();
        String Tax = Objects.requireNonNull(tax.getText()).toString();


        if (!(Constants.Single_Item_Active)) {

            result = invoiceDB.insert_invoice_item_details(Constants.DCReferenceKey,
                    Name, checkNullEmpty(Price), checkNullEmpty(Quantity),
                    Unit, checkNullEmpty(Discount), checkNullEmpty(Tax));


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

            result = invoiceDB.insert_invoice_item_details(Integer.valueOf(itemId),
                    Name, checkNullEmpty(Price), checkNullEmpty(Quantity),
                    Unit, checkNullEmpty(Discount), checkNullEmpty(Tax));

            if (result) {
                //  Toast.makeText(CreateBioDataPDActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                System.out.println("Updated");
            } else {
                Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show();
            }


        }

        return result;
    }

    private void ViewHandler() {

        name = findViewById(R.id.item_name);
        price = findViewById(R.id.item_price);
        quantity = findViewById(R.id.item_quantity);
        unit = findViewById(R.id.item_unit);
        discount = findViewById(R.id.item_discount);
        tax = findViewById(R.id.item_tax);
        save_next_btn = findViewById(R.id.save_next_btn);

        nameLayout = findViewById(R.id.item_name_layout);
        priceLayout = findViewById(R.id.item_price_layout);
        quantityLayout = findViewById(R.id.item_quantity_layout);
    }

    public String checkNullEmpty(String input) {
        if (input == null || input.isEmpty()) {
            return "0";
        } else {
            return input;
        }
    }


}