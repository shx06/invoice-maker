package com.wayyesy.invoicemaker.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
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

import com.wayyesy.invoicemaker.R;
import com.wayyesy.invoicemaker.adapters.CurrencyAdapter;
import com.wayyesy.invoicemaker.db.InvoiceDB;
import com.wayyesy.invoicemaker.activity.InvoiceDashboardActivity;
import com.wayyesy.invoicemaker.model.CurrencyModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

        if (Constants.FinalInvoiceDiscountType != null) {
            if (Constants.FinalInvoiceDiscountType.equals(StaticConstants.DISCOUNT_PERCENTAGE))
                optionsSpinner.setSelection(0);
            else optionsSpinner.setSelection(1);
        }

        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.FinalInvoiceDiscountType = optionsList[position];

                if (Constants.FinalInvoiceDiscountType != null) {
                    inputField.setText(String.valueOf(Constants.SelectedInvoiceDiscount));
                } else {
                    if (optionsList[position].equals(StaticConstants.DISCOUNT_PERCENTAGE)) {
                        inputField.setHint("0.0%");
                    } else {
                        inputField.setHint(Constants.InvoiceCurrencySymbol + " 0.0");
                    }
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

                        Constants.SelectedInvoiceDiscount = Double.valueOf(inputField.getText().toString());

                        if (Constants.FinalInvoiceDiscountType.equals(StaticConstants.DISCOUNT_PERCENTAGE)) {
                            Constants.FinalInvoiceDiscount = Double.parseDouble(inputField.getText().toString()) * Constants.TotalInvoicePrice / 100;
                        } else {
                            Constants.FinalInvoiceDiscount = Double.parseDouble(inputField.getText().toString());
                        }

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
                Toast.makeText(context, "Please input discount amount. Put zero for no discount.", Toast.LENGTH_SHORT).show();
            }

            ((InvoiceDashboardActivity) context).fetchInvoiceData();
        });

        cancel.setOnClickListener(v3 -> dialog.dismiss());
        dialog.show();
    }

    public void displayCurrencyDialog(Resources resources) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.language_list_layout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);

        invoiceDB = new InvoiceDB(context);

        RecyclerView languageRecyclerView = dialog.findViewById(R.id.language_list_recycler_view);
        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView save = dialog.findViewById(R.id.save);

        List<CurrencyModel> list = new ArrayList<>();

        String[] currencyList = resources.getStringArray(R.array.currency_array);

        for (String s : currencyList) {
            String[] currency = s.split(", ");
            list.add(new CurrencyModel(currency[0], currency[1], currency[2]));
        }

        CurrencyAdapter currencyAdapter = new CurrencyAdapter(list, context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        languageRecyclerView.setLayoutManager(layoutManager);
        languageRecyclerView.setAdapter(currencyAdapter);

        cancel.setOnClickListener(v -> dialog.dismiss());

        save.setOnClickListener(v -> {
            currencyAdapter.handleCurrency();
            dialog.dismiss();
            ((InvoiceDashboardActivity) context).fetchInvoiceData();
        });

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
