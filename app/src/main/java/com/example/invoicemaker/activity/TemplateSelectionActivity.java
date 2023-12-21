package com.example.invoicemaker.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.adapters.SelectTemplateAdapter;
import com.example.invoicemaker.model.SelectTemplateModel;
import com.example.invoicemaker.utils.StaticConstants;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class TemplateSelectionActivity extends AppCompatActivity {

    RecyclerView recycler_view;

    List<SelectTemplateModel> selectTemplateList = new ArrayList<>();

    SelectTemplateAdapter selectTemplateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_selection);

        TextView toolbarHeader = findViewById(R.id.toolbar_title);
        toolbarHeader.setText("Select Template");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());


        recycler_view = findViewById(R.id.recycler_view);


        selectTemplateList.add(new SelectTemplateModel(R.drawable.ic_upload_image, StaticConstants.TEMPLATE_1));


        GridLayoutManager gridLayoutManager = new GridLayoutManager(TemplateSelectionActivity.this, 2, GridLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(gridLayoutManager);
        selectTemplateAdapter = new SelectTemplateAdapter(selectTemplateList,
                TemplateSelectionActivity.this,
                TemplateSelectionActivity.this);
        recycler_view.setAdapter(selectTemplateAdapter);

        permissionLooker();

        // here need to write the code to fetch all data and store into static variable
        FetchDataForInvoiceCreation(1);


    }

    void FetchDataForInvoiceCreation(int dc_id) {


    }

    public void TemplateSelection(String selected_template) {

        Intent go = new Intent(TemplateSelectionActivity.this, ViewPDFPreviewActivity.class);
        go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        go.putExtra("SelectedTemplate", selected_template);
        startActivity(go);


    }


    private void permissionLooker() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            System.out.println("Android 13 or more!! Permission Not Required!!");
        } else {

            Dexter.withContext(this)
                    .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
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