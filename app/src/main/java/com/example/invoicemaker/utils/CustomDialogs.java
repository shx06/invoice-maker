package com.example.invoicemaker.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.invoicemaker.R;
import com.google.android.material.textfield.TextInputEditText;

public class CustomDialogs {

    Context context;
    Dialog dialog;

    public CustomDialogs(Context context) {
        this.context = context;
    }

    public void displayDiscountDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_discount_layout);
        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.background_hollow_white_round));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        TextInputEditText inputField = dialog.findViewById(R.id.input_area);
        Spinner optionsSpinner = dialog.findViewById(R.id.spinner);
        AppCompatButton cancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton save = dialog.findViewById(R.id.btn_save);

        String[] optionsList = {"Percentage", "Amount"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionsSpinner.setAdapter(adapter);

        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, optionsList[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        save.setOnClickListener(v2 -> {
            if (inputField.getText() != null && inputField.getText().length() > 0) {
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Please input discount amount.", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(v3 -> dialog.dismiss());
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
