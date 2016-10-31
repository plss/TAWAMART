package mibh.mis.tawamart.realmobj;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ponlakit on 8/20/2016.
 */

public class ImageStore extends RealmObject {

    @PrimaryKey
    String filename;
    String dateImage;
    String tranId;
    String type;
    String empInput;
    String lat;
    String lng;
    String locationName;
    String comment;
    String statusUpload;

    public String getFilename() {
        return filename;
    }

    public String getStatusUpload() {
        return statusUpload;
    }

    public void setStatusUpload(String statusUpload) {
        this.statusUpload = statusUpload;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDateImage() {
        return dateImage;
    }

    public void setDateImage(String dateImage) {
        this.dateImage = dateImage;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmpInput() {
        return empInput;
    }

    public void setEmpInput(String empInput) {
        this.empInput = empInput;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
