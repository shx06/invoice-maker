package com.wayyeasy.invoicemaker.templates;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.wayyeasy.invoicemaker.utils.Constants;
import com.wayyeasy.invoicemaker.utils.StaticConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class InvoiceTemplates {

    Context context;
    Activity activity;


    public InvoiceTemplates(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    public void invoiceTemplate_1(Document document) throws IOException {

        DeviceRgb greyFontColor = new DeviceRgb(211, 211, 211);
        DeviceRgb blueFontColor = new DeviceRgb(0, 22, 221);
        DeviceRgb lightBlueFontColor = new DeviceRgb(0, 188, 248);
        DeviceRgb whiteFontColor = new DeviceRgb(255, 255, 225);

        float[] tableCol1 = {270f, 180f, 120f};
        Table tableHeader = new Table(tableCol1);
        tableHeader.addCell(new Cell(3, 1).add(new Paragraph("INVOICE").setBold().setFontSize(45f)).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        tableHeader.addCell(new Cell().add(new Paragraph("INVOICE#").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setTextAlignment(TextAlignment.CENTER).setFontSize(19f)).setBorder(Border.NO_BORDER));
        }


        tableHeader.addCell(new Cell().add(new Paragraph("CREATE DATE").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setTextAlignment(TextAlignment.CENTER).setFontSize(19f)).setBorder(Border.NO_BORDER));
        }

        tableHeader.addCell(new Cell().add(new Paragraph("DUE DATE").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setTextAlignment(TextAlignment.CENTER).setFontSize(19f)).setBorder(Border.NO_BORDER));
        }

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);

        float[] tableCol2 = {280f, 280f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("FROM").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));

        tableUserDetails.addCell(new Cell().add(new Paragraph("Sam Tech").setFontSize(18f).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("billing address").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("billing address two").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("5265856585").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("ok@gmail.com").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("website").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));

        tableUserDetails.addCell(new Cell().add(new Paragraph("Sam Tech").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("12345").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("12345").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("5265856585").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("ok2@gmail.com").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("website2").setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));

        ILineDrawer dashedLine = new DashedLine();
        LineSeparator hrLineDashed = new LineSeparator(dashedLine).setOpacity(0.5f);

        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        Table tableDataDetailsHead = new Table(tableCol3);
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setBold()).setBorder(Border.NO_BORDER));


        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(10f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.countrySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice())).setBorder(Border.NO_BORDER));

            double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

            double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

            double netItemPrice = extra + totalItemPrice;

            tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + Constants.InvoiceCurrencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + Constants.InvoiceCurrencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(Constants.InvoiceCurrencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))).setBorder(Border.NO_BORDER));
        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(5f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(Constants.InvoiceCurrencySymbol + " " + new DecimalFormat("##.##").format(Constants.TotalInvoicePrice))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));

        if (InvoiceHelper.discountType != null && !(TextUtils.isEmpty(InvoiceHelper.discountType))) {
            if (Objects.equals(InvoiceHelper.discountType, StaticConstants.DISCOUNT_PERCENTAGE)) {
                tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.countrySymbol + " " + new DecimalFormat("##.##").format(Double.valueOf(Constants.SelectedInvoiceDiscount) / 100 * Double.valueOf(Constants.TotalInvoicePrice)))).setBorder(Border.NO_BORDER));
            } else {
                tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.countrySymbol + " " + Constants.SelectedInvoiceDiscount)).setBorder(Border.NO_BORDER));
            }

        }


        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL").setBold()).setBorder(Border.NO_BORDER));

        if (Constants.FinalInvoiceDiscount > 0) {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(Constants.InvoiceCurrencySymbol + " " + new DecimalFormat("##.##").format(Constants.TotalInvoicePrice - Constants.FinalInvoiceDiscount))).setBorder(Border.NO_BORDER));
        } else {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(Constants.InvoiceCurrencySymbol + " " + new DecimalFormat("##.##").format(Constants.TotalInvoicePrice))).setBorder(Border.NO_BORDER));
        }


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(hrLineDashed).setTopMargin(15f);
        document.add(tableDataDetailsHead);
        document.add(hrLineDashed);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);


        document.close();


    }


}
