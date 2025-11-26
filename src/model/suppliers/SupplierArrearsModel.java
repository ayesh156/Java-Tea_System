package model.suppliers;

import java.sql.Timestamp;

public class SupplierArrearsModel {
    private int id;
    private int supplierId;
    private int year;
    private int month;
    private double arrears;
    private Timestamp createdAt;

    public SupplierArrearsModel() {
    }

    public SupplierArrearsModel(int supplierId, int year, int month, double arrears) {
        this.supplierId = supplierId;
        this.year = year;
        this.month = month;
        this.arrears = arrears;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getArrears() {
        return arrears;
    }

    public void setArrears(double arrears) {
        this.arrears = arrears;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
