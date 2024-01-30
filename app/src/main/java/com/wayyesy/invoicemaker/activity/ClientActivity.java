package com.wayyesy.invoicemaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.wayyesy.invoicemaker.R;

import com.google.android.material.textfield.TextInputLayout;
import com.wayyesy.invoicemaker.db.InvoiceDB;
import com.wayyesy.invoicemaker.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ClientActivity extends AppCompatActivity {

    TextInputEditText clientName, clientEmail, clientBillingAddress1, clientBillingAddress2, clientShippingAddress1, clientShippingAddress2, clientPhone, clientDetails;
    TextInputLayout clientNameLayout;
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

        clientNameLayout = findViewById(R.id.client_name_layout);

        invoiceDB = new InvoiceDB(getApplicationContext());

        FetchDataAndSet();

        save_next_btn.setOnClickListener(view -> {

            boolean result = false;

            if(clientName != null && clientName.length() > 0) {
                result = savePersonalDetails();
            } else {
                clientNameLayout.setHelperText("Please enter client name.");
                clientName.requestFocus();
            }
            if (result) {
                finish();
            }

        });
    }

    private boolean savePersonalDetails() {
        boolean result;

        String name = Objects.requireNonNull(clientName.getText().toString());
        String email = Objects.requireNonNull(clientEmail.getText().toString());
        String bilAdd1 = Objects.requireNonNull(clientBillingAddress1.getText().toString());
        String bilAdd2 = Objects.requireNonNull(clientBillingAddress2.getText().toString());
        String shipAdd1 = Objects.requireNonNull(clientShippingAddress1.getText().toString());
        String shipAdd2 = Objects.requireNonNull(clientShippingAddress2.getText().toString());
        String phone = Objects.requireNonNull(clientPhone.getText().toString());
        String details = Objects.requireNonNull(clientDetails.getText().toString());

        if (!(Constants.Client_Active)) {

            result = invoiceDB.insert_client_details(Constants.DCReferenceKey, checkNullEmpty(name),
                    checkNullEmpty(email), checkNullEmpty(phone), checkNullEmpty(bilAdd1), checkNullEmpty(bilAdd2),
                    checkNullEmpty(shipAdd1), checkNullEmpty(shipAdd2), checkNullEmpty(details));


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


            result = invoiceDB.update_client_details(Constants.DCReferenceKey, checkNullEmpty(name),
                    checkNullEmpty(email), checkNullEmpty(phone), checkNullEmpty(bilAdd1), checkNullEmpty(bilAdd2),
                    checkNullEmpty(shipAdd1), checkNullEmpty(shipAdd2), checkNullEmpty(details));

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

    public String checkNullEmpty(String input) {
        if(input == null && input.isEmpty()) {
            return "";
        }
        return input;
    }
}