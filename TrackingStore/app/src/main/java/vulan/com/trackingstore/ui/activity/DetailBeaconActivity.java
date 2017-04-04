package vulan.com.trackingstore.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.util.FakeContainer;

import static vulan.com.trackingstore.ui.activity.MainActivity.ALL_ESTIMOTE_BEACONS_REGION;

public class DetailBeaconActivity extends Activity {
    private static final double RELATIVE_START_POS = 320.0 / 1110.0;
    private static final double RELATIVE_STOP_POS = 885.0 / 1110.0;

    private BeaconManager beaconManager;
    private Beacon beacon;
    private BeaconRegion region;
    private String mShopName;
    private String mMeter;
    private View dotView;
    private int startY = -1;
    private int segmentLength = -1;
    private TextView mShopNameText;
    private TextView mMeterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_beacon);
        init();
    }

    private void init() {
        beaconManager = new BeaconManager(this);
        dotView = findViewById(R.id.dot);
        mShopNameText = (TextView) findViewById(R.id.tv_shop_name);
        mMeterText = (TextView) findViewById(R.id.tv_meter);
        beacon = getIntent().getParcelableExtra(MainActivity.EXTRAS_BEACON);
        mShopName = getIntent().getStringExtra(MainActivity.SHOP_NAME);
        mMeter = getIntent().getStringExtra(MainActivity.METER);
        mShopNameText.setText(mShopName);
        //mMeterText.setText(mMeter);
        region = new BeaconRegion("regionid", beacon.getProximityUUID(), beacon.getMajor(), beacon.getMinor());
        if (beacon == null) {
            Toast.makeText(this, "Beacon not found in intent extras", Toast.LENGTH_LONG).show();
            finish();
        }
        final View view = findViewById(R.id.sonar);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                startY = (int) (RELATIVE_START_POS * view.getMeasuredHeight());
                int stopY = (int) (RELATIVE_STOP_POS * view.getMeasuredHeight());
                segmentLength = stopY - startY;

                dotView.setVisibility(View.VISIBLE);
                dotView.setTranslationY(computeDotPosY(beacon));
            }
        });
    }

    private int computeDotPosY(Beacon beacon) {
        // Let's put dot at the end of the scale when it's further than 6m.
        double distance = Math.min(FakeContainer.computeAccuracy(beacon), 6.0);
        return startY + (int) (segmentLength * (distance / 6.0));
    }

    @Override
    protected void onStart() {
        super.onStart();

        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion region, final List<Beacon> rangedBeacons) {
                // Note that results are not delivered on UI thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Just in case if there are multiple beacons with the same uuid, major, minor.
                        Beacon foundBeacon = null;
                        for (Beacon rangedBeacon : rangedBeacons) {
                            if (rangedBeacon.getMacAddress().equals(beacon.getMacAddress())) {
                                foundBeacon = rangedBeacon;
                            }
                        }
                        if (foundBeacon != null) {
                            updateDistanceView(foundBeacon);
                            mMeterText.setText(
                                    String.format("%f  m",FakeContainer.computeAccuracy(foundBeacon)));
                        }
                    }
                });
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        beaconManager.stopRanging(region);
        beaconManager.disconnect();

        super.onStop();
    }

    private void updateDistanceView(Beacon foundBeacon) {
        if (segmentLength == -1) {
            return;
        }
        dotView.animate().translationY(computeDotPosY(foundBeacon)).start();
    }
}
