package com.wayyesy.invoicemaker.model;

public class DataModel {
    private String invoiceTitle, date, amtPaid, clientName, totalBill, billType;

    public DataModel() {
    }

    public DataModel(String invoiceTitle, String date, String amtPaid, String clientName, String totalBill, String billType) {
        this.invoiceTitle = invoiceTitle;
        this.date = date;
        this.amtPaid = amtPaid;
        this.clientName = clientName;
        this.totalBill = totalBill;
        this.billType = billType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmtPaid() {
        return amtPaid;
    }

    public void setAmtPaid(String amtPaid) {
        this.amtPaid = amtPaid;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
