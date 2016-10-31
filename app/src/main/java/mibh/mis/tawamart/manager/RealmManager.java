package mibh.mis.tawamart.manager;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import mibh.mis.tawamart.realmobj.ImageStore;
import mibh.mis.tawamart.utils.Utils;

/**
 * Created by Ponlakit on 8/20/2016.
 */

public class RealmManager {

    private static RealmManager instance;

    public static RealmManager getInstance() {
        if (instance == null)
            instance = new RealmManager();
        return instance;
    }

    private Context mContext;

    private RealmManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public void insertImage(String filename,
                            String tranId,
                            String type,
                            String empInput,
                            String lat,
                            String lng,
                            String locationName,
                            String comment) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ImageStore imgStore = new ImageStore();
        imgStore.setFilename(filename);
        imgStore.setComment(comment);
        imgStore.setTranId(tranId);
        imgStore.setType(type);
        imgStore.setEmpInput(empInput);
        imgStore.setLat(lat);
        imgStore.setLng(lng);
        imgStore.setLocationName(locationName);
        imgStore.setDateImage(Utils.getCurrentDateTime());
        imgStore.setStatusUpload("INACTIVE");
        realm.copyToRealmOrUpdate(imgStore);
        realm.commitTransaction();
        realm.close();

    }

    public List<ImageStore> getAllImage() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageStore> result = realm.where(ImageStore.class)
                .findAllSorted("dateImage", Sort.DESCENDING);
        List<ImageStore> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public List<ImageStore> getAllImageInactive() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ImageStore> result = realm.where(ImageStore.class)
                .equalTo("statusUpload", "INACTIVE")
                .findAll();
        List<ImageStore> listResult = realm.copyFromRealm(result);
        realm.close();
        return listResult;
    }

    public void updateUploadStatus(String filename) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ImageStore result = realm.where(ImageStore.class)
                .equalTo("filename", filename)
                .findFirst();
        result.setStatusUpload("ACTIVE");
        realm.commitTransaction();
        realm.close();
    }

    public void deleteImageData(String filename) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(ImageStore.class)
                .equalTo("filename", filename)
                .findFirst().deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

}
