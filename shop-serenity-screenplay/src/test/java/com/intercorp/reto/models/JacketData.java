package com.intercorp.reto.models;

public class JacketData {
    private String category;
    private String productName;
    private String size;
    private int quantity;
    private boolean valid;

    public JacketData() {}

    public JacketData(String category, String productName, String size, int quantity, boolean valid) {
        this.category = category;
        this.productName = productName;
        this.size = size;
        this.quantity = quantity;
        this.valid = valid;
    }

    public String getCategory() { return category; }
    public String getProductName() { return productName; }
    public String getSize() { return size; }
    public int getQuantity() { return quantity; }
    public boolean isValid() { return valid; }
}
