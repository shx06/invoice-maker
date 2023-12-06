package com.example.invoicemaker.model;

public class SingleItemModel {
    String itemName, itemDisc, itemPrice, itemQuantity, itemUnit, itemTax;
    int ii_id, dc_id, in_id;

    public SingleItemModel() {
    }

    public SingleItemModel(int ii_id, int dc_id, int in_id, String itemName, String itemPrice, String itemQuantity, String itemUnit, String itemDisc, String itemTax) {
        this.ii_id = ii_id;
        this.dc_id = dc_id;
        this.in_id = in_id;
        this.itemName = itemName;
        this.itemDisc = itemDisc;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemUnit = itemUnit;
        this.itemTax = itemTax;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDisc() {
        return itemDisc;
    }

    public void setItemDisc(String itemDisc) {
        this.itemDisc = itemDisc;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getItemTax() {
        return itemTax;
    }

    public void setItemTax(String itemTax) {
        this.itemTax = itemTax;
    }

    public int getIi_id() {
        return ii_id;
    }

    public void setIi_id(int ii_id) {
        this.ii_id = ii_id;
    }

    public int getDc_id() {
        return dc_id;
    }

    public void setDc_id(int dc_id) {
        this.dc_id = dc_id;
    }

    public int getIn_id() {
        return in_id;
    }

    public void setIn_id(int in_id) {
        this.in_id = in_id;
    }
}
