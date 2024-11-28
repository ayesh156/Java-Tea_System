package model.suppliers;

import java.util.Date;

public class SupplierDetails {
    private String name;
    private String docRate;

    private String transportRate;
    private String lastArrears;
    private String arrears;
    private Date lastModify;


    public SupplierDetails(String name, String docRate, String transportRate, String lastArrears, String arrears, Date lastModify) {
        this.name = name;
        this.docRate = docRate;
        this.transportRate = transportRate;
        this.lastArrears = lastArrears;
        this.arrears = arrears;
        this.lastModify = lastModify;
    }

    public String getName() {
        return name;
    }

    public String getDocRate() {
        return docRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocRate(String docRate) {
        this.docRate = docRate;
    }

    public String getTransportRate() {
        return transportRate;
    }

    public void setTransportRate(String transportRate) {
        this.transportRate = transportRate;
    }

    public String getArrears() {
        return arrears;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public String getLastArrears() {
        return lastArrears;
    }

    public void setLastArrears(String lastArrears) {
        this.lastArrears = lastArrears;
    }

}
