package com.wayyesy.invoicemaker.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wayyesy.invoicemaker.R;
import com.wayyesy.invoicemaker.adapters.SelectTemplateAdapter;
import com.wayyesy.invoicemaker.db.InvoiceDB;
import com.wayyesy.invoicemaker.model.SelectTemplateModel;
import com.wayyesy.invoicemaker.model.SingleItemInvoiceLinkedModel;
import com.wayyesy.invoicemaker.model.SingleItemModel;
import com.wayyesy.invoicemaker.templates.InvoiceHelper;
import com.wayyesy.invoicemaker.utils.Constants;
import com.wayyesy.invoicemaker.utils.StaticConstants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

        selectTemplateList.add(new SelectTemplateModel(R.drawable.invoice_1, StaticConstants.TEMPLATE_1));
        selectTemplateList.add(new SelectTemplateModel(R.drawable.invoice_2, StaticConstants.TEMPLATE_2));
        selectTemplateList.add(new SelectTemplateModel(R.drawable.invoice_3, StaticConstants.TEMPLATE_3));
        selectTemplateList.add(new SelectTemplateModel(R.drawable.invoice_4, StaticConstants.TEMPLATE_4));
        selectTemplateList.add(new SelectTemplateModel(R.drawable.invoice_5, StaticConstants.TEMPLATE_5));
        selectTemplateList.add(new SelectTemplateModel(R.drawable.invoice_5, StaticConstants.TEMPLATE_6));


        GridLayoutManager gridLayoutManager = new GridLayoutManager(TemplateSelectionActivity.this, 2, GridLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(gridLayoutManager);
        selectTemplateAdapter = new SelectTemplateAdapter(selectTemplateList, TemplateSelectionActivity.this, TemplateSelectionActivity.this);
        recycler_view.setAdapter(selectTemplateAdapter);

        permissionLooker();

        // here need to write the code to fetch all data and store into static variable
        clearInvoiceHelperClass();
        FetchDataForInvoiceCreation(Constants.DCReferenceKey);


    }

    void FetchDataForInvoiceCreation(int dc_id) {

        //   get invoice data -------------------------------------------------------------------------

        Cursor curInvInfo = invoiceDB.getRows_invoice_info(dc_id);
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

        Cursor curComp = invoiceDB.getRows_company(dc_id);
        if (curComp.getCount() > 0) {
            while (curComp.moveToNext()) {
                InvoiceHelper.compName = curComp.getString(2);
                InvoiceHelper.compEmail = curComp.getString(3);
                InvoiceHelper.compPhone = curComp.getString(4);
                InvoiceHelper.compAdd1 = curComp.getString(5);
                InvoiceHelper.compAdd2 = curComp.getString(6);
                InvoiceHelper.compWebsite = curComp.getString(7);
                InvoiceHelper.compImage = curComp.getBlob(8);
                InvoiceHelper.companyTerms = curComp.getString(9);
            }
        }
        curComp.close();

        //   get client data -------------------------------------------------------------------------

        Cursor curClient = invoiceDB.getRows_client(dc_id);
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

        List<SingleItemInvoiceLinkedModel> dataItemsList = new ArrayList<>();
        Cursor countItems = invoiceDB.getRows_invoice_items_link(dc_id);

        if (countItems.getCount() > 0) {
            while (countItems.moveToNext()) {

                dataItemsList.add(new SingleItemInvoiceLinkedModel(countItems.getInt(0), countItems.getInt(1), countItems.getInt(2)));
            }

        }
        countItems.close();

        double netItemsPrice = 0.0;

        for (int i = 0; i < dataItemsList.size(); i++) {
            Cursor couItems = invoiceDB.getRows_invoice_item_byId(dataItemsList.get(i).getInvoice_item_id());

            if (couItems.getCount() > 0) {

                double netItemPrice = 0.0, totalItemPrice = 0.0;

                while (couItems.moveToNext()) {
                    InvoiceHelper.itemsList.add(new SingleItemModel(couItems.getInt(0), couItems.getInt(1),
                            couItems.getString(2), couItems.getString(3), couItems.getString(4),
                            couItems.getString(5), couItems.getString(6), couItems.getString(7)));

                    totalItemPrice = Double.parseDouble(couItems.getString(3)) * Double.parseDouble(couItems.getString(4));

                    double extra = (((Double.parseDouble(couItems.getString(7)) / 100) * totalItemPrice) - ((Double.parseDouble(couItems.getString(6)) / 100) * totalItemPrice));

                    netItemPrice = extra + totalItemPrice;
                    netItemsPrice += netItemPrice;
                }
            }

            couItems.close();
        }

        InvoiceHelper.subTotal = netItemsPrice;

        // items discount --------------------------------------------------------------------

        Cursor curDiscount = invoiceDB.getRows_invoice_discount_by_dcId(dc_id);
        if (curDiscount.getCount() > 0) {
            while (curDiscount.moveToNext()) {

                if (curDiscount.getString(2) != null && curDiscount.getString(2).length() > 0) {

                    if (curDiscount.getString(2).equals(StaticConstants.DISCOUNT_PERCENTAGE)) {
                        InvoiceHelper.discount = Double.parseDouble(curDiscount.getString(3)) / 100 * InvoiceHelper.subTotal;
                    } else {
                        InvoiceHelper.discount = Double.parseDouble(curDiscount.getString(3));
                    }
                }

            }
        }


        curDiscount.close();

        // currency data --------------------------------------------------------------------
        Cursor curCurrency = invoiceDB.getRows_currency(dc_id);
        if (curCurrency.getCount() > 0) {
            while (curCurrency.moveToNext()) {
                if(curCurrency.getString(3)!=null && curCurrency.getString(3).length() > 0) {
                    InvoiceHelper.countryName = curCurrency.getString(2);
                    InvoiceHelper.currencySymbol = curCurrency.getString(3);
                } else {
                    InvoiceHelper.currencySymbol = "₹";
                }
            }
        } else {
            InvoiceHelper.currencySymbol = "₹";
        }
        curCurrency.close();


        InvoiceHelper.finalTotal = InvoiceHelper.subTotal - InvoiceHelper.discount;

    }

    private void clearInvoiceHelperClass() {
        InvoiceHelper.invNo = null;
        InvoiceHelper.invTitle = null;
        InvoiceHelper.invCreatedDate = null;
        InvoiceHelper.invDueTerm = null;
        InvoiceHelper.invDueDate = null;
        InvoiceHelper.invoicePo = null;

        InvoiceHelper.compName = null;
        InvoiceHelper.compEmail = null;
        InvoiceHelper.compPhone = null;
        InvoiceHelper.compAdd1 = null;
        InvoiceHelper.compAdd2 = null;
        InvoiceHelper.compWebsite = null;
        InvoiceHelper.compImage = null;
        InvoiceHelper.companyTerms = null;

        InvoiceHelper.clientName = null;
        InvoiceHelper.clientEmail = null;
        InvoiceHelper.clientPhone = null;
        InvoiceHelper.clientBilAddress1 = null;
        InvoiceHelper.clientBilAddress2 = null;
        InvoiceHelper.clientShipAddress1 = null;
        InvoiceHelper.clientShipAddress2 = null;
        InvoiceHelper.clientDetails = null;

        InvoiceHelper.itemsList.clear();

        InvoiceHelper.discountType = null;
        InvoiceHelper.discountValue = 0;


        InvoiceHelper.countryName = null;
        InvoiceHelper.countrySymbol = null;
        InvoiceHelper.currencySymbol = null;


        InvoiceHelper.subTotal = 0;
        InvoiceHelper.discount = 0;
        InvoiceHelper.finalTotal = 0;
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