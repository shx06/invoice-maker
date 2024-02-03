package com.wayyesy.invoicemaker;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wayyesy.invoicemaker.R;

import com.wayyesy.invoicemaker.adapters.DashboardDataRecyclerView;
import com.wayyesy.invoicemaker.adapters.DashboardTopicsAdapter;
import com.wayyesy.invoicemaker.model.TopicsModel;
import com.wayyesy.invoicemaker.db.InvoiceDB;
import com.wayyesy.invoicemaker.activity.InvoiceDashboardActivity;
import com.wayyesy.invoicemaker.model.DataControllerModel;
import com.wayyesy.invoicemaker.utils.Constants;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    DashboardTopicsAdapter dashboardTopicsAdapter;
    DashboardDataRecyclerView dashboardDataRecyclerView;
    RecyclerView topicsRecyclerView, dataRecyclerView;
    InvoiceDB invoiceDB;
    List<DataControllerModel> dataControllerList;
    LinearLayout nav_share_btn, nav_rate_us, no_data_layout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitComponent();

        invoiceDB = new InvoiceDB(getApplicationContext());


        navigationView.bringToFront();
        DrawerToggle();
        navigationView.setNavigationItemSelectedListener(this);


        FetchDataController();

        ArrayList<TopicsModel> topicsModels = new ArrayList<>();
        topicsModels.add(new TopicsModel("All"));
        topicsModels.add(new TopicsModel("Paid"));
        topicsModels.add(new TopicsModel("Partial Paid"));
        topicsModels.add(new TopicsModel("Unpaid"));
        topicsModels.add(new TopicsModel("Overdue"));

        dashboardTopicsAdapter = new DashboardTopicsAdapter(topicsModels, this);
        topicsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        topicsRecyclerView.setAdapter(dashboardTopicsAdapter);

        findViewById(R.id.create_invoice).setOnClickListener(v -> {
            createInvoiceLauncher();
           // Intent go = new Intent(MainActivity.this, RazorpayActivity.class);
           // startActivity(go);

        });

        CustomNavMenuClick();

        DashBoardRecyclerView();


    }

    private void InitComponent() {

        topicsRecyclerView = findViewById(R.id.topic_recycler_view);
        dataRecyclerView = findViewById(R.id.data_recycler_view);
        nav_share_btn = findViewById(R.id.nav_share_btn);
        toolbar = findViewById(R.id.my_toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        nav_rate_us = findViewById(R.id.nav_rate_us);
        no_data_layout = findViewById(R.id.no_data_layout);


    }


    private void DashBoardRecyclerView() {

        if(dataControllerList != null && dataControllerList.size() > 0 ) {
            no_data_layout.setVisibility(View.GONE);
            dataRecyclerView.setVisibility(View.VISIBLE);
            dashboardDataRecyclerView = new DashboardDataRecyclerView(dataControllerList, this);
            dataRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
            dataRecyclerView.setAdapter(dashboardDataRecyclerView);
        } else {
            dataRecyclerView.setVisibility(View.GONE);
            no_data_layout.setVisibility(View.VISIBLE);
        }
    }

    public void FetchDataController() {

        dataControllerList = new ArrayList<>();

        Cursor m_cur = invoiceDB.getAllData_data_controller();

        if (m_cur.getCount() > 0) {

            no_data_layout.setVisibility(View.GONE);
            dataRecyclerView.setVisibility(View.VISIBLE);

            while (m_cur.moveToNext()) {
                int flag = m_cur.getInt(2);

                if (flag == Constants.DefaultFlag) {
                    int dc_id = m_cur.getInt(0);
                    invoiceDB.delete_data_controller_OneRow(dc_id);
                    invoiceDB.delete_currency_OneRow(dc_id);
                    invoiceDB.delete_discount_by_dcId(dc_id);

                } else {

                    dataControllerList.add(new DataControllerModel(m_cur.getInt(0),
                            m_cur.getString(1), m_cur.getInt(2)));


                }


            }

        } else {
            dataRecyclerView.setVisibility(View.GONE);
            no_data_layout.setVisibility(View.VISIBLE);
        }
        m_cur.close();
    }


    private void createInvoiceLauncher() {

        boolean result = invoiceDB.insertData_data_controller("System", Constants.DefaultFlag);
        if (result) {

            //  Toast.makeText(this, "Data manger Active", Toast.LENGTH_SHORT).show();

            Cursor cur = invoiceDB.getLastRow_data_controller();
            if (cur.getCount() > 0) {

                while (cur.moveToNext()) {
                    Constants.DCReferenceKey = cur.getInt(0);
                }

                Intent g = new Intent(MainActivity.this, InvoiceDashboardActivity.class);
                Constants.Insertion_Update_Flag = true;

//                Constants.DCReferenceKey = 0;
//                Constants.InvoiceReferenceKey = 0;


                Constants.FinalInvoiceDiscountType = null;
                Constants.FinalInvoiceDiscount = 0;
                Constants.TotalInvoicePrice = 0;
                Constants.SelectedInvoiceDiscount = 0;

                Constants.ReLoaderActivator = true;
                startActivity(g);

                cur.close();
            }


            Constants.CreateInvoiceKey = Constants.DCReferenceKey;

        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }


    }

    public void forEdit(int dc_id, int invoiceId) {

        Constants.DCReferenceKey = dc_id;
        Constants.InvoiceReferenceKey = invoiceId;

        Constants.TotalInvoicePrice = 0;
        Constants.FinalInvoiceDiscount = 0;

        Intent g = new Intent(MainActivity.this, InvoiceDashboardActivity.class);
        Constants.Insertion_Update_Flag = false;

        Constants.ReLoaderActivator = true;
        startActivity(g);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        return true;
    }


    public void DrawerToggle() {

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.white));

    }

    private void CustomNavMenuClick() {

        nav_rate_us.setOnClickListener(v -> {

            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +
                        getPackageName() + "&hl=en")));

            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName() + "&hl=en")));
            }


            drawerLayout.closeDrawers();
        });


        nav_share_btn.setOnClickListener(v -> {
            shareApp();
            drawerLayout.closeDrawers();

        });


    }

    private void shareApp() {

        Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.app_share_back_pic);

        Intent shareApp = new Intent(Intent.ACTION_SEND);
        shareApp.setType("image/jpeg");
        Uri bmpUri;
        bmpUri = saveImage(bitmap, getApplicationContext());
        shareApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareApp.putExtra(Intent.EXTRA_STREAM, bmpUri);

        String ShareBody = "Elevate your invoicing experience with our Invoice Maker app, where accuracy meets innovation. " + "\n" +
                "https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en";
        String ShareSub = "Invoice Maker App";
        shareApp.putExtra(Intent.EXTRA_SUBJECT, ShareSub);
        shareApp.putExtra(Intent.EXTRA_TEXT, ShareBody);
        startActivity(Intent.createChooser(shareApp, "Share Using"));


    }

    private static Uri saveImage(Bitmap image, Context context) {

        File imageFolder = new File(context.getCacheDir(), "picture");
        Uri uri = null;
        try {
            if (!imageFolder.exists()) {
                try {
                    boolean success = imageFolder.mkdirs();
                    if (!success) {
                        Toast.makeText(context, "Folder Creation Failed!! Contact support Team!.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            File file = new File(imageFolder, "app_share_banner.jpg");

            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();

            uri = FileProvider.getUriForFile(Objects.requireNonNull(context.getApplicationContext()),
                    context.getPackageName() + ".provider", file);
        } catch (IOException e) {
            Log.d("TAG", "Exception" + e.getMessage());
        }


        return uri;

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Constants.ReLoaderActivator) {
            FetchDataController();
            DashBoardRecyclerView();
            Constants.ReLoaderActivator = false;
        }

    }


}