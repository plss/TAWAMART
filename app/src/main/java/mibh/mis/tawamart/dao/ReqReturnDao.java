package mibh.mis.tawamart.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/20/2016.
 */

public class ReqReturnDao implements Parcelable {

    @SerializedName("DATE_TRAN")
    @Expose
    public String dateTran;

    @SerializedName("RUN_ID")
    @Expose
    public String runId;

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
    public double volProduct;

    @SerializedName("UNIT_ID")
    @Expose
    public String unitId;

    @SerializedName("UNIT_NAME")
    @Expose
    public String unitName;

    @SerializedName("COST_CENTER")
    @Expose
    public String costCenter;

    @SerializedName("PART_ZONE")
    @Expose
    public String partZone;

    @SerializedName("PART_NAME")
    @Expose
    public String partName;

    @SerializedName("STATUS")
    @Expose
    public String status;

    @SerializedName("TYPE_INOUT")
    @Expose
    public String typeInOut;

    @SerializedName("REMARK_RECEIVE")
    @Expose
    public String remarkReceive;

    @SerializedName("EMP_ID")
    @Expose
    public String empId;

    @SerializedName("EMP_NAME")
    @Expose
    public String empName;

    @SerializedName("PRICE_PRODUCT")
    @Expose
    public double priceProduct;

    @SerializedName("PRICE_AMT")
    @Expose
    public String priceAmt;

    @SerializedName("REF_ID")
    @Expose
    public String refId;

    @SerializedName("REF_NAME")
    @Expose
    public String refName;

    @SerializedName("REF_CODE")
    @Expose
    public String refCode;


    public ReqReturnDao() {
    }


    protected ReqReturnDao(Parcel in) {
        dateTran = in.readString();
        runId = in.readString();
        productId = in.readString();
        productCode = in.readString();
        productName = in.readString();
        remarkProduct = in.readString();
        volProduct = in.readDouble();
        unitId = in.readString();
        unitName = in.readString();
        costCenter = in.readString();
        partZone = in.readString();
        partName = in.readString();
        status = in.readString();
        typeInOut = in.readString();
        remarkReceive = in.readString();
        empId = in.readString();
        empName = in.readString();
        priceProduct = in.readDouble();
        priceAmt = in.readString();
        refId = in.readString();
        refName = in.readString();
        refCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateTran);
        dest.writeString(runId);
        dest.writeString(productId);
        dest.writeString(productCode);
        dest.writeString(productName);
        dest.writeString(remarkProduct);
        dest.writeDouble(volProduct);
        dest.writeString(unitId);
        dest.writeString(unitName);
        dest.writeString(costCenter);
        dest.writeString(partZone);
        dest.writeString(partName);
        dest.writeString(status);
        dest.writeString(typeInOut);
        dest.writeString(remarkReceive);
        dest.writeString(empId);
        dest.writeString(empName);
        dest.writeDouble(priceProduct);
        dest.writeString(priceAmt);
        dest.writeString(refId);
        dest.writeString(refName);
        dest.writeString(refCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReqReturnDao> CREATOR = new Creator<ReqReturnDao>() {
        @Override
        public ReqReturnDao createFromParcel(Parcel in) {
            return new ReqReturnDao(in);
        }

        @Override
        public ReqReturnDao[] newArray(int size) {
            return new ReqReturnDao[size];
        }
    };

    public String getDateTran() {
        return dateTran;
    }

    public void setDateTran(String dateTran) {
        this.dateTran = dateTran;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
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

    public double getVolProduct() {
        return volProduct;
    }

    public void setVolProduct(double volProduct) {
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

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getPartZone() {
        return partZone;
    }

    public void setPartZone(String partZone) {
        this.partZone = partZone;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeInOut() {
        return typeInOut;
    }

    public void setTypeInOut(String typeInOut) {
        this.typeInOut = typeInOut;
    }

    public String getRemarkReceive() {
        return remarkReceive;
    }

    public void setRemarkReceive(String remarkReceive) {
        this.remarkReceive = remarkReceive;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getPriceAmt() {
        return priceAmt;
    }

    public void setPriceAmt(String priceAmt) {
        this.priceAmt = priceAmt;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }
}
