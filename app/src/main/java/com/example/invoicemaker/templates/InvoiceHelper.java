package com.example.invoicemaker.templates;

import com.example.invoicemaker.model.SingleItemModel;

import java.sql.Blob;
import java.util.ArrayList;

public class InvoiceHelper {

    // todo : keep here only that variable which used for creation of Invoice.

    public static String invTitle;
    public static String invNo;
    public static String invCreatedDate;
    public static String invDueDate;
    public static String invDueTerm;
    public static String invoicePo;
    public static String compName;
    public static String compEmail;
    public static String compPhone;
    public static String compAdd1;
    public static String compAdd2;
    public static String compWebsite;
    public static byte[] compImage;
    public static String clientName;
    public static String clientEmail;
    public static String clientPhone;
    public static String clientBilAddress1;
    public static String clientBilAddress2;
    public static String clientShipAddress1;
    public static String clientShipAddress2;
    public static String clientDetails;
    public static String discountType;
    public static double discountValue;
    public static ArrayList<SingleItemModel> itemsList = new ArrayList<>();

    public static String countryName;
    public static String countrySymbol;
    public static String currencySymbol;
}
