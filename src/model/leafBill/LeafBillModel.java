package model.leafBill;

import java.time.Month;
import java.time.Year;
import java.util.Date;

public class LeafBillModel {
    private int id;

    private String supplier_id;
    private String advance_price;
    private String debit_price;
    private String gross_tqty;
    private String net_tqty;
    private String transport_rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getAdvance_price() {
        return advance_price;
    }

    public void setAdvance_price(String advance_price) {
        this.advance_price = advance_price;
    }

    public String getDebit_price() {
        return debit_price;
    }

    public void setDebit_price(String debit_price) {
        this.debit_price = debit_price;
    }

    public String getGross_tqty() {
        return gross_tqty;
    }

    public void setGross_tqty(String gross_tqty) {
        this.gross_tqty = gross_tqty;
    }

    public String getNet_tqty() {
        return net_tqty;
    }

    public void setNet_tqty(String net_tqty) {
        this.net_tqty = net_tqty;
    }

    public String getTransport_rate() {
        return transport_rate;
    }

    public void setTransport_rate(String transport_rate) {
        this.transport_rate = transport_rate;
    }

    @Override
    public String toString() {
        return "LeafBillModel{" +
                "id=" + id +
                ", supplier_id='" + supplier_id + '\'' +
                ", advance_price='" + advance_price + '\'' +
                ", debit_price='" + debit_price + '\'' +
                ", gross_tqty='" + gross_tqty + '\'' +
                ", net_tqty='" + net_tqty + '\'' +
                ", transport_rate='" + transport_rate + '\'' +
                '}';
    }
}
