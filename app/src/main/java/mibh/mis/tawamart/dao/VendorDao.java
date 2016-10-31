package mibh.mis.tawamart.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/3/2016.
 */

public class VendorDao {

    @SerializedName("VENDOR_ID")
    @Expose
    public String vendorId;
    @SerializedName("VENDOR_CODE")
    @Expose
    public String vendorCode;
    @SerializedName("TITLE_NAME")
    @Expose
    public String titleName;
    @SerializedName("VENDOR_NAME")
    @Expose
    public String vendorName;
    @SerializedName("VENDOR_SHORTNAME")
    @Expose
    public String vendorShortName;
    @SerializedName("TAX_ID")
    @Expose
    public String taxId;
    @SerializedName("CREDIT_TERM")
    @Expose
    public Double creditTerm;
    @SerializedName("ADDR")
    @Expose
    public String address;
    @SerializedName("TAMBON_ID")
    @Expose
    public String tambonId;
    @SerializedName("TAMBON_NAME")
    @Expose
    public String tambonName;
    @SerializedName("AMPHOE_ID")
    @Expose
    public String amphoeId;
    @SerializedName("AMPHOE_NAME")
    @Expose
    public String amphoeName;
    @SerializedName("PROVINCE_ID")
    @Expose
    public String provinceId;
    @SerializedName("PROVINCE_NAME")
    @Expose
    public String provinceName;
    @SerializedName("ZIP_CODE")
    @Expose
    public String zipCode;
    @SerializedName("TEL")
    @Expose
    public String tel;
    @SerializedName("LINE_ID")
    @Expose
    public String lineId;
    @SerializedName("CONTR_EMAIL")
    @Expose
    public String email;
    @SerializedName("STATUS")
    @Expose
    public String status;
    @SerializedName("DATE_INPUT")
    @Expose
    public String dateInput;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorShortName() {
        return vendorShortName;
    }

    public void setVendorShortName(String vendorShortName) {
        this.vendorShortName = vendorShortName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Double getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(Double creditTerm) {
        this.creditTerm = creditTerm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTambonId() {
        return tambonId;
    }

    public void setTambonId(String tambonId) {
        this.tambonId = tambonId;
    }

    public String getTambonName() {
        return tambonName;
    }

    public void setTambonName(String tambonName) {
        this.tambonName = tambonName;
    }

    public String getAmphoeId() {
        return amphoeId;
    }

    public void setAmphoeId(String amphoeId) {
        this.amphoeId = amphoeId;
    }

    public String getAmphoeName() {
        return amphoeName;
    }

    public void setAmphoeName(String amphoeName) {
        this.amphoeName = amphoeName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
