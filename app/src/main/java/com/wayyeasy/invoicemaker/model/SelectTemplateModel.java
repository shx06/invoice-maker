package com.wayyeasy.invoicemaker.model;

public class SelectTemplateModel {

    int Image;
    String template_id;


    public SelectTemplateModel(int image, String template_id) {
        this.Image = image;
        this.template_id = template_id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
}
