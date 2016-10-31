package mibh.mis.tawamart.thread;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kobjects.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import mibh.mis.tawamart.manager.RealmManager;
import mibh.mis.tawamart.realmobj.ImageStore;
import mibh.mis.tawamart.service.CallService;

/**
 * Created by ponlakiss on 10/16/2015.
 */
public class UploadImg extends IntentService {

    String filename;
    String tranId;
    String type;
    String empInput;
    String lat;
    String lng;
    String locationName;
    String comment;
    String dateImg;
    List<ImageStore> imageStores;

    public UploadImg() {
        super("ScheduledService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        imageStores = RealmManager.getInstance().getAllImageInactive();
        for (int i = 0; i < imageStores.size(); i++) {
            filename = imageStores.get(i).getFilename();
            tranId = imageStores.get(i).getTranId();
            type = imageStores.get(i).getType();
            empInput = imageStores.get(i).getEmpInput();
            lat = imageStores.get(i).getLat();
            lng = imageStores.get(i).getLng();
            locationName = imageStores.get(i).getLocationName();
            comment = imageStores.get(i).getComment();
            dateImg = imageStores.get(i).getDateImage();
            String resultSave = savePhoto(filename, tranId, type == null ? "" : type, empInput == null ? "" : empInput, lat, lng, locationName == null ? "" : locationName, comment == null ? "" : comment, dateImg);
            if (!resultSave.equalsIgnoreCase("error") && !resultSave.equalsIgnoreCase("false")) {
                RealmManager.getInstance().updateUploadStatus(filename);
            }
        }
    }

    private String savePhoto(String filename,
                             String tranId,
                             String type,
                             String empInput,
                             String lat,
                             String lng,
                             String locationName,
                             String comment,
                             String dateImg) {
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "TAWAMART");
        File output = new File(imagesFolder, filename);
        if (output.exists()) {
            try {
                JSONArray array = new JSONArray();
                JSONObject dataIm_reg;

                JSONObject Img_file;
                JSONArray arrayIm_reg = new JSONArray();
                byte[] byteArray;
                String strBase64;
                Img_file = new JSONObject();
                FileInputStream fis = new FileInputStream(output);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];

                int bytesRead;
                while ((bytesRead = fis.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                byteArray = bos.toByteArray();
                strBase64 = Base64.encode(byteArray);

                Img_file.put("file_name", filename);
                Img_file.put("img_file", strBase64);
                array.put(Img_file);
                dataIm_reg = new JSONObject();
                dataIm_reg.put("Req_id", tranId);
                dataIm_reg.put("Type_img", "0");
                dataIm_reg.put("File_name", filename);
                dataIm_reg.put("Lat", lat);
                dataIm_reg.put("Lng", lng);
                dataIm_reg.put("date_image", dateImg);
                dataIm_reg.put("Status", "Active");
                arrayIm_reg.put(dataIm_reg);

                if (comment.length() >= 296) {
                    comment = comment.substring(0, 295) + "..";
                }

                String resultState = new CallService().saveStateImage(tranId, lat + "," + lng, locationName, type, empInput, "", filename, comment);
                String resultSavePic = new CallService().savePic(array.toString(), arrayIm_reg.toString());
                System.gc();
                Log.d("Save pic", resultSavePic + " " + resultState);
                return resultSavePic;
            } catch (Exception e) {
                Log.d("Error save photo", e.toString());
                return "error";
            }
        } else {
            return "error";
        }
    }

}
