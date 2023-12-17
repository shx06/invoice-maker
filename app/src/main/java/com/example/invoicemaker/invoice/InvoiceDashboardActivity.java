package com.example.invoicemaker.invoice;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.invoicemaker.R;
import com.example.invoicemaker.activity.ClientActivity;
import com.example.invoicemaker.activity.CompanyProfileActivity;
import com.example.invoicemaker.activity.InvoiceInfoActivity;
import com.example.invoicemaker.activity.ItemsActivity;
import com.example.invoicemaker.adapters.ItemsAdapter;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.model.SingleItemModel;
import com.example.invoicemaker.utils.Constants;
import com.example.invoicemaker.utils.CustomDialogs;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDashboardActivity extends AppCompatActivity {

    LinearLayout headerLayout, businessLayout, clientLayout, itemsLayout,
            discountLayout, currencyLayout, termsLayout, companyDataLayout, clientDataLayout;

    TextView feedBack, invoiceName, invoiceDueDate, invoiceCreatedDate, companyReplacable, clientReplacable,
            companyName, companyWebsite, companyAddress, clientName, clientAdd1, clientAdd2, subTotal;

    RecyclerView itemsDataRecyclerView;
    ItemsAdapter itemsAdapter;

    CustomDialogs customDialogs;

    List<SingleItemModel> dataItemsList;

    InvoiceDB invoiceDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invoice);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Add Invoice");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());


        ////****************** Data manger  -------------------------->
        invoiceDB = new InvoiceDB(getApplicationContext());

        if (Constants.Insertion_Update_Flag) {

            boolean result = invoiceDB.insertData_data_controller("System", Constants.DefaultFlag);
            if (result) {

                Toast.makeText(this, "Data manger Active", Toast.LENGTH_SHORT).show();

                Cursor cur = invoiceDB.getLastRow_data_controller();
                if (cur.getCount() > 0) {

                    while (cur.moveToNext()) {
                        Constants.DCReferenceKey = cur.getInt(0);
                    }

                    cur.close();
                }


                Constants.CreateInvoiceKey = Constants.DCReferenceKey;

            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }

        }
        ////****************** Data manger  -------------------------->


        findViewById(R.id.close_activity).setOnClickListener(v -> {
            finish();
        });

        customDialogs = new CustomDialogs(InvoiceDashboardActivity.this);

        handleElements();

        headerLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InvoiceInfoActivity.class);
            startActivity(intent);

            /*Intent intent = new Intent(getApplicationContext(), DisplayLayoutsActivity.class);
            intent.putExtra(FinalStaticConstants.SCREEN_TYPE, FinalStaticConstants.HEADER_SCREEN);
            startActivity(intent);*/
        });

        businessLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CompanyProfileActivity.class);
            startActivity(intent);
        });

        clientLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ClientActivity.class);
            startActivity(intent);
        });

        itemsLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ItemsActivity.class);
            startActivity(intent);
        });

        termsLayout.setOnClickListener(v -> {
        });

        discountLayout.setOnClickListener(v -> {
            customDialogs.displayDiscountDialog();
        });

        currencyLayout.setOnClickListener(v -> {
            customDialogs.displayCurrencyDialog();
        });

//        Extra fields needs to delete
//        paymentLayout.setOnClickListener(v -> {
//        });
//
//        taxLayout.setOnClickListener(v -> {
//            customDialogs.displayTaxDialog();
//        });
//        shippingLayout.setOnClickListener(v -> {
//            customDialogs.displayShippingDialog();
//        });

        fetchInvoiceData();
    }

    private void handleElements() {
        subTotal = findViewById(R.id.sub_total);

        companyReplacable = findViewById(R.id.company_replacable);
        clientReplacable = findViewById(R.id.client_replacable);

        invoiceName = findViewById(R.id.invoice_title);
        invoiceDueDate = findViewById(R.id.invoice_due_date);
        invoiceCreatedDate = findViewById(R.id.invoice_created_Date);
        companyName = findViewById(R.id.company_name);
        companyAddress = findViewById(R.id.company_address);
        companyWebsite = findViewById(R.id.company_website);

        clientName = findViewById(R.id.client_name);
        clientAdd1 = findViewById(R.id.client_add1);
        clientAdd2 = findViewById(R.id.client_add2);

        itemsDataRecyclerView = findViewById(R.id.items_list);

        headerLayout = findViewById(R.id.invoice_header_layout);
        businessLayout = findViewById(R.id.add_business_layout);
        clientLayout = findViewById(R.id.add_client_layout);
        itemsLayout = findViewById(R.id.add_item_layout);
        discountLayout = findViewById(R.id.discount_layout);
        currencyLayout = findViewById(R.id.currency_layout);
        termsLayout = findViewById(R.id.terms_conditions_layout);
        companyDataLayout = findViewById(R.id.company_data_layout);
        clientDataLayout = findViewById(R.id.client_data_layout);
//        taxLayout = findViewById(R.id.tax_layout);
//        shippingLayout = findViewById(R.id.shipping_layout);
//        paymentLayout = findViewById(R.id.payment_methods_layout);
//        attachmentLayout = findViewById(R.id.attachment_layout);
        feedBack = findViewById(R.id.add_feedback);

        itemsLayout.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ItemsActivity.class));
        });
    }

    private void fetchInvoiceData() {
        if (Constants.Invoice_info_Active) {

            Cursor cur = invoiceDB.getRows_invoice_info(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                System.out.println("row_counted invoice " + cur.getCount());

                while (cur.moveToNext()) {
                    invoiceName.setText(cur.getString(2));
                    invoiceCreatedDate.setText(cur.getString(4));
                    invoiceDueDate.setText(cur.getString(6));
                }


            }

            cur.close();
        }

        if (Constants.Company_profile_Active) {

            Cursor cur = invoiceDB.getRows_company(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                System.out.println("row_counted " + cur.getCount());
                companyReplacable.setVisibility(View.GONE);
                companyDataLayout.setVisibility(View.VISIBLE);


                while (cur.moveToNext()) {
                    companyName.setText(cur.getString(2));
                    companyAddress.setText(cur.getString(5));
                    companyWebsite.setText(cur.getString(7));
                }


            }

            cur.close();

        }

        if (Constants.Client_Active) {

            Cursor cur = invoiceDB.getRows_client(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                System.out.println("row_counted " + cur.getCount());
                clientReplacable.setVisibility(View.GONE);
                clientDataLayout.setVisibility(View.VISIBLE);


                while (cur.moveToNext()) {
                    clientName.setText(cur.getString(2));
                    clientAdd1.setText(cur.getString(5));
                    clientAdd2.setText(cur.getString(6));
                }


            }

            cur.close();
        }

        dataItemsList = new ArrayList<>();

        Cursor m_cur = invoiceDB.getRows_invoice_item(Constants.DCReferenceKey);

        double netItemsPrice, totalItemsPrice = 0.0, totalItemsDiscount = 0.0;

        if (m_cur.getCount() > 0) {
            while (m_cur.moveToNext()) {

                totalItemsPrice += Double.parseDouble(m_cur.getString(3)) * Double.parseDouble(m_cur.getString(4));
                totalItemsDiscount += Double.parseDouble(m_cur.getString(6));

                dataItemsList.add(new SingleItemModel(m_cur.getInt(0), m_cur.getInt(1),
                        m_cur.getString(2), m_cur.getString(3), m_cur.getString(4),
                        m_cur.getString(5), m_cur.getString(6), m_cur.getString(7)));
            }

            netItemsPrice = totalItemsPrice - (totalItemsDiscount * 100);

            subTotal.setText(("$ " + netItemsPrice));

        }
        m_cur.close();

        ItemsRecyclerView();
    }


    private void ItemsRecyclerView() {
        itemsAdapter = new ItemsAdapter(dataItemsList, this);
        itemsDataRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        itemsDataRecyclerView.setAdapter(itemsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchInvoiceData();
    }
}