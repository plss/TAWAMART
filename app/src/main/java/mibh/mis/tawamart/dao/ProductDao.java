package mibh.mis.tawamart.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/3/2016.
 */

public class ProductDao {

    @SerializedName("PRODUCT_ID")
    @Expose
    public String productId;
    @SerializedName("PRODUCT_CODE")
    @Expose
    public String productCode;
    @SerializedName("PRODUCT_NAME")
    @Expose
    public String productName;
    @SerializedName("PRODUCT_DETAIL")
    @Expose
    public String productDetail;
    @SerializedName("PRODUCT_GID")
    @Expose
    public String productGroupId;
    @SerializedName("PRODUCT_GNAME")
    @Expose
    public String productGroupName;
    @SerializedName("PRODUCT_TID")
    @Expose
    public String productTypeId;
    @SerializedName("PRODUCT_TNAME")
    @Expose
    public String productTypeName;
    @SerializedName("UNIT_ID")
    @Expose
    public String unitId;
    @SerializedName("UNIT_NAME")
    @Expose
    public String initName;
    @SerializedName("PRICE_DE")
    @Expose
    public Double price;
    @SerializedName("STATUS")
    @Expose
    public String status;
    @SerializedName("DATE_INPUT")
    @Expose
    public String dateInput;
    @SerializedName("VENDOR_ID_DE")
    @Expose
    public String vendorId;
    @SerializedName("VENDOR_NAME_DE")
    @Expose
    public String vendorName;
    @SerializedName("DATE_S")
    @Expose
    public String dateS;
    @SerializedName("DATE_E")
    @Expose
    public String dateE;
    @SerializedName("EMP_ID")
    @Expose
    public String empId;
    @SerializedName("EMP_INPUT")
    @Expose
    public String empInput;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(String productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
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

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public String getDateE() {
        return dateE;
    }

    public void setDateE(String dateE) {
        this.dateE = dateE;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpInput() {
        return empInput;
    }

    public void setEmpInput(String empInput) {
        this.empInput = empInput;
    }
}
