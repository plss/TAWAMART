package mibh.mis.tawamart.manager;

import android.content.Context;

public class PreferencesManage {

    private static PreferencesManage instance;

    public static PreferencesManage getInstance() {
        if (instance == null)
            instance = new PreferencesManage();
        return instance;
    }

    private Context mContext;

    private PreferencesManage() {
        mContext = Contextor.getInstance().getContext();
    }

    public static class Key {
        public static final String TAWA = "TAWA";
        public static final String username = "username";
        public static final String password = "password";
        public static final String lat = "lat";
        public static final String lng = "lng";
        public static final String locationName = "locationName";
        public static final String vendorDefault = "vendorDefault";
        public static final String cookingZoneDefault = "cookingZoneDefault";
        public static final String shortcutAdded = "shortcutAdded";
    }

    public String getUsername() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.username, "");
    }

    public String getPassword() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.password, "");
    }

    public void setUsername(String username) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.username, username).apply();
    }

    public void setPassword(String password) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.password, password).apply();
    }

    public void setLat(double lat) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.lat, String.format("%.5f", lat)).apply();
    }

    public void setLong(double lng) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.lng, String.format("%.5f", lng)).apply();
    }

    public void setVendorDefault(String vendorDefault) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.vendorDefault, vendorDefault).apply();
    }

    public void setCookingZoneDefault(String cookingZoneDefault) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.cookingZoneDefault, cookingZoneDefault).apply();
    }

    public void setLocationName(String locationName) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putString(Key.locationName, locationName).apply();
    }

    public void setShortcutAdded(Boolean added) {
        mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).edit().putBoolean(Key.shortcutAdded, added).apply();
    }

    public String getLat() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.lat, "0");
    }

    public String getLong() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.lng, "0");
    }

    public String getVendorDefault() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.vendorDefault, "");
    }

    public String getCookingZonrDefault() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.cookingZoneDefault, "");
    }

    public String getLocationName() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getString(Key.locationName, "Location not found");
    }

    public Boolean getShortcutAdded() {
        return mContext.getSharedPreferences(Key.TAWA, Context.MODE_PRIVATE).getBoolean(Key.shortcutAdded, false);
    }
}
