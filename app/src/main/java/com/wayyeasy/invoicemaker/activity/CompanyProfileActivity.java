package com.wayyeasy.invoicemaker.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wayyeasy.invoicemaker.R;
import com.wayyeasy.invoicemaker.db.InvoiceDB;
import com.wayyeasy.invoicemaker.utils.Constants;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CompanyProfileActivity extends AppCompatActivity {

    TextInputEditText companyName, companyEmail, companyAddress1, companyAddress2, companyPhone, companyWebsite;
    ImageView companyImage;
    byte[] bytesOfCompanyImage;
    Button save_next_btn;
    InvoiceDB invoiceDB;
    TextView toolbarHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Add Company");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());

        companyName = findViewById(R.id.company_name);
        companyEmail = findViewById(R.id.company_email);
        companyAddress1 = findViewById(R.id.company_address_1);
        companyAddress2 = findViewById(R.id.company_address_2);
        companyPhone = findViewById(R.id.company_phone);
        companyWebsite = findViewById(R.id.company_website);
        save_next_btn = findViewById(R.id.save_next_btn);
        companyImage = findViewById(R.id.company_image);

        invoiceDB = new InvoiceDB(getApplicationContext());

        FetchDataAndSet();

        save_next_btn.setOnClickListener(view -> {

            boolean result = false;

            result = savePersonalDetails();


            if (result) {
                finish();
            }

        });

        companyImage.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                companyImage.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        bytesOfCompanyImage = byteArrayOutputStream.toByteArray();
    }

    private boolean savePersonalDetails() {
        boolean result;

        Log.d(TAG, "savePersonalDetails: 113 "+Constants.Company_profile_Active);
        Log.d(TAG, "savePersonalDetails: 114 "+Constants.DCReferenceKey);

        if (!(Constants.Company_profile_Active)) {

            result = invoiceDB.insert_company_details(Constants.DCReferenceKey,
                    companyName.getText().toString(), companyEmail.getText().toString(), companyPhone.getText().toString(),
                    companyAddress1.getText().toString(), companyAddress2.getText().toString(), companyWebsite.getText().toString(), bytesOfCompanyImage);


            if (result) {
                Constants.Company_profile_Active = true;
                boolean u_result = invoiceDB.update_data_controller(Constants.DCReferenceKey, Constants.Flag);

                if (u_result) {
                    System.out.println("saved");
//                     Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show();
            }

        } else {


            result = invoiceDB.update_company_details(Constants.DCReferenceKey,
                    companyName.getText().toString(), companyEmail.getText().toString(), companyPhone.getText().toString(),
                    companyAddress1.getText().toString(), companyAddress2.getText().toString(), companyWebsite.getText().toString(), bytesOfCompanyImage);

            if (result) {
                //  Toast.makeText(CreateBioDataPDActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                System.out.println("Updated");
            } else {
                Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show();
            }


        }

        return result;
    }

    private void FetchDataAndSet() {

        if (Constants.Company_profile_Active) {
            save_next_btn.setText("Update");
            toolbarHeader.setText("Update Company");

            Cursor cur = invoiceDB.getRows_company(Constants.DCReferenceKey);

            if (cur.getCount() > 0) {
                System.out.println("row_counted company" + cur.getCount());

                while (cur.moveToNext()) {
                    companyName.setText(cur.getString(2));
                    companyEmail.setText(cur.getString(3));
                    companyPhone.setText(cur.getString(4));
                    companyAddress1.setText(cur.getString(5));
                    companyAddress2.setText(cur.getString(6));
                    companyWebsite.setText(cur.getString(7));

                    if(cur.getBlob(8)!=null) {
                        byte[] data = cur.getBlob(8);
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        companyImage.setImageBitmap(bmp);
                    }

                }


            }

            cur.close();

        }


    }
}