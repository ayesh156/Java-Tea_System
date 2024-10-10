package model.dailyLeaf;

import java.util.Date;

public class DailyLeafModel {

    private int id;
    private String supplier_name;
    private String supplier_id;
    private Date date;
    private String gross_qty;
    private String net_qty;
    private String transport_rate;

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

    public String getGross_qty() {
        return gross_qty;
    }

    public void setGross_qty(String gross_qty) {
        this.gross_qty = gross_qty;
    }



    public String getNet_qty() {
        return net_qty;
    }

    public void setNet_qty(String net_qty) {
        this.net_qty = net_qty;
    }

    public String getTransport_rate() {
        return transport_rate;
    }

    public void setTransport_rate(String transport_rate) {
        this.transport_rate = transport_rate;
    }

    @Override
    public String toString() {
        return "DailyLeafModel{" +
                "id=" + id +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_id='" + supplier_id + '\'' +
                ", date=" + date +
                ", gross_qty='" + gross_qty + '\'' +
                ", net_qty='" + net_qty + '\'' +
                ", transport_rate='" + transport_rate + '\'' +
                '}';
    }
}
