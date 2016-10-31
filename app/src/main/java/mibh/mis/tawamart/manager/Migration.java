package mibh.mis.tawamart.manager;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Ponlakit on 8/30/2016.
 */

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        Log.d("TEST Realm", "migrate: " + oldVersion + " " + newVersion);

        if (oldVersion == 0) {
            RealmObjectSchema imageSchema = schema.get("ImageStore");
            imageSchema.addField("locationName", String.class);
            oldVersion++;
        }
    }
}
