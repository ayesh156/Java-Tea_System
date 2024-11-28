package model.leafBill;

import java.time.Month;
import java.time.Year;
import java.util.Date;

public class LeafBillModel {
    private int id;

    private int supplier_id;
    private String supplier_name;
    private String doc_rate;
    private String advance_price;
    private String debit_price;
    private String gross_tqty;
    private String net_tqty;
    private String transport_rate;
    private String tea;
    private String manure;
    private String dolomite;
    private String leafRate;
    private String totalLeafPrice;
    private String totalDeductions;
    private String arrears;
    private String lastArrears;
    private String finalAmount;
    private boolean arrearsSetZero;
    private String newArrears;
    private String billNumber;
    private String lastModify;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getDoc_rate() {
        return doc_rate;
    }

    public void setDoc_rate(String doc_rate) {
        this.doc_rate = doc_rate;
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

    public String getTea() {
        return tea;
    }

    public void setTea(String tea) {
        this.tea = tea;
    }

    public String getManure() {
        return manure;
    }

    public void setManure(String manure) {
        this.manure = manure;
    }

    public String getDolomite() {
        return dolomite;
    }

    public void setDolomite(String dolomite) {
        this.dolomite = dolomite;
    }

    public String getLeafRate() {
        return leafRate;
    }

    public void setLeafRate(String leafRate) {
        this.leafRate = leafRate;
    }

    public String getTotalLeafPrice() {
        return totalLeafPrice;
    }

    public void setTotalLeafPrice(String totalLeafPrice) {
        this.totalLeafPrice = totalLeafPrice;
    }

    public String getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(String totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public String getArrears() {
        return arrears;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public boolean isArrearsSetZero() {
        return arrearsSetZero;
    }

    public void setArrearsSetZero(boolean arrearsSetZero) {
        this.arrearsSetZero = arrearsSetZero;
    }

    public String getNewArrears() {
        return newArrears;
    }

    public void setNewArrears(String newArrears) {
        this.newArrears = newArrears;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public String getLastArrears() {
        return lastArrears;
    }

    public void setLastArrears(String lastArrears) {
        this.lastArrears = lastArrears;
    }

    @Override
    public String toString() {
        return "LeafBillModel{" +
                "id=" + id +
                ", supplier_id=" + supplier_id +
                ", supplier_name='" + supplier_name + '\'' +
                ", doc_rate='" + doc_rate + '\'' +
                ", advance_price='" + advance_price + '\'' +
                ", debit_price='" + debit_price + '\'' +
                ", gross_tqty='" + gross_tqty + '\'' +
                ", net_tqty='" + net_tqty + '\'' +
                ", transport_rate='" + transport_rate + '\'' +
                ", tea='" + tea + '\'' +
                ", manure='" + manure + '\'' +
                ", dolomite='" + dolomite + '\'' +
                ", leafRate='" + leafRate + '\'' +
                ", totalLeafPrice='" + totalLeafPrice + '\'' +
                ", totalDeductions='" + totalDeductions + '\'' +
                ", arrears='" + arrears + '\'' +
                ", lastArrears='" + lastArrears + '\'' +
                ", finalAmount='" + finalAmount + '\'' +
                ", arrearsSetZero=" + arrearsSetZero +
                ", newArrears='" + newArrears + '\'' +
                ", billNumber='" + billNumber + '\'' +
                ", lastModify='" + lastModify + '\'' +
                '}';
    }
}
