package model.tea;

import java.util.Date;

public class TeaModel {
    private int id;
    private String supplier_name;
    private String supplier_id;
    private Date date;
    private String qty;
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TeaModel{" +
                "id=" + id +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_id='" + supplier_id + '\'' +
                ", date=" + date +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
