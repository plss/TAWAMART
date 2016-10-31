package mibh.mis.tawamart.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tawamart.R;
import mibh.mis.tawamart.service.CallService;
import mibh.mis.tawamart.utils.Utils;

/**
 * Created by Ponlakit on 8/30/2016.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private TextView tvVersionDetail;
    private ProgressBar pbVersion;
    private String newVersion;
    private String urlDownload;
    private String currentVersion;
    private String appName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        initInstances();
    }

    private void initInstances() {
        this.pbVersion = (ProgressBar) findViewById(R.id.pbVersion);
        this.tvVersionDetail = (TextView) findViewById(R.id.tvVersionDetail);
        currentVersion = Utils.getVersionName();
        appName = getApplicationName();

        if (!Utils.isGpsEnable()) {
            showDialog("ไม่สาสมารถค้นหาตำแหน่งได้");
        } else if (!Utils.isOnline()) {
            showDialog("ไม่สาสมารถเชื่อมต่อเครือข่ายได้");
        } else {
            new CheckingVersion().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private String getApplicationName() {
        //Get application info
        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }

        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");

        return applicationName;
    }

    private void showDialog(String message) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("ขออภัย")
                .setContentText(message)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                })
                .show();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private class CheckingVersion extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvVersionDetail.setText("Checking version");
        }

        @Override
        protected String doInBackground(Void... voids) {
            return new CallService().getActiveVersion();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("") || result.equals("error")) {
                showErrorDialog();
            } else {
                JSONArray data = null;
                try {
                    data = new JSONArray(result);
                    JSONObject c = data.getJSONObject(0);
                    newVersion = c.getString("CURRENT_VERSION");
                    urlDownload = c.getString("URL");
                    if (Double.parseDouble(currentVersion) != Double.parseDouble(newVersion)) {
                        new DownloadFileApk().execute();
                        /*new SweetAlertDialog(SplashScreenActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("มีการอัพเดทแอพพลิเคชั่น")
                                .setContentText("กรุณาทำการติดตั้งแอพพลิเคชั่นเวอร์ชั่นใหม่เพื่อใช้งาน")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        final String appPackageName = "mibh.mis.tawamart";
                                        try {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                        } catch (android.content.ActivityNotFoundException anfe) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                        }
                                        SplashScreenActivity.this.finish();
                                    }
                                })
                                .show();*/
                    } else {
                        startLoginActivity();
                    }
                } catch (JSONException e) {
                    showErrorDialog();
                }

            }
        }

        private void showErrorDialog() {
            /*AlertDialog.Builder builderSingle = new AlertDialog.Builder(LoadingScreen.this);
            builderSingle.setMessage("ไม่สามารถตรวจสอบเวอชันได้");
            builderSingle.setPositiveButton("ตกลง",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startLoginActivity();
                            finish();
                        }
                    });
            builderSingle.show();*/
            tvVersionDetail.setText("Could not check version");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startLoginActivity();
                }
            }, 1000);
        }

    }

    private class DownloadFileApk extends AsyncTask<Void, Integer, String> {

        private int downloadTotalSize = 0;
        private int downloadedSize = 0;
        private int MAX_APP_FILE_SIZE = 40 * 1024;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvVersionDetail.setText("Update to version " + newVersion);
            pbVersion.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(urlDownload);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(false);
                urlConnection.connect();
                String PATH = Environment.getExternalStorageDirectory() + "/download/";
                //DeleteFile(PATH, appName);
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, appName);
                FileOutputStream fileOutput = new FileOutputStream(outputFile);
                InputStream inputStream = urlConnection.getInputStream();
                downloadTotalSize = urlConnection.getContentLength();
                byte[] buffer = new byte[MAX_APP_FILE_SIZE];
                int bufferLength = 0;
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    if (pbVersion != null) {
                        int Progress = (downloadedSize * 100) / downloadTotalSize;
                        publishProgress(Progress);
                    }
                }
                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbVersion.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pbVersion.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + appName)), "application/vnd.android.package-archive");
            startActivity(intent);
            finish();
        }
    }

}
