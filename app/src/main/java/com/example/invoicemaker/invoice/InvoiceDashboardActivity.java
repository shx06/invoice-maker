package com.example.invoicemaker.invoice;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.invoicemaker.activity.TemplateSelectionActivity;
import com.example.invoicemaker.adapters.InvoiceItemsAdapter;
import com.example.invoicemaker.adapters.ItemsAdapter;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.model.SingleItemInvoiceLinkedModel;
import com.example.invoicemaker.model.SingleItemModel;
import com.example.invoicemaker.utils.Constants;
import com.example.invoicemaker.utils.CustomDialogs;
import com.example.invoicemaker.utils.StaticConstants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDashboardActivity extends AppCompatActivity {

    LinearLayout headerLayout, businessLayout, clientLayout, itemsLayout,
            discountLayout, currencyLayout, termsLayout, companyDataLayout, clientDataLayout;

    TextView feedBack, invoiceName, invoiceDueDate, invoiceCreatedDate, companyReplacable, clientReplacable,
            companyName, companyWebsite, companyAddress, clientName, clientAdd1, clientAdd2, subTotal, finalAmt;

    RecyclerView itemsDataRecyclerView;
    InvoiceItemsAdapter itemsAdapter;

    CustomDialogs customDialogs;

    List<SingleItemInvoiceLinkedModel> dataItemsList;

    InvoiceDB invoiceDB;

    AppCompatButton btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invoice);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Add Invoice");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());

        btn_save = findViewById(R.id.btn_save);

        handleElements();


        ////****************** Data manger  -------------------------->
        invoiceDB = new InvoiceDB(getApplicationContext());

        ////****************** Data manger  -------------------------->


        findViewById(R.id.close_activity).setOnClickListener(v -> {
            finish();
        });

        customDialogs = new CustomDialogs(InvoiceDashboardActivity.this);

        headerLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InvoiceInfoActivity.class);
            startActivity(intent);
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


        btn_save.setOnClickListener(view -> {
            Intent j = new Intent(InvoiceDashboardActivity.this, TemplateSelectionActivity.class);
            startActivity(j);

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
        finalAmt = findViewById(R.id.final_amount);

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

        //   get invoice data -------------------------------------------------------------------------

        Constants.Invoice_info_Active = false;

        Cursor curInvInfo = invoiceDB.getRows_invoice_info(Constants.DCReferenceKey);

        if (curInvInfo.getCount() > 0) {
            Constants.Invoice_info_Active = true;
            while (curInvInfo.moveToNext()) {
                invoiceName.setText(curInvInfo.getString(2));
                invoiceCreatedDate.setText(curInvInfo.getString(4));
                invoiceDueDate.setText(curInvInfo.getString(6));
            }


        }

        curInvInfo.close();


        //   get company data -------------------------------------------------------------------------

        Cursor curComp = invoiceDB.getRows_company(Constants.DCReferenceKey);

        Constants.Company_profile_Active = false;

        if (curComp.getCount() > 0) {
            Constants.Company_profile_Active = true;
            companyReplacable.setVisibility(View.GONE);
            companyDataLayout.setVisibility(View.VISIBLE);


            while (curComp.moveToNext()) {
                companyName.setText(curComp.getString(2));
                companyAddress.setText(curComp.getString(5));
                companyWebsite.setText(curComp.getString(7));
            }


        }

        curComp.close();


        //   get client data -------------------------------------------------------------------------

        Constants.Client_Active = false;

        Cursor curClient = invoiceDB.getRows_client(Constants.DCReferenceKey);

        if (curClient.getCount() > 0) {
            clientReplacable.setVisibility(View.GONE);
            clientDataLayout.setVisibility(View.VISIBLE);
            Constants.Client_Active = true;


            while (curClient.moveToNext()) {
                clientName.setText(curClient.getString(2));
                clientAdd1.setText(curClient.getString(5));
                clientAdd2.setText(curClient.getString(6));
            }


        }

        curClient.close();


        // items data --------------------------------------------------------------------


        Constants.itemsActive = false;

        dataItemsList = new ArrayList<>();

        Cursor couItems = invoiceDB.getRows_invoice_items_link(Constants.DCReferenceKey);

        if (couItems.getCount() > 0) {
            Constants.itemsActive = false;
            while (couItems.moveToNext()) {

                dataItemsList.add(new SingleItemInvoiceLinkedModel(couItems.getInt(0), couItems.getInt(1), couItems.getInt(2)));
            }

        }
        couItems.close();

        ItemsRecyclerView();

        String discType = null;
        Double finalDiscount = 0.0;

        Cursor curDiscount = invoiceDB.getRows_invoice_discount_by_dcId(Constants.DCReferenceKey);


        if (curDiscount.getCount() > 0) {
            while (curDiscount.moveToNext()) {
                if (curDiscount.getString(2) != null) {
                    discType = curDiscount.getString(2);
                    finalDiscount = curDiscount.getDouble(3);
                }
            }
        }

        curDiscount.close();

        final Handler handler = new Handler(Looper.getMainLooper());
        String finalDiscType = discType;
        Double finalDisc = finalDiscount;
        handler.postDelayed(() -> {
            updateInvoiceFinalDiscount(finalDiscType, finalDisc);
            updateInvoiceFinalAmount();
        }, 300);

    }

    private void updateInvoiceFinalDiscount(String finalDiscType, Double finalDisc) {
        if (finalDiscType != null && finalDiscType.equals(StaticConstants.DISCOUNT_PERCENTAGE)) {
            Constants.FinalInvoiceDiscount = finalDisc * Constants.TotalInvoicePrice / 100;

        } else {
            Constants.FinalInvoiceDiscount = finalDisc;
        }
    }

    public void updateInvoiceFinalAmount() {

        Log.d(TAG, "called : updateInvoiceFinalAmount");

        finalAmt.setText("$ " + new DecimalFormat("##.##").format(Constants.TotalInvoicePrice - Constants.FinalInvoiceDiscount));

    }

    public void updateInvoiceFromAdapter() {
        Log.d(TAG, "called : updateInvoiceFromAdapter");

        subTotal.setText("$ " + new DecimalFormat("##.##").format(Constants.TotalInvoicePrice));
    }


    private void ItemsRecyclerView() {
        itemsAdapter = new InvoiceItemsAdapter(dataItemsList, this);
        itemsDataRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        itemsDataRecyclerView.setAdapter(itemsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchInvoiceData();
    }
}