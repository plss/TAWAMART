package mibh.mis.tawamart.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mibh.mis.tawamart.manager.Contextor;

/**
 * Created by Ponlakit on 8/15/2016.
 */

public class Utils {

    public static String getVersionName() {
        try {
            PackageInfo pInfo = Contextor.getInstance().getContext().getPackageManager().getPackageInfo(Contextor.getInstance().getContext().getPackageName(), 0);
            return pInfo.versionName;
        } catch (Exception e) {
            return "0.0";
        }
    }

    public static void hideSoftKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) Contextor.getInstance().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String priceWithDecimal(double number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.#");
        return formatter.format(number);
    }

    public static String getCurrentDateTime() {
        /* Add image capture time */
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String formattedDate = date.format(c.getTime());
        return formattedDate;
    }

    public static String genImageName(String tranId, String type) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        String formattedDate = date.format(c.getTime());
        return tranId + "_" + formattedDate + "_" + type + ".jpg";
    }

    public static String yyMMddFormatDate(String date) {
        String[] dateSplited = date.split("/");
        return String.valueOf(dateSplited[2]).substring(2) + dateSplited[1] + dateSplited[0];
    }

    public static String toDateFormat(String yyMMdd) {
        return yyMMdd.substring(4, 6) + "/" + yyMMdd.substring(2, 4) + "/" + (2000 + Integer.parseInt(yyMMdd.substring(0, 2)));
    }

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) Contextor.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean isGpsEnable() {
        LocationManager manager = (LocationManager) Contextor.getInstance().getContext().getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
        //// Old Version ////
        /*String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.equals("")) {
            return true;
        } else return false;*/
    }

}
