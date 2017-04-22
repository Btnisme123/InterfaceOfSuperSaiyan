package vulan.com.trackingstore.data.model;

/**
 * Created by Thanh on 4/22/2017.
 */

public class BeaconWithDistance {
    private String mMacId;
    private double mDistance;

    public BeaconWithDistance(String mMacId, double mDistance) {
        this.mMacId = mMacId;
        this.mDistance = mDistance;
    }

    public String getmMacId() {
        return mMacId;
    }

    public void setmMacId(String mMacId) {
        this.mMacId = mMacId;
    }

    public double getmDistance() {
        return mDistance;
    }

    public void setmDistance(double mDistance) {
        this.mDistance = mDistance;
    }
}
