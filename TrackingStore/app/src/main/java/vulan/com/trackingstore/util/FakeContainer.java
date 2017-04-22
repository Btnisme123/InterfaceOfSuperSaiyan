package vulan.com.trackingstore.util;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by VULAN on 10/21/2016.
 */

public class FakeContainer {
    public static double computeAccuracy(Beacon beacon) {
        if (beacon.getRssi() == 0) {
            return -1.0;
        } else {
            double ratio = (double) beacon.getRssi() / (double) beacon.getMeasuredPower();
            double rssiCorrection = 0.96D + Math.pow((double) Math.abs(beacon.getRssi()), 3.0D) % 10.0D / 150.0D;
            return ratio <= 1.0D ? Math.pow(ratio, 9.98D) * rssiCorrection : (0.103D + 0.89978D * Math.pow(ratio, 7.71D)) * rssiCorrection;
        }
    }
}
