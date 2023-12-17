package com.example.invoicemaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.invoicemaker.R;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;

public class ClientActivity extends AppCompatActivity {

    TextInputEditText clientName, clientEmail, clientBillingAddress1, clientBillingAddress2, clientShippingAddress1, clientShippingAddress2, clientPhone, clientDetails;
    Button save_next_btn;
    InvoiceDB invoiceDB;
    TextView toolbarHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Add Client");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());
        // new testing to push




        //checking
        // hello new test changes
        clientName = findViewById(R.id.client_name);
        clientEmail = findViewById(R.id.client_email);
        clientBillingAddress1 = findViewById(R.id.client_billing_address_1);
        clientBillingAddress2 = findViewById(R.id.client_billing_address_2);
        clientShippingAddress1 = findViewById(R.id.client_shipping_address_1);
        clientShippingAddress2 = findViewById(R.id.client_shipping_address_2);
        clientPhone = findViewById(R.id.client_phone);
        clientDetails = findViewById(R.id.client_details);
        save_next_btn = findViewById(R.id.save_next_btn);

        invoiceDB = new InvoiceDB(getApplicationContext());

        FetchDataAndSet();

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

        if (Constants.Insertion_Update_Flag && !(Constants.Client_Active)) {

            result = invoiceDB.insert_client_details(Constants.DCReferenceKey,
                    clientName.getText().toString(), clientEmail.getText().toString(), clientBillingAddress1.getText().toString(),
                    clientBillingAddress2.getText().toString(), clientShippingAddress1.getText().toString(),
                    clientShippingAddress2.getText().toString(), clientPhone.getText().toString(), clientDetails.getText().toString());


            if (result) {
                Constants.Client_Active = true;
                boolean u_result = invoiceDB.update_data_controller(Constants.DCReferenceKey, Constants.Flag);

                if (u_result) {
                    System.out.println("saved");
//                     Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
            }

        } else {


            result = invoiceDB.update_client_details(Constants.DCReferenceKey,
                    clientName.getText().toString(), clientEmail.getText().toString(), clientPhone.getText().toString(),
                    clientBillingAddress1.getText().toString(), clientBillingAddress2.getText().toString(),
                    clientShippingAddress1.getText().toString(), clientShippingAddress2.getText().toString(), clientDetails.getText().toString());

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

        if (Constants.Client_Active) {
            save_next_btn.setText("Update");
            toolbarHeader.setText("Update Client");

            Cursor cur = invoiceDB.getRows_client(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                System.out.println("row_counted " + cur.getCount());


                while (cur.moveToNext()) {
                    clientName.setText(cur.getString(2));
                    clientEmail.setText(cur.getString(3));
                    clientPhone.setText(cur.getString(4));
                    clientBillingAddress1.setText(cur.getString(5));
                    clientBillingAddress2.setText(cur.getString(6));
                    clientShippingAddress1.setText(cur.getString(7));
                    clientShippingAddress2.setText(cur.getString(8));
                    clientDetails.setText(cur.getString(9));
                }


            }

            cur.close();

        }


    }
}