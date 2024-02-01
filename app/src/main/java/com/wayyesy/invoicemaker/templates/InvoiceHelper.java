package com.wayyesy.invoicemaker.templates;

import com.wayyesy.invoicemaker.model.SingleItemModel;

import java.util.ArrayList;

public class InvoiceHelper {

    // todo : keep here only that variable which used for creation of Invoice.


    //  todo : Table invoice info
    public static String invTitle;
    public static String invNo;
    public static String invCreatedDate;
    public static String invDueDate;
    public static String invDueTerm;
    public static String invoicePo;
    public static String compName;
    public static String compEmail;



    // todo :  table company
    public static String compPhone;
    public static String compAdd1;
    public static String compAdd2;
    public static String compWebsite;
    public static byte[] compImage;



    // todo :  table client

    public static String clientName;
    public static String clientEmail;
    public static String clientPhone;
    public static String clientBilAddress1;
    public static String clientBilAddress2;
    public static String clientShipAddress1;
    public static String clientShipAddress2;
    public static String clientDetails;



    // todo :  table discount

    public static String discountType;
    public static double discountValue;



    // todo :  table item list

    public static ArrayList<SingleItemModel> itemsList = new ArrayList<>();


    // todo :  table currency

    public static String countryName;
    public static String countrySymbol;
    public static String currencySymbol;


    // todo :  others
    public static double subTotal;
    public static double discount;
    public static double finalTotal;


}
