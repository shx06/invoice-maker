package com.example.invoicemaker.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.adapters.SelectTemplateAdapter;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.model.SelectTemplateModel;
import com.example.invoicemaker.model.SingleItemInvoiceLinkedModel;
import com.example.invoicemaker.model.SingleItemModel;
import com.example.invoicemaker.templates.InvoiceHelper;
import com.example.invoicemaker.utils.Constants;
import com.example.invoicemaker.utils.StaticConstants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplateSelectionActivity extends AppCompatActivity {

    RecyclerView recycler_view;

    List<SelectTemplateModel> selectTemplateList = new ArrayList<>();

    SelectTemplateAdapter selectTemplateAdapter;
    InvoiceDB invoiceDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_selection);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Select Template");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());


        recycler_view = findViewById(R.id.recycler_view);

        invoiceDB = new InvoiceDB(this);

        selectTemplateList.add(new SelectTemplateModel(R.drawable.ic_upload_image, StaticConstants.TEMPLATE_1));


        GridLayoutManager gridLayoutManager = new GridLayoutManager(TemplateSelectionActivity.this, 2, GridLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(gridLayoutManager);
        selectTemplateAdapter = new SelectTemplateAdapter(selectTemplateList, TemplateSelectionActivity.this, TemplateSelectionActivity.this);
        recycler_view.setAdapter(selectTemplateAdapter);

        permissionLooker();

        // here need to write the code to fetch all data and store into static variable
        FetchDataForInvoiceCreation(Constants.DCReferenceKey);


    }

    void FetchDataForInvoiceCreation(int dc_id) {

        //   get invoice data -------------------------------------------------------------------------

        Cursor curInvInfo = invoiceDB.getRows_invoice_info(Constants.DCReferenceKey);
        if (curInvInfo.getCount() > 0) {
            while (curInvInfo.moveToNext()) {
                InvoiceHelper.invTitle = curInvInfo.getString(2);
                InvoiceHelper.invNo = curInvInfo.getString(3);
                InvoiceHelper.invCreatedDate = curInvInfo.getString(4);
                InvoiceHelper.invDueTerm = curInvInfo.getString(5);
                InvoiceHelper.invDueDate = curInvInfo.getString(6);
                InvoiceHelper.invoicePo = curInvInfo.getString(7);
            }
        }
        curInvInfo.close();

        //   get company data -------------------------------------------------------------------------

        Cursor curComp = invoiceDB.getRows_company(Constants.DCReferenceKey);
        if (curComp.getCount() > 0) {
            while (curComp.moveToNext()) {
                InvoiceHelper.compName = curComp.getString(2);
                InvoiceHelper.compEmail = curComp.getString(3);
                InvoiceHelper.compPhone = curComp.getString(4);
                InvoiceHelper.compAdd1 = curComp.getString(5);
                InvoiceHelper.compAdd2 = curComp.getString(6);
                InvoiceHelper.compWebsite = curComp.getString(7);
                InvoiceHelper.compImage = curComp.getBlob(8);
            }
        }
        curComp.close();

        //   get client data -------------------------------------------------------------------------

        Cursor curClient = invoiceDB.getRows_client(Constants.DCReferenceKey);
        if (curClient.getCount() > 0) {
            while (curClient.moveToNext()) {
                InvoiceHelper.clientName = curClient.getString(2);
                InvoiceHelper.clientEmail = curClient.getString(3);
                InvoiceHelper.clientPhone = curClient.getString(4);
                InvoiceHelper.clientBilAddress1 = curClient.getString(5);
                InvoiceHelper.clientBilAddress2 = curClient.getString(6);
                InvoiceHelper.clientShipAddress1 = curClient.getString(7);
                InvoiceHelper.clientShipAddress2 = curClient.getString(8);
                InvoiceHelper.clientDetails = curClient.getString(9);
            }
        }
        curClient.close();

        // items data --------------------------------------------------------------------

        Cursor couItems = invoiceDB.getRows_invoice_item_byDcId(Constants.DCReferenceKey);
        if (couItems.getCount() > 0) {
//            ArrayList<SingleItemModel> itemsList = new ArrayList<>();
            while (couItems.moveToNext()) {
//                Log.d(TAG, "FetchDataForInvoiceCreation: " + couItems.getString(3));
                InvoiceHelper.itemsList.add(new SingleItemModel(couItems.getInt(0), couItems.getInt(1), couItems.getString(2), couItems.getString(3), couItems.getString(4), couItems.getString(5), couItems.getString(6), couItems.getString(7)));
//                itemsList.add(new SingleItemModel(curClient.getInt(0), curClient.getInt(1), curClient.getString(2), curClient.getString(3), curClient.getString(4), curClient.getString(5), curClient.getString(6), curClient.getString(7)));
            }
//            InvoiceHelper.itemsList = itemsList;
        }
        couItems.close();

        // items discount --------------------------------------------------------------------

        Cursor curDiscount = invoiceDB.getRows_invoice_discount_by_dcId(Constants.DCReferenceKey);
        if (curDiscount.getCount() > 0) {
            while (curDiscount.moveToNext()) {
                InvoiceHelper.discountType = curDiscount.getString(2);
                InvoiceHelper.discountValue = curDiscount.getDouble(3);
            }
        }
        curDiscount.close();

        // currency data --------------------------------------------------------------------
        Cursor curCurrency = invoiceDB.getRows_currency(dc_id);
        if (curCurrency.getCount() > 0) {
            while (curCurrency.moveToNext()) {
                InvoiceHelper.countryName = curCurrency.getString(2);
                InvoiceHelper.countrySymbol = curCurrency.getString(3);
                InvoiceHelper.currencySymbol = curCurrency.getString(4);
            }
        }
        curCurrency.close();

    }

    public void TemplateSelection(String selected_template) {

        Intent go = new Intent(TemplateSelectionActivity.this, ViewPDFPreviewActivity.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        go.putExtra("fromWhereToCome", StaticConstants.FromWhereToCome);
        go.putExtra("selected_template", selected_template);
        startActivity(go);


    }


    private void permissionLooker() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            System.out.println("Android 13 or more!! Permission Not Required!!");
        } else {

            Dexter.withContext(this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse response) {

                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {
                    Toast.makeText(TemplateSelectionActivity.this, "Required For PDf Creation" + "\n" + response.getPermissionName() + " Need Enable", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();

        }

    }


}