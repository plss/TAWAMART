package mibh.mis.tawamart.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/3/2016.
 */

public class PoHeaderDao {

    @SerializedName("TRANSECTION_ID")
    @Expose
    public String transectionId;
    @SerializedName("DATE_CRE")
    @Expose
    public String dateCreate;
    @SerializedName("DATE_ORDER")
    @Expose
    public String dateOrder;
    @SerializedName("COST_CENTER")
    @Expose
    public String costCener;
    @SerializedName("VENDOR_ID")
    @Expose
    public String vendorId;
    @SerializedName("VENDOR_NAME")
    @Expose
    public String vendorName;
    @SerializedName("TAX_ID")
    @Expose
    public String taxId;
    @SerializedName("VENDOR_ADRR")
    @Expose
    public String vendorAddr;
    @SerializedName("STATUS_PO")
    @Expose
    public String statusPo;
    @SerializedName("STATUS_RECEIVE")
    @Expose
    public String statusReceive;
    @SerializedName("PRICE_AMT")
    @Expose
    public Double priceAmt;
    @SerializedName("PAYMENT_TYPE")
    @Expose
    public String paymentType;
    @SerializedName("VAT_TYPE")
    @Expose
    public String vatType;
    @SerializedName("VAT")
    @Expose
    public Double vat;
    @SerializedName("VAT_AMT")
    @Expose
    public String vatAmt;
    @SerializedName("DISCOUNT")
    @Expose
    public String discount;
    @SerializedName("REMARK_PO")
    @Expose
    public String remarkPo;
    @SerializedName("EMP_RECEIVEID")
    @Expose
    public String empReceiveId;
    @SerializedName("EMP_RECEIVE")
    @Expose
    public String getEmpReceive;
    @SerializedName("EMP_CREATEID")
    @Expose
    public String empCreateId;
    @SerializedName("EMP_CREATE")
    @Expose
    public String empCreate;
    @SerializedName("EMP_APPID")
    @Expose
    public String empAppId;
    @SerializedName("EMP_APP")
    @Expose
    public String empApp;
    @SerializedName("COCKINGZONE_ID")
    @Expose
    public String cookingzoneId;
    @SerializedName("COCKINGZONE_NAME")
    @Expose
    public String cookingzoneName;

    public String getTransectionId() {
        return transectionId;
    }

    public void setTransectionId(String transectionId) {
        this.transectionId = transectionId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getCostCener() {
        return costCener;
    }

    public void setCostCener(String costCener) {
        this.costCener = costCener;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getVendorAddr() {
        return vendorAddr;
    }

    public void setVendorAddr(String vendorAddr) {
        this.vendorAddr = vendorAddr;
    }

    public String getStatusPo() {
        return statusPo;
    }

    public void setStatusPo(String statusPo) {
        this.statusPo = statusPo;
    }

    public String getStatusReceive() {
        return statusReceive;
    }

    public void setStatusReceive(String statusReceive) {
        this.statusReceive = statusReceive;
    }

    public Double getPriceAmt() {
        return priceAmt;
    }

    public void setPriceAmt(Double priceAmt) {
        this.priceAmt = priceAmt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public String getVatAmt() {
        return vatAmt;
    }

    public void setVatAmt(String vatAmt) {
        this.vatAmt = vatAmt;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRemarkPo() {
        return remarkPo;
    }

    public void setRemarkPo(String remarkPo) {
        this.remarkPo = remarkPo;
    }

    public String getEmpReceiveId() {
        return empReceiveId;
    }

    public void setEmpReceiveId(String empReceiveId) {
        this.empReceiveId = empReceiveId;
    }

    public String getGetEmpReceive() {
        return getEmpReceive;
    }

    public void setGetEmpReceive(String getEmpReceive) {
        this.getEmpReceive = getEmpReceive;
    }

    public String getEmpCreateId() {
        return empCreateId;
    }

    public void setEmpCreateId(String empCreateId) {
        this.empCreateId = empCreateId;
    }

    public String getEmpCreate() {
        return empCreate;
    }

    public void setEmpCreate(String empCreate) {
        this.empCreate = empCreate;
    }

    public String getEmpAppId() {
        return empAppId;
    }

    public void setEmpAppId(String empAppId) {
        this.empAppId = empAppId;
    }

    public String getEmpApp() {
        return empApp;
    }

    public void setEmpApp(String empApp) {
        this.empApp = empApp;
    }

    public String getCookingzoneId() {
        return cookingzoneId;
    }

    public void setCookingzoneId(String cookingzoneId) {
        this.cookingzoneId = cookingzoneId;
    }

    public String getCookingzoneName() {
        return cookingzoneName;
    }

    public void setCookingzoneName(String cookingzoneName) {
        this.cookingzoneName = cookingzoneName;
    }
}
