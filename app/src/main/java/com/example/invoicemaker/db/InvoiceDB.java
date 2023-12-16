package com.example.invoicemaker.db;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

public class InvoiceDB extends SQLiteOpenHelper {


    Context context;
    public static final String database_name = "InvoiceMaker.db";


    public InvoiceDB(@Nullable Context context) {
        super(context, database_name, null, 1);
        this.context = context;

    }


    /////////////////////---------data Controller------------------>>

    public static final String table_name_data_controller = "data_controller";
    public static final String COL_0_dc_id = "dc_id";
    public static final String COL_1_name = "operator_name";
    public static final String COL_2_flag = "flag";
    /////////////////////--------------------------->>


    /////////////////////---------Invoice Info------------------>>
    public static final String table_name_invoice_info = "invoice_info";
    public static final String COL_0_ii_id = "ii_id";
    public static final String COL_1_ii_dc_id = "dc_id";
    public static final String COL_2_ii_name = "title_name";
    public static final String COL_3_ii_invoice_number = "invoice_number";
    public static final String COL_4_ii_date = "created_date";
    public static final String COL_5_ii_due_term = "due_term";
    public static final String COL_6_ii_due_date = "due_date";
    public static final String COL_7_ii_p_o = "p_o";
    public static final String COL_8_ii_pay_status = "pay_status";
    public static final String COL_9_ii_paid_amount = "paid_amount";



    /////////////////////---------Language Info------------------>>
    public static final String table_name_language_info = "language_info";
    public static final String COL_0_li_id = "ii_id";
    public static final String COL_1_li_dc_id = "dc_id";
    public static final String COL_2_li_language = "language";


    /////////////////////---------Company------------------>>
    public static final String table_name_company = "company";
    public static final String COL_0_comp_id = "ii_id";
    public static final String COL_1_comp_dc_id = "dc_id";
    public static final String COL_2_comp_name = "comp_name";
    public static final String COL_3_comp_email = "comp_email";
    public static final String COL_4_comp_phone = "comp_phone";
    public static final String COL_5_comp_add1 = "comp_add1";
    public static final String COL_6_comp_add2 = "comp_add2";
    public static final String COL_7_comp_website = "comp_website";
    public static final String COL_8_comp_image = "comp_image";

    /////////////////////--------------------------->>

    /////////////////////-------bill- to -Client------------------>>
    public static final String table_name_client = "client";
    public static final String COL_0_client_id = "ii_id";
    public static final String COL_1_client_dc_id = "dc_id";
    public static final String COL_2_client_name = "client_name";
    public static final String COL_3_client_email = "client_email";
    public static final String COL_4_client_phone = "client_phone";
    public static final String COL_5_client_billing_add1 = "client_billing_add1";
    public static final String COL_6_client_billing_add2 = "client_billing_add2";
    public static final String COL_7_client_shipping_add1 = "client_shipping_add1";
    public static final String COL_8_client_shipping_add2 = "client_shipping_add2";
    public static final String COL_9_client_details = "client_details";

    /////////////////////--------------------------->>

    /////////////////////---------Invoice Item------------------>>
    public static final String table_name_invoice_item = "invoice_item";
    public static final String COL_0_invoice_item_id = "ii_id";
    public static final String COL_1_invoice_item_dc_id = "dc_id";
    public static final String COL_2_invoice_item_name = "invoice_item_name";
    public static final String COL_3_invoice_item_price = "invoice_item_price";
    public static final String COL_4_invoice_item_quantity = "invoice_item_quantity";
    public static final String COL_5_invoice_item_unit = "invoice_item_unit";
    public static final String COL_6_invoice_item_discount = "invoice_item_discount";
    public static final String COL_7_invoice_item_tax = "invoice_item_tax";
    public static final String COL_8_invoice_item_desc = "invoice_item_desc";


    /////////////////////---------Invoice Item manger------------------>>
    public static final String table_name_invoice_item_manager = "invoice_item_manager";
    public static final String COL_0_iim = "iim_id";
    public static final String COL_1_iim_dc_id = "dc_id";
    public static final String COL_2_iim_invoice_item_id = "invoice_item_id";



    /////////////////////--------------------------->>


    /////////////////////---------discount Item------------------>>
    public static final String table_name_discount_item = "discount_item";
    public static final String COL_0_di_id = "di_id";
    public static final String COL_1_di_dc_id = "dc_id";
    public static final String COL_2_di_type = "discount_type";
    public static final String COL_3_di_discount_value = "discount_value";


    /////////////////////--------------------------->>


    /////////////////////---------Currency ------------------>>
    public static final String table_name_currency = "currency";
    public static final String COL_0_c_id = "di_id";
    public static final String COL_1_c_dc_id = "dc_id";
    public static final String COL_3_c_country_name = "country";
    public static final String COL_2_c_currency_type = "currency_type";
    public static final String COL_2_c_currency_symbol = "currency_symbol";


    /////////////////////--------------------------->>


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + table_name_data_controller + "(" +
                COL_0_dc_id + " integer primary key autoincrement, " +
                COL_1_name + " text, " +
                COL_2_flag + " integer not null" +

                ")");


        db.execSQL("create table " + table_name_invoice_info + "(" +
                COL_0_ii_id + " integer primary key autoincrement, " +
                COL_1_ii_dc_id + " integer, " +
                COL_2_ii_name + " text, " +
                COL_3_ii_invoice_number + " text, " +
                COL_4_ii_date + " text, " +
                COL_5_ii_due_term + " text, " +
                COL_6_ii_due_date + " text, " +
                COL_7_ii_p_o + " text" +

                ")");

        db.execSQL("create table " + table_name_company + "(" +
                COL_0_comp_id + " integer primary key autoincrement, " +
                COL_1_comp_dc_id + " integer, " +
                COL_2_comp_name + " text, " +
                COL_3_comp_email + " text, " +
                COL_4_comp_phone + " text, " +
                COL_5_comp_add1 + " text, " +
                COL_6_comp_add2 + " text, " +
                COL_7_comp_website + " text, " +
                COL_8_comp_image + " blob" +

                ")");

        db.execSQL("create table " + table_name_client + "(" +
                COL_0_client_id + " integer primary key autoincrement, " +
                COL_1_client_dc_id + " integer, " +
                COL_2_client_name + " text, " +
                COL_3_client_email + " text, " +
                COL_4_client_phone + " text, " +
                COL_5_client_billing_add1 + " text, " +
                COL_6_client_billing_add2 + " text, " +
                COL_7_client_shipping_add1 + " text, " +
                COL_8_client_shipping_add2 + " text, " +
                COL_9_client_details + " text" +

                ")");

        db.execSQL("create table " + table_name_invoice_item + "(" +
                COL_0_invoice_item_id + " integer primary key autoincrement, " +
                COL_1_invoice_item_dc_id + " integer, " +
                COL_1_invoice_id + " integer, " +
                COL_2_invoice_item_name + " text, " +
                COL_3_invoice_item_price + " text, " +
                COL_4_invoice_item_quantity + " text, " +
                COL_5_invoice_item_unit + " text, " +
                COL_6_invoice_item_discount + " text, " +
                COL_7_invoice_item_tax + " text" +

                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        // onCreate(db);

    }


    public boolean insertData_data_controller(String name, int flag) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_name, name);//inserting data into column of table
        contentValues.put(COL_2_flag, flag);
        long result = db.insert(table_name_data_controller, null, contentValues);

        return result != -1;
    }

    public boolean update_data_controller(int dc_id, int flag) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_flag, flag);

        long result = db.update(table_name_data_controller, contentValues, " " + COL_0_dc_id + " = ?", new String[]{String.valueOf(dc_id)});
        if (result == -1) {

            return false;

        } else {
            return true;

        }
    }

    public Cursor getAllData_data_controller() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name_data_controller, null);
        return res;

    }


    public Cursor getLastRow_data_controller() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name_data_controller + " order by dc_id desc limit 1", null);
        return res;

    }

    public boolean delete_data_controller_OneRow(int dc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name_data_controller, " " + COL_0_dc_id + " = ?", new String[]{String.valueOf(dc_id)});
        if (result == -1) {
            return false;

        } else {
            return true;
        }

    }


    /////////////////////---------Invoice Info------------------>>

    public boolean insert_invoice_info_details(int dc_id, String name, String invoice_number,
                                               String created_date, String due_term,
                                               String due_date,
                                               String p_o) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_ii_dc_id, dc_id);
        contentValues.put(COL_2_ii_name, name);
        contentValues.put(COL_3_ii_invoice_number, invoice_number);
        contentValues.put(COL_4_ii_date, created_date);
        contentValues.put(COL_5_ii_due_term, due_term);
        contentValues.put(COL_6_ii_due_date, due_date);
        contentValues.put(COL_7_ii_p_o, p_o);


        long result = db.insert(table_name_invoice_info, null, contentValues);


        return result != -1;


    }


    public boolean update_invoice_info_details(int dc_id, String name, String invoice_number,
                                               String created_date, String due_term,
                                               String due_date,
                                               String p_o) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_ii_name, name);
        contentValues.put(COL_3_ii_invoice_number, invoice_number);
        contentValues.put(COL_4_ii_date, created_date);
        contentValues.put(COL_5_ii_due_term, due_term);
        contentValues.put(COL_6_ii_due_date, due_date);
        contentValues.put(COL_7_ii_p_o, p_o);


        long result = db.update(table_name_invoice_info, contentValues, " " + COL_1_ii_dc_id + " = ?", new String[]{String.valueOf(dc_id)});

        return result != -1;
    }


    public boolean delete_invoice_info_OneRow(int dc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name_invoice_info, " " + COL_1_ii_dc_id + " = ?", new String[]{String.valueOf(dc_id)});
        if (result == -1) {
            return false;

        } else {
            return true;

        }
    }


    public Cursor getRows_invoice_info(int my_dc_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name_invoice_info + " where " + COL_1_ii_dc_id + "='" + my_dc_id + "'", null);

        return res;

    }

    /////////////////////---------Company------------------>>

    public boolean insert_company_details(int dc_id, String name, String email,
                                          String phone, String add1,
                                          String add2, String website, byte[] img) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_comp_dc_id, dc_id);
        contentValues.put(COL_2_comp_name, name);
        contentValues.put(COL_3_comp_email, email);
        contentValues.put(COL_4_comp_phone, phone);
        contentValues.put(COL_5_comp_add1, add1);
        contentValues.put(COL_6_comp_add2, add2);
        contentValues.put(COL_7_comp_website, website);
        contentValues.put(COL_8_comp_image, img);

        long result = db.insert(table_name_company, null, contentValues);


        return result != -1;


    }


    public boolean update_company_details(int dc_id, String name, String email,
                                          String phone, String add1,
                                          String add2, String website, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_comp_name, name);
        contentValues.put(COL_3_comp_email, email);
        contentValues.put(COL_4_comp_phone, phone);
        contentValues.put(COL_5_comp_add1, add1);
        contentValues.put(COL_6_comp_add2, add2);
        contentValues.put(COL_7_comp_website, website);
        contentValues.put(COL_8_comp_image, img);

        Log.d(TAG, "update_company_details: " + new Gson().toJson(contentValues));

        long result = db.update(table_name_company, contentValues, " " + COL_1_comp_dc_id + " = ?", new String[]{String.valueOf(dc_id)});

        return result != -1;
    }


    public boolean delete_company_OneRow(int dc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name_company, " " + COL_1_comp_dc_id + " = ?", new String[]{String.valueOf(dc_id)});
        if (result == -1) {
            return false;

        } else {
            return true;

        }
    }


    public Cursor getRows_company(int my_dc_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name_company + " where " + COL_1_comp_dc_id + "='" + my_dc_id + "'", null);

        return res;

    }


    /////////////////////---------Client------------------>>

    public boolean insert_client_details(int dc_id, String name, String email,
                                         String phone, String bilAdd1,
                                         String bilAdd2, String shipAdd1,
                                         String shipAdd2, String clientDetails) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_ii_dc_id, dc_id);
        contentValues.put(COL_2_client_name, name);
        contentValues.put(COL_3_client_email, email);
        contentValues.put(COL_4_client_phone, phone);
        contentValues.put(COL_5_client_billing_add1, bilAdd1);
        contentValues.put(COL_6_client_billing_add2, bilAdd2);
        contentValues.put(COL_7_client_shipping_add1, shipAdd1);
        contentValues.put(COL_8_client_shipping_add2, shipAdd2);
        contentValues.put(COL_9_client_details, clientDetails);


        long result = db.insert(table_name_client, null, contentValues);


        return result != -1;


    }


    public boolean update_client_details(int dc_id, String name, String email,
                                         String phone, String bilAdd1,
                                         String bilAdd2, String shipAdd1,
                                         String shipAdd2, String clientDetails) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_ii_dc_id, dc_id);
        contentValues.put(COL_2_client_name, name);
        contentValues.put(COL_3_client_email, email);
        contentValues.put(COL_4_client_phone, phone);
        contentValues.put(COL_5_client_billing_add1, bilAdd1);
        contentValues.put(COL_6_client_billing_add2, bilAdd2);
        contentValues.put(COL_7_client_shipping_add1, shipAdd1);
        contentValues.put(COL_8_client_shipping_add2, shipAdd2);
        contentValues.put(COL_9_client_details, clientDetails);


        long result = db.update(table_name_client, contentValues, " " + COL_1_client_dc_id + " = ?", new String[]{String.valueOf(dc_id)});

        return result != -1;
    }


    public boolean delete_client_OneRow(int dc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name_client, " " + COL_1_client_dc_id + " = ?", new String[]{String.valueOf(dc_id)});
        if (result == -1) {
            return false;

        } else {
            return true;

        }
    }


    public Cursor getRows_client(int my_dc_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name_client + " where " + COL_1_ii_dc_id + "='" + my_dc_id + "'", null);

        return res;

    }

    /////////////////////---------Invoice Item------------------>>

    public boolean insert_invoice_item_details(int dc_id, String name, String price,
                                               String quantity, String unit,
                                               String discount, String taxRate) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1_invoice_item_dc_id, dc_id);
        contentValues.put(COL_2_invoice_item_name, name);
        contentValues.put(COL_3_invoice_item_price, price);
        contentValues.put(COL_4_invoice_item_quantity, quantity);
        contentValues.put(COL_5_invoice_item_unit, unit);
        contentValues.put(COL_6_invoice_item_discount, discount);
        contentValues.put(COL_7_invoice_item_tax, taxRate);


        long result = db.insert(table_name_invoice_item, null, contentValues);


        return result != -1;


    }


    public boolean update_invoice_item_details(int item_id, String name, String price,
                                               String quantity, String unit,
                                               String discount, String taxRate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2_invoice_item_name, name);
        contentValues.put(COL_3_invoice_item_price, price);
        contentValues.put(COL_4_invoice_item_quantity, quantity);
        contentValues.put(COL_5_invoice_item_unit, unit);
        contentValues.put(COL_6_invoice_item_discount, discount);
        contentValues.put(COL_7_invoice_item_tax, taxRate);


        long result = db.update(table_name_invoice_item, contentValues, " " + COL_0_invoice_item_id + " = ?", new String[]{String.valueOf(item_id)});

        return result != -1;
    }


    public boolean delete_invoice_item_OneRow(int ii_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name_invoice_item, " " + COL_0_invoice_item_id + " = ?", new String[]{String.valueOf(ii_id)});
        if (result == -1) {
            return false;

        } else {
            return true;

        }
    }


    public Cursor getRows_invoice_item(int dc_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name_invoice_item + " where " + COL_1_invoice_item_dc_id + "='" + dc_id + "'", null);

        return res;

    }

}
