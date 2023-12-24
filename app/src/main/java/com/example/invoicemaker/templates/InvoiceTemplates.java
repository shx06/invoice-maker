package com.example.invoicemaker.templates;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;

public class InvoiceTemplates {

    Context context;
    Activity activity;


    public InvoiceTemplates(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    public void invoiceTemplate_1(Document document) throws IOException {


        /// here need to design the Invoice PDF

        float[] columnWidth4 = {642};
        Table table4 = new Table(columnWidth4).setWidth(642f).setFixedLayout();
        table4.addCell(new Cell().add(new Paragraph("Test ok "))
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
        );

        document.add(table4);


        document.close();


    }


}
