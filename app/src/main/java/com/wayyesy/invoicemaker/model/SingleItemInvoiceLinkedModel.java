package com.wayyesy.invoicemaker.model;

public class SingleItemInvoiceLinkedModel {
    int iim_id, dc_id, invoice_item_id;

    public SingleItemInvoiceLinkedModel() {
    }

    public SingleItemInvoiceLinkedModel(int iim_id, int dc_id, int invoice_item_id) {
        this.iim_id = iim_id;
        this.dc_id = dc_id;
        this.invoice_item_id = invoice_item_id;
    }

    public int getIim_id() {
        return iim_id;
    }

    public void setIim_id(int iim_id) {
        this.iim_id = iim_id;
    }

    public int getDc_id() {
        return dc_id;
    }

    public void setDc_id(int dc_id) {
        this.dc_id = dc_id;
    }

    public int getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }
}
