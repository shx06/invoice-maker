package com.wayyesy.invoicemaker.templates;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.wayyesy.invoicemaker.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class InvoiceTemplates {

    Context context;
    Activity activity;


    public InvoiceTemplates(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    public void invoiceTemplate_1(Document document) {

        float[] tableCol1 = {270f, 180f, 120f};
        Table tableHeader = new Table(tableCol1);
        tableHeader.addCell(new Cell(3, 1).add(new Paragraph("INVOICE").setBold().setFontSize(45f)).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        tableHeader.addCell(new Cell().add(new Paragraph("INVOICE#").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setTextAlignment(TextAlignment.CENTER).setFontSize(19f)).setBorder(Border.NO_BORDER));
        }


        tableHeader.addCell(new Cell().add(new Paragraph("CREATED DATE").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
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
        Table tableUserDetails = new Table(tableCol2).setMarginBottom(10f);
        tableUserDetails.addCell(new Cell().add(new Paragraph("FROM").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(19f)).setBorder(Border.NO_BORDER));

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setFontSize(18f).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.compAdd1 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd1).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.compAdd2 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd2).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.compPhone != null && !(TextUtils.isEmpty(InvoiceHelper.compPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.compPhone).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.compEmail != null && !(TextUtils.isEmpty(InvoiceHelper.compEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.compEmail).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.compWebsite != null && !(TextUtils.isEmpty(InvoiceHelper.compWebsite))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.compWebsite).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }


        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.clientShipAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientShipAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientShipAddress1).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }
        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(18f)).setBorder(Border.NO_BORDER));
        }

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

        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(20f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice())).setBorder(Border.NO_BORDER));

            double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

            double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

            double netItemPrice = extra + totalItemPrice;

            tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))).setBorder(Border.NO_BORDER));
        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(5f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount))).setBorder(Border.NO_BORDER));


        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal))).setBorder(Border.NO_BORDER));


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(hrLineDashed);
        document.add(tableDataDetailsHead);
        document.add(hrLineDashed);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);

        document.close();
    }


    public void invoiceTemplate_2(Document document, Resources resources) {

        DeviceRgb blueFontColor = new DeviceRgb(0, 150, 255);
        DeviceRgb lightBlueFontColor = new DeviceRgb(153, 204, 255);
        DeviceRgb whiteFontColor = new DeviceRgb(255, 255, 255);

        Bitmap iconBitmap;

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            byte[] byteArray = InvoiceHelper.compImage;
            iconBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            iconBitmap = BitmapFactory.decodeResource(resources, R.drawable.temp_2_icon);
        }

        ByteArrayOutputStream iconStream = new ByteArrayOutputStream();
        iconBitmap.compress(Bitmap.CompressFormat.PNG, 100, iconStream);
        ImageData iconImageData = ImageDataFactory.create(iconStream.toByteArray());

        float[] tableCol1 = {100f, 230f, 230f};
        Table tableHeader = new Table(tableCol1);

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f)).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("")).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(blueFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(blueFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.compAdd1 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd1).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compAdd2 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd2))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd2).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compPhone != null && !(TextUtils.isEmpty(InvoiceHelper.compPhone))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compPhone).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compEmail != null && !(TextUtils.isEmpty(InvoiceHelper.compEmail))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compEmail).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compWebsite != null && !(TextUtils.isEmpty(InvoiceHelper.compWebsite))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compWebsite).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);

        float[] tableCol2 = {240, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(blueFontColor).setMarginTop(10f);
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingLeft(5f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingRight(5f)).setBorder(Border.NO_BORDER));

        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(10f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            if (i % 2 == 0) {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))).setBorder(Border.NO_BORDER));

            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setBackgroundColor(lightBlueFontColor).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setBackgroundColor(lightBlueFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setBackgroundColor(lightBlueFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)).setBackgroundColor(lightBlueFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)).setBackgroundColor(lightBlueFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)).setBackgroundColor(lightBlueFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

            }
        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(10f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL").setBold().setBackgroundColor(blueFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setBold().setBackgroundColor(blueFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(tableDataDetailsHead);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);


        document.close();


    }

    public void invoiceTemplate_3(Document document, Resources resources) {

        DeviceRgb themeFontColor = new DeviceRgb(204, 0, 204);
        DeviceRgb lightThemeFontColor = new DeviceRgb(255, 204, 255);
        DeviceRgb whiteFontColor = new DeviceRgb(255, 255, 255);

        Bitmap iconBitmap;

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            byte[] byteArray = InvoiceHelper.compImage;

            iconBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            iconBitmap = BitmapFactory.decodeResource(resources, R.drawable.temp_2_icon);
        }

        ByteArrayOutputStream iconStream = new ByteArrayOutputStream();
        iconBitmap.compress(Bitmap.CompressFormat.PNG, 100, iconStream);
        ImageData iconImageData = ImageDataFactory.create(iconStream.toByteArray());

        float[] tableCol1 = {100f, 230f, 230f};
        Table tableHeader = new Table(tableCol1);

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f)).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("")).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.compAdd1 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd1).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compAdd2 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd2))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd2).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compPhone != null && !(TextUtils.isEmpty(InvoiceHelper.compPhone))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compPhone).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compEmail != null && !(TextUtils.isEmpty(InvoiceHelper.compEmail))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compEmail).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compWebsite != null && !(TextUtils.isEmpty(InvoiceHelper.compWebsite))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compWebsite).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);

        float[] tableCol2 = {240, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingLeft(5f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingRight(5f)).setBorder(Border.NO_BORDER));

        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(10f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            if (i % 2 == 0) {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))).setBorder(Border.NO_BORDER));

            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setBackgroundColor(lightThemeFontColor).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

            }
        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(10f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount))).setBorder(Border.NO_BORDER));


        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL").setBold().setBackgroundColor(themeFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setBold().setBackgroundColor(themeFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(tableDataDetailsHead);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);


        document.close();


    }

    public void invoiceTemplate_4(Document document, Resources resources) {

        DeviceRgb themeFontColor = new DeviceRgb(0, 153, 153);
        DeviceRgb lightThemeFontColor = new DeviceRgb(204, 255, 204);
        DeviceRgb whiteFontColor = new DeviceRgb(255, 255, 255);

        Bitmap iconBitmap;

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            byte[] byteArray = InvoiceHelper.compImage;

            iconBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            iconBitmap = BitmapFactory.decodeResource(resources, R.drawable.temp_2_icon);
        }

        ByteArrayOutputStream iconStream = new ByteArrayOutputStream();
        iconBitmap.compress(Bitmap.CompressFormat.PNG, 100, iconStream);
        ImageData iconImageData = ImageDataFactory.create(iconStream.toByteArray());

        float[] tableCol1 = {100f, 230f, 230f};
        Table tableHeader = new Table(tableCol1);

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f)).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("")).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.compAdd1 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd1).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compAdd2 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd2))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd2).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compPhone != null && !(TextUtils.isEmpty(InvoiceHelper.compPhone))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compPhone).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compEmail != null && !(TextUtils.isEmpty(InvoiceHelper.compEmail))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compEmail).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compWebsite != null && !(TextUtils.isEmpty(InvoiceHelper.compWebsite))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compWebsite).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);

        float[] tableCol2 = {240, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingLeft(5f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingRight(5f)).setBorder(Border.NO_BORDER));

        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(10f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            if (i % 2 == 0) {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))).setBorder(Border.NO_BORDER));

            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setBackgroundColor(lightThemeFontColor).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

            }
        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(10f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount))).setBorder(Border.NO_BORDER));


        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL").setBold().setBackgroundColor(themeFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setBold().setBackgroundColor(themeFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(tableDataDetailsHead);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);


        document.close();


    }

    public void invoiceTemplate_5(Document document, Resources resources) {

        DeviceRgb themeFontColor = new DeviceRgb(225, 128, 0);
        DeviceRgb lightThemeFontColor = new DeviceRgb(255, 204, 153);
        DeviceRgb whiteFontColor = new DeviceRgb(255, 255, 255);

        Bitmap iconBitmap;

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            byte[] byteArray = InvoiceHelper.compImage;

            iconBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            iconBitmap = BitmapFactory.decodeResource(resources, R.drawable.temp_2_icon);
        }

        ByteArrayOutputStream iconStream = new ByteArrayOutputStream();
        iconBitmap.compress(Bitmap.CompressFormat.PNG, 100, iconStream);
        ImageData iconImageData = ImageDataFactory.create(iconStream.toByteArray());

        float[] tableCol1 = {100f, 230f, 230f};
        Table tableHeader = new Table(tableCol1);

        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f)).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("")).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setHorizontalAlignment(HorizontalAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.compAdd1 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd1).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compAdd2 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd2))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd2).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compPhone != null && !(TextUtils.isEmpty(InvoiceHelper.compPhone))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compPhone).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compEmail != null && !(TextUtils.isEmpty(InvoiceHelper.compEmail))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compEmail).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        if (InvoiceHelper.compWebsite != null && !(TextUtils.isEmpty(InvoiceHelper.compWebsite))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compWebsite).setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginLeft(30f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);

        float[] tableCol2 = {240, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold().setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setHorizontalAlignment(HorizontalAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingLeft(5f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setBold().setFontColor(whiteFontColor).setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setBold().setFontColor(whiteFontColor).setFontSize(14f).setPaddingRight(5f)).setBorder(Border.NO_BORDER));

        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(10f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            if (i % 2 == 0) {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))).setBorder(Border.NO_BORDER));

            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setBackgroundColor(lightThemeFontColor).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(22f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

            }
        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(10f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal))).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold()).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount))).setBorder(Border.NO_BORDER));


        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL").setBold().setBackgroundColor(themeFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setBold().setBackgroundColor(themeFontColor).setHeight(22f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(tableDataDetailsHead);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);


        document.close();


    }


}