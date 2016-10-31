package mibh.mis.tawamart.thread;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import mibh.mis.tawamart.manager.PreferencesManage;

/**
 * Created by Ponlakit on 7/14/2016.
 */

public class GetLocationName extends AsyncTask<String, Void, String> {

    private double lat, lng;

    public GetLocationName(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    protected String doInBackground(String... params) {
        String locationName = "";
        try {
            ArrayList<String> listLocationName = getFromLocation(lat, lng, 1);
            if (listLocationName.size() > 0) {
                for (int i = 0; i < listLocationName.size(); ++i) {
                    locationName += (listLocationName.get(i) + " ");
                }
            } else locationName = "Location not found";
        } catch (Exception e) {
            locationName = "Location not found";
        }
        return locationName;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        PreferencesManage.getInstance().setLat(lat);
        PreferencesManage.getInstance().setLong(lng);
        PreferencesManage.getInstance().setLocationName(s);
    }

    private static ArrayList<String> getFromLocation(double lat, double lng, int maxResult) throws Exception {
        ArrayList<String> AddressList;
        AddressList = new ArrayList<String>();
        String address = String.format(Locale.getDefault(), "http://maps.googleapis.com/maps/api/geocode/json?latlng=%1$f,%2$f&sensor=true&language=th", lat, lng);
        HttpGet httpGet = new HttpGet(address);

        HttpParams httpParameters = new BasicHttpParams();

        int timeoutConnection = 3000;
        try {
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        } catch (Exception ex) {
            Log.d("march", "err " + ex.getMessage());
        }

        int timeoutSocket = 5000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

        HttpClient client = new DefaultHttpClient(httpParameters);

        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            if (response == null) {
                return AddressList;
            }

            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();

            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject = new JSONObject(stringBuilder.toString());
            if ((jsonObject != null) && ("OK".equalsIgnoreCase(jsonObject.getString("status")))) {

                JSONArray results = jsonObject.getJSONArray("results");

                int i = 0;
                if (results.length() >= 2) {
                    i = 1;
                }

                {
                    JSONObject jo = results.getJSONObject(i);
                    JSONArray jaa = jo.getJSONArray("address_components");

                    for (int j = 0; j < jaa.length(); j++) {
                        JSONObject jotwo = jaa.getJSONObject(j);

                        String str_long_name = jotwo.getString("long_name");
                        String str_Type = jotwo.getString("types");

                        if ((str_Type.indexOf("postal_code") == -1)
                                && (str_Type.indexOf("country") == -1)) {
                            byte[] encode = str_long_name.getBytes("ISO-8859-1");
                            String addr = new String(encode);

                            AddressList.add(addr);
                        }
                    }
                }
            }

            return AddressList;

        } catch (ClientProtocolException e) {
            Log.d("march", "Error calling Google geocode webservice.");
        } catch (IOException e) {
            Log.d("march", "Error calling Google geocode webservice.");
        } catch (JSONException e) {
            Log.d("march", "Error parsing Google geocode webservice response.");
        }
        return AddressList;
    }

}

