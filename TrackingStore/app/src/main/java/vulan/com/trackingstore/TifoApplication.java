package vulan.com.trackingstore;

import android.app.Application;

import vulan.com.trackingstore.data.network.ApiRequest;

/**
 * Created by Ominext on 4/20/2017.
 */

public class TifoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiRequest.getInstance().init();
    }
}
