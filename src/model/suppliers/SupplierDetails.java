package model.suppliers;

public class SupplierDetails {
    private String name;
    private String docRate;

    private String transportRate;
    private String arrears;


    public SupplierDetails(String name, String docRate, String transportRate, String arrears) {
        this.name = name;
        this.docRate = docRate;
        this.transportRate = transportRate;
        this.arrears = arrears;
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
}
