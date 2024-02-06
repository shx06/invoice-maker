package com.wayyesy.invoicemaker.templates;

import android.content.res.Resources;
import android.util.Log;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.wayyesy.invoicemaker.R;
import com.wayyesy.invoicemaker.model.CurrencyModel;
import com.wayyesy.invoicemaker.model.SingleItemModel;

import java.io.IOException;
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
    public static String companyTerms;


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


    public static PdfFont selectPDFFont(String countryName) {

        String country = (countryName != null) ? countryName : "";

        String englishFont = "assets/english.ttf";
        String hindiFont = "assets/hindi.ttf";
        String bengaliFont = "assets/bengali.ttf";
        String arabicFont = "assets/arabic.ttf";

        PdfFont font = null;

        try {
            switch (country) {
                case "India":
                    font = PdfFontFactory.createFont(hindiFont, PdfEncodings.IDENTITY_H, true);
                    break;

                case "Nepal":
                case "Pakistan":
                case "Sri Lanka":
                    font = PdfFontFactory.createFont(englishFont, PdfEncodings.IDENTITY_H, true);
                    break;

                case "Saudi Arabia":
                case "Dubai":
                    font = PdfFontFactory.createFont(arabicFont , PdfEncodings.IDENTITY_H, true);
                    break;

                case "Bangladesh":
                    font = PdfFontFactory.createFont(bengaliFont, PdfEncodings.IDENTITY_H, true);
                    break;

                default:
                    font = PdfFontFactory.createFont(englishFont, PdfEncodings.IDENTITY_H, true);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return font;
    }


}
