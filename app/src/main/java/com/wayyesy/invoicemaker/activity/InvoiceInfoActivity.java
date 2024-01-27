package com.wayyesy.invoicemaker.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import android.database.Cursor;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.wayyesy.invoicemaker.R;
import com.wayyesy.invoicemaker.db.InvoiceDB;
import com.wayyesy.invoicemaker.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class InvoiceInfoActivity extends AppCompatActivity {

    TextInputEditText invoiceName, invoiceNo, invoicePO;

    TextView invoiceCreatedDate, invoiceDueDate;
    Spinner invoiceDueTerms;
    int day, month, year;
    Button save_next_btn;
    private String selectedInvoiceDueTerms;


    InvoiceDB invoiceDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_info);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Invoice Info");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());

        invoiceName = findViewById(R.id.invoice_name);
        invoiceNo = findViewById(R.id.invoice_no);
        invoiceCreatedDate = findViewById(R.id.invoice_created_date);
        invoiceDueTerms = findViewById(R.id.invoice_due_terms);
        invoiceDueDate = findViewById(R.id.invoice_due_date);
        invoicePO = findViewById(R.id.p_o);
        save_next_btn = findViewById(R.id.save_next_btn);

        invoiceNo.setText("INV0000" + Constants.InvoiceName);

        invoiceDB = new InvoiceDB(getApplicationContext());

        FetchDataAndSet();

        save_next_btn.setOnClickListener(view -> {

            boolean result = false;

            result = savePersonalDetails();


            if (result) {
                finish();
            }

        });

        Calendar calendar = Calendar.getInstance();

        invoiceCreatedDate.setOnClickListener(v -> {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(InvoiceInfoActivity.this, (datePicker, y, m, d) -> {
                int m1 = m + 1;
                invoiceCreatedDate.setText(d + "/" + m1 + "/" + y);
            }, year, month, day);
            datePickerDialog.show();
        });

        invoiceDueDate.setOnClickListener(v -> {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(InvoiceInfoActivity.this, (datePicker, y, m, d) -> {
                int m1 = m + 1;
                invoiceDueDate.setText(d + "/" + m1 + "/" + y); }, year, month, day);
            datePickerDialog.show();
        });

        String[] optionsList = getResources().getStringArray(R.array.due_date);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        invoiceDueTerms.setAdapter(adapter);

        invoiceDueTerms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected: "+ parent.getItemAtPosition(position));
                selectedInvoiceDueTerms = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private boolean savePersonalDetails() {
        boolean result;

        if (!(Constants.Invoice_info_Active)) {

            result = invoiceDB.insert_invoice_info_details(Constants.DCReferenceKey,
                    invoiceName.getText().toString(), invoiceNo.getText().toString(), invoiceCreatedDate.getText().toString(),
                    selectedInvoiceDueTerms, invoiceDueDate.getText().toString(), invoicePO.getText().toString());


            if (result) {
                Constants.Invoice_info_Active = true;
                boolean u_result = invoiceDB.update_data_controller(Constants.DCReferenceKey, Constants.Flag);

                if (u_result) {
                    System.out.println("saved");
                    // Toast.makeText(CreateBioDataPDActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
            }

        } else {


            result = invoiceDB.update_invoice_info_details(Constants.DCReferenceKey,
                    invoiceName.getText().toString(), invoiceNo.getText().toString(), invoiceCreatedDate.getText().toString(),
                    selectedInvoiceDueTerms, invoiceDueDate.getText().toString(), invoicePO.getText().toString());

            if (result) {
                //  Toast.makeText(CreateBioDataPDActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                System.out.println("Updated");
            } else {
                Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show();
            }


        }


        return result;

    }


    private void FetchDataAndSet() {

        if (Constants.Invoice_info_Active) {
            save_next_btn.setText("Update");

            Cursor cur = invoiceDB.getRows_invoice_info(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                System.out.println("row_counted invoice " + cur.getCount());

                while (cur.moveToNext()) {
                    invoiceName.setText(cur.getString(2));
                    invoiceNo.setText(cur.getString(3));
                    invoiceCreatedDate.setText(cur.getString(4));
                    invoiceDueDate.setText(cur.getString(6));
                    invoicePO.setText(cur.getString(7));
                }


            }

            cur.close();

        }


    }

}