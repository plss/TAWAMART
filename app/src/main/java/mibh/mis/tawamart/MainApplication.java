package mibh.mis.tawamart;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.io.FileNotFoundException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mibh.mis.tawamart.activity.SplashScreenActivity;
import mibh.mis.tawamart.manager.Contextor;
import mibh.mis.tawamart.manager.Migration;
import mibh.mis.tawamart.manager.PreferencesManage;
import mibh.mis.tawamart.thread.UploadImg;

/**
 * Created by Ponlakit on 7/20/2016.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Contextor.getInstance().init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        try {
            Realm.migrateRealm(realmConfiguration, new Migration());
        } catch (FileNotFoundException ignored) {
            // If the Realm file doesn't exist, just ignore.
        }
        Realm.getInstance(realmConfiguration);
        createShortcutIcon();
        startService();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void createShortcutIcon() {
        boolean shortCutWasAlreadyAdded = PreferencesManage.getInstance().getShortcutAdded();
        if (shortCutWasAlreadyAdded) return;

        Intent shortcutIntent = new Intent(getApplicationContext(), SplashScreenActivity.class);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "TAWAMART");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);

        PreferencesManage.getInstance().setShortcutAdded(true);
    }

    private void startService() {
        Log.i("SERVICE", "Service created...");
        Intent startServiceIntent = new Intent(this, UploadImg.class);
        PendingIntent startWebServicePendingIntent = PendingIntent.getService(this, 0, startServiceIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 1000 * 30, startWebServicePendingIntent);
    }

}
