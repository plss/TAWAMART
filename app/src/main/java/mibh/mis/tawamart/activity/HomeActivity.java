package mibh.mis.tawamart.activity;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yayandroid.locationmanager.*;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProviderType;

import mibh.mis.tawamart.R;
import mibh.mis.tawamart.fragment.HomeFragment;
import mibh.mis.tawamart.fragment.LoginFragment;
import mibh.mis.tawamart.thread.GetLocationName;

/**
 * Created by Ponlakit on 8/18/2016.
 */

public class HomeActivity extends LocationBaseActivity {

    private static final String TAG = "HomeActivity";
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.homeContainer, HomeFragment.newInstance()).commit();
        getLocation();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        View parentLayout = findViewById(R.id.homeContainer);
        Snackbar.make(parentLayout, "กด Back อีกครั้งเพื่อออก", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return new LocationConfiguration()
                .keepTracking(true)
                .askForGooglePlayServices(true)
                .setMinAccuracy(200.0f)
                .setWaitPeriod(ProviderType.GOOGLE_PLAY_SERVICES, 5 * 1000)
                .setWaitPeriod(ProviderType.GPS, 10 * 1000)
                .setWaitPeriod(ProviderType.NETWORK, 5 * 1000)
                .setGPSMessage("ต้องการเปิด GPS ?")
                .setRationalMessage("Gimme the permission!");
    }

    @Override
    public void onLocationFailed(int failType) {
        switch (failType) {
            case FailType.PERMISSION_DENIED: {
                Toast.makeText(this, "Couldn't get location, because user didn't give permission!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.GP_SERVICES_NOT_AVAILABLE:
            case FailType.GP_SERVICES_CONNECTION_FAIL: {
                Toast.makeText(this, "Couldn't get location, because Google Play Services not available!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                Toast.makeText(this, "Couldn't get location, because network is not accessible!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.TIMEOUT: {
                Toast.makeText(this, "Couldn't get location, and timeout!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.GP_SERVICES_SETTINGS_DENIED: {
                Toast.makeText(this, "Couldn't get location, because user didn't activate providers via settingsApi!", Toast.LENGTH_SHORT).show();
                break;
            }
            case FailType.GP_SERVICES_SETTINGS_DIALOG: {
                Toast.makeText(this, "Couldn't display settingsApi dialog!", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Toast.makeText(this, location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
        new GetLocationName(location.getLatitude(), location.getLongitude()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
