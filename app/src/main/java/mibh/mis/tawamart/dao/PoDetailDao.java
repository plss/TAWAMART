package mibh.mis.tawamart.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/3/2016.
 */

public class PoDetailDao implements Parcelable {

    @SerializedName("TRANSECTION_ID")
    @Expose
    public String transectionId;
    @SerializedName("ITEM_ID")
    @Expose
    public Double itemId;
    @SerializedName("PRODUCT_ID")
    @Expose
    public String productId;
    @SerializedName("PRODUCT_CODE")
    @Expose
    public String productCode;
    @SerializedName("PRODUCT_NAME")
    @Expose
    public String productName;
    @SerializedName("REMARK_PRODUCT")
    @Expose
    public String remarkProduct;
    @SerializedName("VOL_PRODUCT")
    @Expose
    public Double volProduct;
    @SerializedName("UNIT_ID")
    @Expose
    public String unitId;
    @SerializedName("UNIT_NAME")
    @Expose
    public String unitName;
    @SerializedName("PRICE_PO")
    @Expose
    public Double pricePo;
    @SerializedName("PRICE_NET")
    @Expose
    public Double priceNet;
    @SerializedName("DISCOUNT")
    @Expose
    public String discount;
    @SerializedName("STATUS_PO")
    @Expose
    public String statusPo;
    @SerializedName("STATUS_RECEIVE")
    @Expose
    public String statusReceive;
    @SerializedName("REMARK_RECEIVE")
    @Expose
    public String remarkReceive;
    @SerializedName("VOL_RECEIVE")
    @Expose
    public Double volReceive;
    @SerializedName("PRICE_RECEIVE")
    @Expose
    public String priceReceive;
    @SerializedName("EMP_RECEIVEID")
    @Expose
    public String empReceiveId;
    @SerializedName("EMP_RECEIVE")
    @Expose
    public String empReceive;
    @SerializedName("EMP_CREATEID")
    @Expose
    public String empCreateId;
    @SerializedName("EMP_CREATE")
    @Expose
    public String empCreate;
    @SerializedName("DATE_PO")
    @Expose
    public String datePo;
    @SerializedName("VENDOR_CODE")
    @Expose
    public String vendorCode;

    public PoDetailDao() {

    }

    protected PoDetailDao(Parcel in) {
        transectionId = in.readString();
        productId = in.readString();
        productCode = in.readString();
        productName = in.readString();
        remarkProduct = in.readString();
        unitId = in.readString();
        unitName = in.readString();
        discount = in.readString();
        statusPo = in.readString();
        statusReceive = in.readString();
        remarkReceive = in.readString();
        priceReceive = in.readString();
        empReceiveId = in.readString();
        empReceive = in.readString();
        empCreateId = in.readString();
        empCreate = in.readString();
        datePo = in.readString();
        vendorCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(transectionId);
        dest.writeString(productId);
        dest.writeString(productCode);
        dest.writeString(productName);
        dest.writeString(remarkProduct);
        dest.writeString(unitId);
        dest.writeString(unitName);
        dest.writeString(discount);
        dest.writeString(statusPo);
        dest.writeString(statusReceive);
        dest.writeString(remarkReceive);
        dest.writeString(priceReceive);
        dest.writeString(empReceiveId);
        dest.writeString(empReceive);
        dest.writeString(empCreateId);
        dest.writeString(empCreate);
        dest.writeString(datePo);
        dest.writeString(vendorCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PoDetailDao> CREATOR = new Creator<PoDetailDao>() {
        @Override
        public PoDetailDao createFromParcel(Parcel in) {
            return new PoDetailDao(in);
        }

        @Override
        public PoDetailDao[] newArray(int size) {
            return new PoDetailDao[size];
        }
    };

    public String getTransectionId() {
        return transectionId;
    }

    public void setTransectionId(String transectionId) {
        this.transectionId = transectionId;
    }

    public Double getItemId() {
        return itemId;
    }

    public void setItemId(Double itemId) {
        this.itemId = itemId;
    }

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

    public String getRemarkProduct() {
        return remarkProduct;
    }

    public void setRemarkProduct(String remarkProduct) {
        this.remarkProduct = remarkProduct;
    }

    public Double getVolProduct() {
        return volProduct;
    }

    public void setVolProduct(Double volProduct) {
        this.volProduct = volProduct;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getPricePo() {
        return pricePo;
    }

    public void setPricePo(Double pricePo) {
        this.pricePo = pricePo;
    }

    public Double getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(Double priceNet) {
        this.priceNet = priceNet;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public String getRemarkReceive() {
        return remarkReceive;
    }

    public void setRemarkReceive(String remarkReceive) {
        this.remarkReceive = remarkReceive;
    }

    public Double getVolReceive() {
        return volReceive;
    }

    public void setVolReceive(Double volReceive) {
        this.volReceive = volReceive;
    }

    public String getPriceReceive() {
        return priceReceive;
    }

    public void setPriceReceive(String priceReceive) {
        this.priceReceive = priceReceive;
    }

    public String getEmpReceiveId() {
        return empReceiveId;
    }

    public void setEmpReceiveId(String empReceiveId) {
        this.empReceiveId = empReceiveId;
    }

    public String getEmpReceive() {
        return empReceive;
    }

    public void setEmpReceive(String empReceive) {
        this.empReceive = empReceive;
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

    public String getDatePo() {
        return datePo;
    }

    public void setDatePo(String datePo) {
        this.datePo = datePo;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
}
