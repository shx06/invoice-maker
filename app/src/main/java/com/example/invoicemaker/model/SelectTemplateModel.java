package com.example.invoicemaker.model;

public class SelectTemplateModel {

    int Image;
    String templateName;


    public SelectTemplateModel(int image, String templateName) {
        this.Image = image;
        this.templateName = templateName;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
