package vulan.com.trackingstore;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Ominext on 4/28/2017.
 */

public class EnableMultiDex extends MultiDexApplication {
    private static EnableMultiDex enableMultiDex;
    public static Context context;

    public EnableMultiDex() {
        enableMultiDex = this;
    }

    public static EnableMultiDex getEnableMultiDexApp() {
        return enableMultiDex;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getApplicationContext());
    }
}
