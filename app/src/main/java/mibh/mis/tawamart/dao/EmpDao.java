package mibh.mis.tawamart.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 8/20/2016.
 */

public class EmpDao {


    @SerializedName("ID_Emp")
    @Expose
    private String empId;
    @SerializedName("InitialT")
    @Expose
    private String initName;
    @SerializedName("FNameT")
    @Expose
    private String firstNameTh;
    @SerializedName("LNameT")
    @Expose
    private String lastNameTh;
    @SerializedName("FNameE")
    @Expose
    private String firstNameEn;
    @SerializedName("LNameE")
    @Expose
    private String lastNameEn;
    @SerializedName("NickName")
    @Expose
    private Object nickname;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("IdentityID")
    @Expose
    private String identityID;
    @SerializedName("WorkStatus")
    @Expose
    private String workStatus;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("EMail")
    @Expose
    private String email;
    @SerializedName("Post_NameT")
    @Expose
    private String postNameT;
    @SerializedName("Comp_Code")
    @Expose
    private String compCode;
    @SerializedName("Comp_NameT")
    @Expose
    private String compNameT;
    @SerializedName("CurrentAddress")
    @Expose
    private String currentAddress;
    @SerializedName("CurrentBuilding")
    @Expose
    private Object currentBuilding;
    @SerializedName("CurrentMoo")
    @Expose
    private String currentMoo;
    @SerializedName("CurrentRoad")
    @Expose
    private Object currentRoad;
    @SerializedName("CurrentTumbon")
    @Expose
    private String currentTumbon;
    @SerializedName("CurrentAmphur")
    @Expose
    private String currentAmphur;
    @SerializedName("CurrentProvince")
    @Expose
    private String currentProvince;
    @SerializedName("CurrentPostID")
    @Expose
    private String currentPostID;
    @SerializedName("ID_Post")
    @Expose
    private Integer idPost;
    @SerializedName("ID_Comp")
    @Expose
    private Integer idComp;
    @SerializedName("PicPath")
    @Expose
    private Object picPath;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getInitName() {
        return initName;
    }

    public void setInitName(String initName) {
        this.initName = initName;
    }

    public String getFirstNameTh() {
        return firstNameTh;
    }

    public void setFirstNameTh(String firstNameTh) {
        this.firstNameTh = firstNameTh;
    }

    public String getLastNameTh() {
        return lastNameTh;
    }

    public void setLastNameTh(String lastNameTh) {
        this.lastNameTh = lastNameTh;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdentityID() {
        return identityID;
    }

    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostNameT() {
        return postNameT;
    }

    public void setPostNameT(String postNameT) {
        this.postNameT = postNameT;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompNameT() {
        return compNameT;
    }

    public void setCompNameT(String compNameT) {
        this.compNameT = compNameT;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Object getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(Object currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public String getCurrentMoo() {
        return currentMoo;
    }

    public void setCurrentMoo(String currentMoo) {
        this.currentMoo = currentMoo;
    }

    public Object getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Object currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getCurrentTumbon() {
        return currentTumbon;
    }

    public void setCurrentTumbon(String currentTumbon) {
        this.currentTumbon = currentTumbon;
    }

    public String getCurrentAmphur() {
        return currentAmphur;
    }

    public void setCurrentAmphur(String currentAmphur) {
        this.currentAmphur = currentAmphur;
    }

    public String getCurrentProvince() {
        return currentProvince;
    }

    public void setCurrentProvince(String currentProvince) {
        this.currentProvince = currentProvince;
    }

    public String getCurrentPostID() {
        return currentPostID;
    }

    public void setCurrentPostID(String currentPostID) {
        this.currentPostID = currentPostID;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public Integer getIdComp() {
        return idComp;
    }

    public void setIdComp(Integer idComp) {
        this.idComp = idComp;
    }

    public Object getPicPath() {
        return picPath;
    }

    public void setPicPath(Object picPath) {
        this.picPath = picPath;
    }
}
