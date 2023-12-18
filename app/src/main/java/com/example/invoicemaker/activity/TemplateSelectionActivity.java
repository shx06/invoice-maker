package com.example.invoicemaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.invoicemaker.R;
import com.example.invoicemaker.adapters.SelectTemplateAdapter;
import com.example.invoicemaker.model.SelectTemplateModel;

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
        toolbarHeader.setText("Invoice Info");
        ImageView closeActivity = findViewById(R.id.close_activity);
        closeActivity.setOnClickListener(v -> finish());


        recycler_view = findViewById(R.id.recycler_view);

        selectTemplateList.add(new SelectTemplateModel(R.drawable.ic_upload_image, ""));



        GridLayoutManager gridLayoutManager = new GridLayoutManager(TemplateSelectionActivity.this, 2, GridLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(gridLayoutManager);
        selectTemplateAdapter = new SelectTemplateAdapter(selectTemplateList,
                TemplateSelectionActivity.this,
                TemplateSelectionActivity.this);
        recycler_view.setAdapter(selectTemplateAdapter);

    }
}