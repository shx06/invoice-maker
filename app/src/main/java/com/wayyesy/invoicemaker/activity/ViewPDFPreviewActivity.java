package com.wayyesy.invoicemaker.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.content.ComponentName;
import com.wayyesy.invoicemaker.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wayyesy.invoicemaker.templates.InvoiceTemplates;
import com.wayyesy.invoicemaker.utils.StaticConstants;
import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ViewPDFPreviewActivity extends AppCompatActivity {

    private static String TAG = "ViewPDFActivity";

    String pdfPath, pdfFileName;
    File pdfFilePath;
    boolean SavedStatus = false;
    TextView share_pdf, download_pdf_btn;
    PDFView pdfView;
    LinearLayout lin_bottom;
    String FILE_NAME;
    String fromWhereToCome = "UNKNOWN", selected_template;

    InvoiceTemplates invoiceTemplates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdfpreview);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText(R.string.invoice_preview);
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());

        invoiceTemplates = new InvoiceTemplates(getApplicationContext(), ViewPDFPreviewActivity.this);

        pdfView = findViewById(R.id.pdfView);
        lin_bottom = findViewById(R.id.lin_bottom);
        share_pdf = findViewById(R.id.share_pdf);
        download_pdf_btn = findViewById(R.id.download_pdf_btn);

        fromWhereToCome = getIntent().getStringExtra("fromWhereToCome");
        selected_template = getIntent().getStringExtra("selected_template");

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_hhmmss", Locale.getDefault()); //dd-MM-yyyy HH:mm:ss
        String currentDate_Time = sdf.format(new Date());
        if (TextUtils.isEmpty("add_there_invoice_name")) {
            pdfFileName = "Invoice_" + currentDate_Time;
        } else {
            pdfFileName = "add_there_invoice_name" + "_invoice_" + currentDate_Time;
        }
        FILE_NAME = pdfFileName + ".pdf";

        pdfPath = getAppPath() + FILE_NAME;


        ///  From Where to Come Handler  ///

        switch (fromWhereToCome) {

            case StaticConstants.FromWhereToCome:
                lin_bottom.setVisibility(View.VISIBLE);
                PDFCreation(selected_template, pdfPath);
                break;


            default:
                lin_bottom.setVisibility(View.GONE);
                Toast.makeText(this, "Something not correct you are doing!", Toast.LENGTH_SHORT).show();
                break;
        }

        share_pdf.setOnClickListener(view -> {

            goForShare();

        });

        download_pdf_btn.setOnClickListener(view -> {


            goForDownload();


        });


    }


    void goForShare() {


        sharePDF();

    }

    void goForDownload() {


        savePdfFile(pdfPath, "invoice_" + getCurrentDateTime());

    }


    void sharePDF() {


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Title");
        Uri path = FileProvider.getUriForFile(ViewPDFPreviewActivity.this, getPackageName() + ".provider", new File(pdfPath));
        intent.putExtra(Intent.EXTRA_STREAM, path);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("plain/*");

        // Create a Chooser Intent without the "Remember my choice" checkbox
        Intent chooserIntent = Intent.createChooser(intent, "Share File");
        chooserIntent.putExtra(Intent.EXTRA_EXCLUDE_COMPONENTS, new ComponentName[]{
                new ComponentName("com.android.internal.app", "ChooserActivity"),
                new ComponentName("com.google.android.apps.docs", "SendIntentActivity"),
                new ComponentName("com.google.android.apps.docs", "SendFileActivity"),
                new ComponentName("com.google.android.apps.docs", "SendMultipleFilesActivity")
        });

        startActivity(chooserIntent);

    }


    public void savePdfFile(String FilePath, String desiredFileName) {
        try {
            // Create a File object for the PDF file you want to save
            pdfFilePath = new File(FilePath);

            // Create an intent to allow the user to choose where to save the PDF
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf"); // Set the MIME type for PDF files
            intent.putExtra(Intent.EXTRA_TITLE, desiredFileName);

            // Launch the intent to start the PDF saving process
            savePdfLauncher.launch(intent);

        } catch (Exception e) {
            // Handle any exceptions that may occur during the process
            Toast.makeText(this, "Error saving PDF file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    ActivityResultLauncher<Intent> savePdfLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Get the URI where the PDF should be saved
                        Uri uri = data.getData();
                        try {
                            // Open an output stream for the selected URI
                            OutputStream os = getContentResolver().openOutputStream(uri);
                            if (os != null) {
                                // Read the content of the source PDF file and write it to the output stream
                                FileInputStream fis = new FileInputStream(pdfFilePath);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = fis.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                }

                                // Close the streams
                                os.close();
                                fis.close();

                                // Display a success message
                                Toast.makeText(this, "PDF file saved successfully at: " + uri.getPath(), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            // Handle any exceptions that may occur during the saving process
                            Toast.makeText(this, "Error saving PDF file: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });


    public String getCurrentDateTime() {
        String formattedDateTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hhmmss", Locale.US);
        formattedDateTime = dateFormat.format(new Date());
        return formattedDateTime;
    }


    public void PDFCreation(String selected_template, String Path) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(
                () -> {
                    try {
                        InvoiceCreation(selected_template, Path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }, 10L);


    }

    public void InvoiceCreation(String selected_template, String path) throws IOException {


        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        Document document = new Document(pdfDocument);


        switch (selected_template) {
            case StaticConstants.TEMPLATE_1:
                invoiceTemplates.invoiceTemplate_1(document);
                break;

            case StaticConstants.TEMPLATE_2:
                invoiceTemplates.invoiceTemplate_2(document, getResources());
                break;

            case StaticConstants.TEMPLATE_3:
                invoiceTemplates.invoiceTemplate_3(document, getResources());
                break;

            case StaticConstants.TEMPLATE_4:
                invoiceTemplates.invoiceTemplate_4(document, getResources());
                break;

            case StaticConstants.TEMPLATE_5:
                invoiceTemplates.invoiceTemplate_5(document, getResources());
                break;

        }

        System.out.println("myPath" + pdfPath);
        // pdfView.fromFile(new File(pdfPath)).show();
        pdfView.fromFile(new File(pdfPath))
                .spacing(16)
                .load();

    }


    private String getAppPath() {
        String folder = "invoiceMaker";
        File dir = getExternalFilesDir(folder);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("AppPath", "Failed to create directory");
            }
        }

        return dir.getPath() + File.separator;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        if (!SavedStatus) {

            File file = new File(pdfPath);

            if (file.exists() && !file.isDirectory()) {

                if (file.delete()) {
                    System.out.println("File Removed Done");
                }
            }
        }


        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (!SavedStatus) {

            File file = new File(pdfPath);

            if (file.exists() && !file.isDirectory()) {

                if (file.delete()) {
                    System.out.println("File Removed Done");
                }
            }
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}