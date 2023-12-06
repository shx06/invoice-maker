package com.example.invoicemaker.model;

public class DataControllerModel {

    int dc_id ;
    String dc_name ;
    int flag ;


    public DataControllerModel(int dc_id, String dc_name, int flag) {
        this.dc_id = dc_id;
        this.dc_name = dc_name;
        this.flag = flag;
    }

    public int getDc_id() {
        return dc_id;
    }

    public void setDc_id(int dc_id) {
        this.dc_id = dc_id;
    }

    public String getDc_name() {
        return dc_name;
    }

    public void setDc_name(String dc_name) {
        this.dc_name = dc_name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
