package com.example.invoicemaker.Dashboard;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicemaker.ImportExportActivity;
import com.example.invoicemaker.R;
import com.example.invoicemaker.db.InvoiceDB;
import com.example.invoicemaker.invoice.InvoiceDashboardActivity;
import com.example.invoicemaker.model.DataControllerModel;
import com.example.invoicemaker.utils.Constants;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    DashboardTopicsAdapter dashboardTopicsAdapter;
    DashboardDataRecyclerView dashboardDataRecyclerView;

    TextView displayVersion;
    RecyclerView topicsRecyclerView, dataRecyclerView;

    InvoiceDB invoiceDB;

    List<DataControllerModel> dataControllerList;

    TextView new_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        invoiceDB = new InvoiceDB(getApplicationContext());
        FetchDataController();

        displayVersion = findViewById(R.id.display_version);
        topicsRecyclerView = findViewById(R.id.topic_recycler_view);
        dataRecyclerView = findViewById(R.id.data_recycler_view);

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            displayVersion.setText(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        nav = findViewById(R.id.nav_menu);



        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        nav.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.report) {
                Toast.makeText(this, "feature coming soon", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (item.getItemId() == R.id.sync) {
                Toast.makeText(this, "feature coming soon", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (item.getItemId() == R.id.import_export) {
                // startActivity(new Intent(getApplicationContext(), ImportExportActivity.class));
                Toast.makeText(this, "feature coming soon", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (item.getItemId() == R.id.feedback) {
                Toast.makeText(this, "feature coming soon", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (item.getItemId() == R.id.settings) {
                Toast.makeText(this, "feature coming soon", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        });

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
        });


        DashBoardRecyclerView();






    }

    private void DashBoardRecyclerView() {
        dashboardDataRecyclerView = new DashboardDataRecyclerView(dataControllerList, this);
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        dataRecyclerView.setAdapter(dashboardDataRecyclerView);
    }

    private void FetchDataController() {

        dataControllerList = new ArrayList<>();

        Cursor m_cur = invoiceDB.getAllData_data_controller();

        if (m_cur.getCount() > 0) {
            while (m_cur.moveToNext()) {
                int flag = m_cur.getInt(2);

                if (flag == Constants.DefaultFlag) {
                    int dc_id = m_cur.getInt(0);
                    invoiceDB.delete_data_controller_OneRow(dc_id);
                    invoiceDB.delete_currency_OneRow(dc_id);

                } else {

                    dataControllerList.add(new DataControllerModel(m_cur.getInt(0),
                            m_cur.getString(1), m_cur.getInt(2)));


                }


            }

        }
        m_cur.close();
    }


    private void createInvoiceLauncher() {

        boolean result = invoiceDB.insertData_data_controller("System", Constants.DefaultFlag);
        if (result) {

            Toast.makeText(this, "Data manger Active", Toast.LENGTH_SHORT).show();

            Cursor cur = invoiceDB.getLastRow_data_controller();
            if (cur.getCount() > 0) {

                while (cur.moveToNext()) {
                    Constants.DCReferenceKey = cur.getInt(0);
                }

                Intent g = new Intent(MainActivity.this, InvoiceDashboardActivity.class);
                Constants.Insertion_Update_Flag = true;

//                Constants.DCReferenceKey = 0;
//                Constants.InvoiceReferenceKey = 0;


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