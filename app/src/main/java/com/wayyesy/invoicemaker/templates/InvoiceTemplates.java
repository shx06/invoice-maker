package com.wayyesy.invoicemaker.templates;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
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
import java.text.DecimalFormat;
import java.util.Objects;

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

        // A4 side -->

        float[] tableCol3 = {140, 55, 85, 105, 105, 85};  // 575
        Table tableDataDetailsHead = new Table(tableCol3).setWidth(575f).setFixedLayout();

       /* float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        Table tableDataDetailsHead = new Table(tableCol3);*/
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM NAME").setBold())
                .setTextAlignment(TextAlignment.LEFT)
                .setBorder(Border.NO_BORDER)

        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setBold())
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );

        //  Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(20f);


        float[] tableCol3_1 = {140, 55, 85, 105, 105, 85};  // 575
        Table tableDataDetailsBody = new Table(tableCol3_1).setWidth(575f).setFixedLayout();

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {

            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT)
            );
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.CENTER)
            );
            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.CENTER)
            );

            double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

            double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

            double netItemPrice = extra + totalItemPrice;

           // if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
            if(!(Objects.equals(InvoiceHelper.itemsList.get(i).getItemDisc(),"0"))) {
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("-" + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.CENTER)
                );
            } else {
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.CENTER)
                );
            }

            if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.CENTER)
                );
            } else {
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.CENTER)
                );
            }

            tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)))
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.CENTER)
            );

        }

        Table tableDataDetailsCalculation = new Table(tableCol3).setMarginTop(20f);
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


        float[] tableTermsCol = {560};
        Table tableTermsHeader = new Table(tableTermsCol).setFixedPosition(30f, 20, 560);
        if (InvoiceHelper.companyTerms != null && !(TextUtils.isEmpty(InvoiceHelper.companyTerms))) {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("Terms and Conditions:").setBold()).setBorder(Border.NO_BORDER));
            tableTermsHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.companyTerms).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        } else {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }


        document.add(tableHeader);
        document.add(hrLine);
        document.add(tableUserDetails);
        document.add(hrLineDashed);
        document.add(tableDataDetailsHead);
        document.add(hrLineDashed);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);
        document.add(tableTermsHeader);

        document.close();
    }


    public void invoiceTemplate_2(Document document, DeviceRgb themeFontColor, DeviceRgb lightThemeFontColor, DeviceRgb whiteFontColor, Resources resources) {

        PdfFont font = InvoiceHelper.selectPDFFont(InvoiceHelper.countryName);

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

        float[] tableCol1 = {100f, 230f, 225f};
        Table tableHeader = new Table(tableCol1);



        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(""))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.LEFT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
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

        document.add(tableHeader);

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);
        document.add(hrLine);

        float[] tableCol2 = {235, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold())
                .setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));

        document.add(tableUserDetails);

     ///   float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
//        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
      //  Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);

        float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f).setWidth(555f).setFixedLayout();


        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM NAME").setPaddingLeft(5f).setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)

        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        document.add(tableDataDetailsHead);



        float[] tableCol3_1 = {140, 55, 80, 100, 100, 80};

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {

            Table tableDataDetailsBody = new Table(tableCol3_1).setBackgroundColor(lightThemeFontColor).setWidth(555f).setFixedLayout();

            Table tableDataDetailsBodyWhite = new Table(tableCol3_1).setWidth(555f).setFixedLayout();

            if (i % 2 == 0) {

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                        .setFont(font).setPaddingLeft(5f).setTextAlignment(TextAlignment.LEFT))
                                .setBorder(Border.NO_BORDER)
                        );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i)
                                .getItemPrice())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                document.add(tableDataDetailsBodyWhite);
            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                .setFont(font).setPaddingLeft(5f).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.LEFT))
                        .setBorder(Border.NO_BORDER)
                );




                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;


                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER));


                document.add(tableDataDetailsBody);

            }
        }

        float[] tableCol4 = {140, 55, 80, 80, 80, 120};

        Table tableDataDetailsCalculation = new Table(tableCol4).setMarginTop(15f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.discount > 0) {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        } else {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("--").setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        }

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL :").setFont(font).setBackgroundColor(themeFontColor).setTextAlignment(TextAlignment.RIGHT).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setBackgroundColor(themeFontColor).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

        document.add(tableDataDetailsCalculation);

        float[] tableTermsCol = {560};
        Table tableTermsHeader = new Table(tableTermsCol).setFixedPosition(30f, 20, 560);

        if (InvoiceHelper.companyTerms != null && !(TextUtils.isEmpty(InvoiceHelper.companyTerms))) {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("Terms and Conditions:").setBold().setFontColor(themeFontColor)).setBorder(Border.NO_BORDER));
            tableTermsHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.companyTerms).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        } else {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }
        document.add(tableTermsHeader);

//        document.add(tableHeader);
//        document.add(hrLine);
//        document.add(tableUserDetails);
//        document.add(tableDataDetailsHead);
//        document.add(tableDataDetailsBody);




        document.close();


    }

    public void invoiceTemplate_3(Document document, DeviceRgb themeFontColor, DeviceRgb lightThemeFontColor, DeviceRgb whiteFontColor, Resources resources) {

        PdfFont font = InvoiceHelper.selectPDFFont(InvoiceHelper.countryName);

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

        float[] tableCol1 = {100f, 230f, 225f};
        Table tableHeader = new Table(tableCol1);



        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(""))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.LEFT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
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

        document.add(tableHeader);

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);
        document.add(hrLine);

        float[] tableCol2 = {235, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold())
                .setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));

        document.add(tableUserDetails);

        ///   float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
//        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
        //  Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);

        float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f).setWidth(555f).setFixedLayout();


        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM NAME").setPaddingLeft(5f).setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)

        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        document.add(tableDataDetailsHead);



        float[] tableCol3_1 = {140, 55, 80, 100, 100, 80};

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {

            Table tableDataDetailsBody = new Table(tableCol3_1).setBackgroundColor(lightThemeFontColor).setWidth(555f).setFixedLayout();

            Table tableDataDetailsBodyWhite = new Table(tableCol3_1).setWidth(555f).setFixedLayout();

            if (i % 2 == 0) {

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                .setFont(font).setPaddingLeft(5f).setTextAlignment(TextAlignment.LEFT))
                        .setBorder(Border.NO_BORDER)
                );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i)
                                .getItemPrice())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                document.add(tableDataDetailsBodyWhite);
            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                .setFont(font).setPaddingLeft(5f).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.LEFT))
                        .setBorder(Border.NO_BORDER)
                );




                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;


                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER));


                document.add(tableDataDetailsBody);

            }
        }

        float[] tableCol4 = {140, 55, 80, 80, 80, 120};

        Table tableDataDetailsCalculation = new Table(tableCol4).setMarginTop(15f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.discount > 0) {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        } else {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("--").setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        }

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL :").setFont(font).setBackgroundColor(themeFontColor).setTextAlignment(TextAlignment.RIGHT).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setBackgroundColor(themeFontColor).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

        document.add(tableDataDetailsCalculation);

        float[] tableTermsCol = {560};
        Table tableTermsHeader = new Table(tableTermsCol).setFixedPosition(30f, 20, 560);

        if (InvoiceHelper.companyTerms != null && !(TextUtils.isEmpty(InvoiceHelper.companyTerms))) {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("Terms and Conditions:").setBold().setFontColor(themeFontColor)).setBorder(Border.NO_BORDER));
            tableTermsHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.companyTerms).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        } else {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }
        document.add(tableTermsHeader);

//        document.add(tableHeader);
//        document.add(hrLine);
//        document.add(tableUserDetails);
//        document.add(tableDataDetailsHead);
//        document.add(tableDataDetailsBody);




        document.close();


    }

    public void invoiceTemplate_4(Document document, DeviceRgb themeFontColor, DeviceRgb lightThemeFontColor, DeviceRgb whiteFontColor, Resources resources) {

        PdfFont font = InvoiceHelper.selectPDFFont(InvoiceHelper.countryName);

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

        float[] tableCol1 = {100f, 230f, 225f};
        Table tableHeader = new Table(tableCol1);



        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(""))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.LEFT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
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

        document.add(tableHeader);

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);
        document.add(hrLine);

        float[] tableCol2 = {235, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold())
                .setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));

        document.add(tableUserDetails);

     ///   float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
//        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
      //  Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);

        float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f).setWidth(555f).setFixedLayout();


        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM NAME").setPaddingLeft(5f).setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)

        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        document.add(tableDataDetailsHead);



        float[] tableCol3_1 = {140, 55, 80, 100, 100, 80};

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {

            Table tableDataDetailsBody = new Table(tableCol3_1).setBackgroundColor(lightThemeFontColor).setWidth(555f).setFixedLayout();

            Table tableDataDetailsBodyWhite = new Table(tableCol3_1).setWidth(555f).setFixedLayout();

            if (i % 2 == 0) {

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                        .setFont(font).setPaddingLeft(5f).setTextAlignment(TextAlignment.LEFT))
                                .setBorder(Border.NO_BORDER)
                        );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i)
                                .getItemPrice())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                document.add(tableDataDetailsBodyWhite);
            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                .setFont(font).setPaddingLeft(5f).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.LEFT))
                        .setBorder(Border.NO_BORDER)
                );




                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;


                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER));


                document.add(tableDataDetailsBody);

            }
        }

        float[] tableCol4 = {140, 55, 80, 80, 80, 120};

        Table tableDataDetailsCalculation = new Table(tableCol4).setMarginTop(15f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.discount > 0) {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        } else {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("--").setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        }

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL :").setFont(font).setBackgroundColor(themeFontColor).setTextAlignment(TextAlignment.RIGHT).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setBackgroundColor(themeFontColor).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

        document.add(tableDataDetailsCalculation);

        float[] tableTermsCol = {560};
        Table tableTermsHeader = new Table(tableTermsCol).setFixedPosition(30f, 20, 560);

        if (InvoiceHelper.companyTerms != null && !(TextUtils.isEmpty(InvoiceHelper.companyTerms))) {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("Terms and Conditions:").setBold().setFontColor(themeFontColor)).setBorder(Border.NO_BORDER));
            tableTermsHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.companyTerms).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        } else {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }
        document.add(tableTermsHeader);

//        document.add(tableHeader);
//        document.add(hrLine);
//        document.add(tableUserDetails);
//        document.add(tableDataDetailsHead);
//        document.add(tableDataDetailsBody);




        document.close();


    }

    public void invoiceTemplate_5(Document document, DeviceRgb themeFontColor, DeviceRgb lightThemeFontColor, DeviceRgb whiteFontColor, Resources resources) {

        PdfFont font = InvoiceHelper.selectPDFFont(InvoiceHelper.countryName);

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

        float[] tableCol1 = {100f, 230f, 225f};
        Table tableHeader = new Table(tableCol1);



        if (InvoiceHelper.compImage != null && InvoiceHelper.compImage.length > 0) {
            tableHeader.addCell(new Cell(6, 1).add(new Image(iconImageData).setWidth(100f))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(""))
                    .setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }

        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.LEFT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("WayyEasy").setBold().setFontSize(17f).setMarginLeft(30f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph(InvoiceHelper.invNo).setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell(6, 1).add(new Paragraph("INVOICE").setTextAlignment(TextAlignment.RIGHT).setBold().setFontColor(themeFontColor).setFontSize(35f).setMarginTop(40f))
                    .setBorder(Border.NO_BORDER));
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

        document.add(tableHeader);

        ILineDrawer solidLine = new SolidLine();
        LineSeparator hrLine = new LineSeparator(solidLine).setOpacity(0.5f).setMarginTop(20f).setMarginBottom(15f);
        document.add(hrLine);

        float[] tableCol2 = {235, 180f, 140f};
        Table tableUserDetails = new Table(tableCol2);
        tableUserDetails.addCell(new Cell().add(new Paragraph("BILL TO").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("INVOICE #").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("Created Date").setFontSize(17f).setBold().setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }
        tableUserDetails.addCell(new Cell().add(new Paragraph("DUE Date").setFontSize(17f).setBold().setMarginTop(-7f).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.invDueDate != null && !(TextUtils.isEmpty(InvoiceHelper.invDueDate))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invDueDate).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f))
                    .setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("P.O.#").setFontSize(17f).setMarginTop(-7f).setBold())
                .setBorder(Border.NO_BORDER));

        if (InvoiceHelper.invoicePo != null && !(TextUtils.isEmpty(InvoiceHelper.invoicePo))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.invoicePo).setMarginTop(-7f).setFontSize(17f).setTextAlignment(TextAlignment.RIGHT))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(17f)).setBorder(Border.NO_BORDER));
        }

        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f))
                    .setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableUserDetails.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        } else {
            tableUserDetails.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f).setFontSize(15f)).setBorder(Border.NO_BORDER));
        }

        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f).setMarginTop(-7f).setBold()).setBorder(Border.NO_BORDER));
        tableUserDetails.addCell(new Cell().add(new Paragraph("").setFontSize(17f)).setBorder(Border.NO_BORDER));

        document.add(tableUserDetails);

     ///   float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
//        float[] tableCol3 = {140f, 44f, 84f, 104f, 104f, 84f};
      //  Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f);

        float[] tableCol3 = {140, 55, 80, 100, 100, 80};  // 555
        Table tableDataDetailsHead = new Table(tableCol3).setBackgroundColor(themeFontColor).setMarginTop(10f).setWidth(555f).setFixedLayout();


        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("ITEM NAME").setPaddingLeft(5f).setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)

        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("QTY").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("PRICE").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("DISCOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("TAX").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("AMOUNT").setFont(font).setFontColor(whiteFontColor).setFontSize(14f))
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
        );
        document.add(tableDataDetailsHead);



        float[] tableCol3_1 = {140, 55, 80, 100, 100, 80};

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {

            Table tableDataDetailsBody = new Table(tableCol3_1).setBackgroundColor(lightThemeFontColor).setWidth(555f).setFixedLayout();

            Table tableDataDetailsBodyWhite = new Table(tableCol3_1).setWidth(555f).setFixedLayout();

            if (i % 2 == 0) {

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                        .setFont(font).setPaddingLeft(5f).setTextAlignment(TextAlignment.LEFT))
                                .setBorder(Border.NO_BORDER)
                        );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );
                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i)
                                .getItemPrice())
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBodyWhite.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                document.add(tableDataDetailsBodyWhite);
            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName())
                                .setFont(font).setPaddingLeft(5f).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.LEFT))
                        .setBorder(Border.NO_BORDER)
                );




                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice())
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER)
                );

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;


                if (!InvoiceHelper.itemsList.get(i).getItemDisc().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                }

                if (!InvoiceHelper.itemsList.get(i).getItemTax().equals("0")) {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice))
                                    .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                            .setBorder(Border.NO_BORDER)
                    );
                } else {
                    tableDataDetailsBody.addCell(new Cell().add(new Paragraph("--")
                                    .setFont(font).setBackgroundColor(lightThemeFontColor))
                            .setBorder(Border.NO_BORDER)
                            .setTextAlignment(TextAlignment.CENTER)
                    );
                }

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice))
                                .setFont(font).setBackgroundColor(lightThemeFontColor).setTextAlignment(TextAlignment.CENTER))
                        .setBorder(Border.NO_BORDER));


                document.add(tableDataDetailsBody);

            }
        }

        float[] tableCol4 = {140, 55, 80, 80, 80, 120};

        Table tableDataDetailsCalculation = new Table(tableCol4).setMarginTop(15f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("SUBTOTAL :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("DISCOUNT :").setFont(font).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.discount > 0) {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        } else {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("--").setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));
        }

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("TOTAL :").setFont(font).setBackgroundColor(themeFontColor).setTextAlignment(TextAlignment.RIGHT).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setTextAlignment(TextAlignment.RIGHT).setPaddingRight(5f).setFont(font).setBackgroundColor(themeFontColor).setHeight(25f).setFontColor(whiteFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

        document.add(tableDataDetailsCalculation);

        float[] tableTermsCol = {560};
        Table tableTermsHeader = new Table(tableTermsCol).setFixedPosition(30f, 20, 560);

        if (InvoiceHelper.companyTerms != null && !(TextUtils.isEmpty(InvoiceHelper.companyTerms))) {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("Terms and Conditions:").setBold().setFontColor(themeFontColor)).setBorder(Border.NO_BORDER));
            tableTermsHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.companyTerms).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        } else {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }
        document.add(tableTermsHeader);

//        document.add(tableHeader);
//        document.add(hrLine);
//        document.add(tableUserDetails);
//        document.add(tableDataDetailsHead);
//        document.add(tableDataDetailsBody);




        document.close();


    }

    public void invoiceTemplate_6(Document document, DeviceRgb themeFontColor, DeviceRgb lightThemeFontColor, Resources resources) {


        PdfFont font = InvoiceHelper.selectPDFFont(InvoiceHelper.countryName);


        float[] tableHrLineCol = {580};
        Table tableHrLine = new Table(tableHrLineCol);
        tableHrLine.addCell(new Cell().setBackgroundColor(themeFontColor).setBorder(Border.NO_BORDER).setHeight(14f));


        Paragraph title = new Paragraph("INVOICE").setMarginLeft(200f).setBold().setFontSize(35f).setFontColor(themeFontColor).setHorizontalAlignment(HorizontalAlignment.CENTER);

        float[] tableHeaderCol = {190f, 190f, 180f};
        Table tableHeader = new Table(tableHeaderCol).setMarginTop(10f);

        tableHeader.addCell(new Cell().add(new Paragraph("Invoice From").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER));
        tableHeader.addCell(new Cell().add(new Paragraph("Invoice To").setBold().setFontSize(17f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        tableHeader.addCell(new Cell().add(new Paragraph("Invoice Details").setBold().setFontSize(17f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.compName != null && !(TextUtils.isEmpty(InvoiceHelper.compName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compName).setBold().setFontSize(15f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setBold().setFontSize(15f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.clientName != null && !(TextUtils.isEmpty(InvoiceHelper.clientName))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientName).setBold().setFontSize(15f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setBold().setFontSize(15f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        tableHeader.addCell(new Cell().add(new Paragraph("Invoice No.").setBold().setFontSize(15f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.compEmail != null && !(TextUtils.isEmpty(InvoiceHelper.compEmail))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compEmail).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.clientEmail != null && !(TextUtils.isEmpty(InvoiceHelper.clientEmail))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientEmail).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.invNo != null && !(TextUtils.isEmpty(InvoiceHelper.invNo))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.invNo).setFontSize(15f).setMarginTop(-7f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }


        if (InvoiceHelper.compPhone != null && !(TextUtils.isEmpty(InvoiceHelper.compPhone))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compPhone).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.clientPhone != null && !(TextUtils.isEmpty(InvoiceHelper.clientPhone))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientPhone).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        tableHeader.addCell(new Cell().add(new Paragraph("Date of Invoice").setBold().setMarginTop(-7f).setFontSize(15f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.compAdd1 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd1).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.clientBilAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress1).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.invCreatedDate != null && !(TextUtils.isEmpty(InvoiceHelper.invCreatedDate))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.invCreatedDate).setFontSize(15f).setMarginTop(-7f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));
        }


        if (InvoiceHelper.compAdd2 != null && !(TextUtils.isEmpty(InvoiceHelper.compAdd2))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compAdd2).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.clientBilAddress2 != null && !(TextUtils.isEmpty(InvoiceHelper.clientBilAddress2))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientBilAddress2).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        if (InvoiceHelper.compWebsite != null && !(TextUtils.isEmpty(InvoiceHelper.compWebsite))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.compWebsite).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        if (InvoiceHelper.clientShipAddress1 != null && !(TextUtils.isEmpty(InvoiceHelper.clientShipAddress1))) {
            tableHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.clientShipAddress1).setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        } else {
            tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT));
        }
        tableHeader.addCell(new Cell().add(new Paragraph("").setFontSize(15f).setMarginTop(-7f).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));


        float[] tableCol3 = {280f, 80f, 120f, 80f};
        Table tableDataDetailsHead = new Table(tableCol3).setMarginTop(10f);
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("Description").setBold().setFontSize(14f).setPaddingLeft(5f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("Qty").setBold().setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("Price").setBold().setFontSize(14f)).setBorder(Border.NO_BORDER));
        tableDataDetailsHead.addCell(new Cell().add(new Paragraph("Amount").setBold().setFontSize(14f).setPaddingRight(5f)).setBorder(Border.NO_BORDER));


        Table tableDataDetailsBody = new Table(tableCol3).setMarginTop(10f);

        for (int i = 0; i < InvoiceHelper.itemsList.size(); i++) {
            if (i % 2 == 0) {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setFont(font).setMarginTop(-3f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

//                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)).setFont(font).setMarginTop(-3f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
//                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)).setFont(font).setMarginTop(-3f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)).setFont(font).setMarginTop(-3f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

            } else {

                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemName()).setFont(font).setMarginTop(-3f).setBackgroundColor(lightThemeFontColor).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.itemsList.get(i).getItemQuantity()).setFont(font).setMarginTop(-3f).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + InvoiceHelper.itemsList.get(i).getItemPrice()).setFont(font).setMarginTop(-3f).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

                double totalItemPrice = Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemQuantity()) * Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemPrice());

                double extra = (((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100) * totalItemPrice) - ((Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100) * totalItemPrice));

                double netItemPrice = extra + totalItemPrice;

//                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("- " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemDisc()) / 100 * totalItemPrice)).setFont(font).setMarginTop(-3f).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
//                tableDataDetailsBody.addCell(new Cell().add(new Paragraph("+ " + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(Double.parseDouble(InvoiceHelper.itemsList.get(i).getItemTax()) / 100 * totalItemPrice)).setFont(font).setMarginTop(-3f).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));
                tableDataDetailsBody.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(netItemPrice)).setFont(font).setMarginTop(-3f).setBackgroundColor(lightThemeFontColor).setMarginLeft(-5f).setPaddingLeft(5f).setHeight(25f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));

            }
        }

        float[] tableCol3_1 = {280f, 80f, 80f, 120f};

        Table tableDataDetailsCalculation = new Table(tableCol3_1).setMarginTop(10f);
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("Subtotal :").setFont(font).setTextAlignment(TextAlignment.RIGHT).setFontColor(themeFontColor)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.subTotal)).setTextAlignment(TextAlignment.RIGHT).setFontColor(themeFontColor).setFont(font).setMarginTop(-3f)).setBorder(Border.NO_BORDER));

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("Discount :").setFont(font).setTextAlignment(TextAlignment.RIGHT).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        if (InvoiceHelper.discount > 0) {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph('-' + InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.discount)).setTextAlignment(TextAlignment.RIGHT).setFont(font).setMarginTop(-8f)).setBorder(Border.NO_BORDER));
        } else {
            tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("---").setFont(font).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }

        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph("Invoice Total :").setFont(font).setTextAlignment(TextAlignment.RIGHT).setFontColor(themeFontColor).setHeight(25f).setMarginLeft(-5f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE).setMarginTop(-10f)).setBorder(Border.NO_BORDER));
        tableDataDetailsCalculation.addCell(new Cell().add(new Paragraph(InvoiceHelper.currencySymbol + " " + new DecimalFormat("##.##").format(InvoiceHelper.finalTotal)).setTextAlignment(TextAlignment.RIGHT).setFont(font).setHeight(25f).setFontColor(themeFontColor).setMarginTop(-10f).setPaddingLeft(5f).setVerticalAlignment(VerticalAlignment.MIDDLE)).setBorder(Border.NO_BORDER));


        float[] tableTermsCol = {560};
        Table tableTermsHeader = new Table(tableTermsCol).setFixedPosition(30f, 20, 560);
        if (InvoiceHelper.companyTerms != null && !(TextUtils.isEmpty(InvoiceHelper.companyTerms))) {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("Terms and Conditions:").setBold().setFontColor(themeFontColor)).setBorder(Border.NO_BORDER));
            tableTermsHeader.addCell(new Cell().add(new Paragraph(InvoiceHelper.companyTerms).setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        } else {
            tableTermsHeader.addCell(new Cell().add(new Paragraph("").setMarginTop(-7f)).setBorder(Border.NO_BORDER));
        }

        document.add(tableHrLine);
        document.add(title);
        document.add(tableHeader);
        document.add(tableDataDetailsHead);
        document.add(tableDataDetailsBody);
        document.add(tableDataDetailsCalculation);
        document.add(tableTermsHeader);


        document.close();
    }


}