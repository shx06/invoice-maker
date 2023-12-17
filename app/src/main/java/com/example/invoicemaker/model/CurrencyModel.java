package com.example.invoicemaker.model;

public class CurrencyModel {
    String country_name, country_symbol, currency_symbol;

    public CurrencyModel() {
    }

    public CurrencyModel(String country_name, String currency_symbol, String country_symbol) {
        this.country_name = country_name;
        this.country_symbol = country_symbol;
        this.currency_symbol = currency_symbol;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_symbol() {
        return country_symbol;
    }

    public void setCountry_symbol(String country_symbol) {
        this.country_symbol = country_symbol;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }
}
