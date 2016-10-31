package mibh.mis.tawamart.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/3/2016.
 */

public class ListDao {

    @SerializedName("SYSTEM_ID")
    @Expose
    public String systtemId;
    @SerializedName("RUNID")
    @Expose
    public String runId;
    @SerializedName("THDESC")
    @Expose
    public String thDesc;
    @SerializedName("ENDESC")
    @Expose
    public String enDesc;
    @SerializedName("DETAIL")
    @Expose
    public String detail;
    @SerializedName("GROUP_TYPE")
    @Expose
    public String groupType;
    @SerializedName("REF_RUNID")
    @Expose
    public Object refRunId;
    @SerializedName("STATUS")
    @Expose
    public String status;
    @SerializedName("REMARK")
    @Expose
    public String remark;
    @SerializedName("DATE_SERVER")
    @Expose
    public String dateServer;

    public String getSysttemId() {
        return systtemId;
    }

    public void setSysttemId(String systtemId) {
        this.systtemId = systtemId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getThDesc() {
        return thDesc;
    }

    public void setThDesc(String thDesc) {
        this.thDesc = thDesc;
    }

    public String getEnDesc() {
        return enDesc;
    }

    public void setEnDesc(String enDesc) {
        this.enDesc = enDesc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Object getRefRunId() {
        return refRunId;
    }

    public void setRefRunId(Object refRunId) {
        this.refRunId = refRunId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDateServer() {
        return dateServer;
    }

    public void setDateServer(String dateServer) {
        this.dateServer = dateServer;
    }
}
