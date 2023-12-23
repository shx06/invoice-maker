package com.example.invoicemaker.utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.adapters.CurrencyAdapter;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.invoice.InvoiceDashboardActivity;
import com.example.invoicemaker.model.CurrencyModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CustomDialogs {

    Context context;
    Dialog dialog;
    InvoiceDB invoiceDB;

    public CustomDialogs(Context context) {
        this.context = context;
    }

    public void displayDiscountDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_discount_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        EditText inputField = dialog.findViewById(R.id.discount_input_area);
        Spinner optionsSpinner = dialog.findViewById(R.id.spinner);
        AppCompatButton cancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton save = dialog.findViewById(R.id.btn_save);

        String[] optionsList = {StaticConstants.DISCOUNT_PERCENTAGE, StaticConstants.DISCOUNT_AMOUNT};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionsSpinner.setAdapter(adapter);
        inputField.setText("sldfjhs");

        if(Constants.FinalInvoiceDiscountType != null) {
            Log.d(TAG, "displayDiscountDialog: "+Constants.FinalInvoiceDiscount);
            if (Constants.FinalInvoiceDiscountType.equals(StaticConstants.DISCOUNT_PERCENTAGE)) optionsSpinner.setSelection(0);
            else optionsSpinner.setSelection(1);
        }

        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.FinalInvoiceDiscountType = optionsList[position];
                if (optionsList[position].equals(StaticConstants.DISCOUNT_PERCENTAGE)) {
                    inputField.setText("0.0");
                } else {
                    inputField.setText("0.0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        save.setOnClickListener(v2 -> {
            if (inputField.getText() != null && inputField.getText().length() > 0) {

                invoiceDB = new InvoiceDB(context);


                Cursor cur = invoiceDB.getRows_invoice_discount_by_dcId(Constants.DCReferenceKey);

                boolean result;

                if (cur.getCount() > 0) {
                    System.out.println("row_counted invoice " + cur.getCount());

                    while (cur.moveToNext()) {

                        result = invoiceDB.update_discount_details(Constants.DCReferenceKey, Constants.FinalInvoiceDiscountType, inputField.getText().toString());


                        if (Constants.FinalInvoiceDiscountType.equals(StaticConstants.DISCOUNT_PERCENTAGE)) {
                            Constants.FinalInvoiceDiscount = Double.parseDouble(inputField.getText().toString()) * Constants.TotalInvoicePrice / 100;
                        } else {
                            Constants.FinalInvoiceDiscount = Double.parseDouble(inputField.getText().toString());
                        }

                        Log.d(TAG, "fetchInvoiceData: 99 " + Constants.FinalInvoiceDiscount);


                        ((InvoiceDashboardActivity) context).updateInvoiceFinalAmount();

                        if (result) {
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {

                    result = invoiceDB.insert_discount_details(Constants.DCReferenceKey, Constants.FinalInvoiceDiscountType, inputField.getText().toString());

                    if (result) {
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                cur.close();

                dialog.dismiss();
            } else {
                Toast.makeText(context, "Please input discount amount.", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(v3 -> dialog.dismiss());
        dialog.show();
    }

    public void displayCurrencyDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.language_list_layout);
        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.background_solid_white_round));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        invoiceDB = new InvoiceDB(context);

        RecyclerView languageRecyclerView = dialog.findViewById(R.id.language_list_recycler_view);
        TextView cancel = dialog.findViewById(R.id.cancel);

        List<CurrencyModel> list = new ArrayList<>();
        list.add(new CurrencyModel("India", "\u20b9", "ind"));
        list.add(new CurrencyModel("United States", "$", "usd"));
        list.add(new CurrencyModel("United Kingdom", "£", "uk"));

        CurrencyAdapter currencyAdapter = new CurrencyAdapter(list, context);
        languageRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
        languageRecyclerView.setAdapter(currencyAdapter);

        cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public void displayShippingDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_shipping_layout);
        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.background_hollow_white_round));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        TextInputEditText inputField = dialog.findViewById(R.id.input_area);
        AppCompatButton cancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton save = dialog.findViewById(R.id.btn_save);

        save.setOnClickListener(v2 -> {
            if (inputField.getText() != null && inputField.getText().length() > 0) {
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Please input shipping amount.", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(v3 -> dialog.dismiss());
        dialog.show();
    }

    public void displayTaxDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_tax_layout);
        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.background_hollow_white_round));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        EditText taxName = dialog.findViewById(R.id.tax_name);
        EditText taxRate = dialog.findViewById(R.id.tax_rate);
        AppCompatButton cancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton save = dialog.findViewById(R.id.btn_save);

        save.setOnClickListener(v2 -> {
            if (taxName.getText() != null && taxName.getText().length() > 0 &&
                    taxRate.getText() != null && taxRate.getText().length() > 0) {
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Please input both fields.", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(v3 -> dialog.dismiss());
        dialog.show();
    }
}
