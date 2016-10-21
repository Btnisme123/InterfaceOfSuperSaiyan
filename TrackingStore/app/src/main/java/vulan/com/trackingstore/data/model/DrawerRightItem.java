package vulan.com.trackingstore.data.model;

/**
 * Created by VULAN on 10/21/2016.
 */

public class DrawerRightItem {
    private String mMeter;
    private String mLocation;

    public DrawerRightItem(String mTitle, String mMeter) {
        this.mLocation = mTitle;
        this.mMeter = mMeter;
    }

    public String getTitle() {
        return mLocation;
    }

    public void setTitle(String mTitle) {
        this.mLocation = mTitle;
    }

    public String getMeter() {
        return mMeter;
    }

    public void setMeter(String mMeter) {
        this.mMeter = mMeter;
    }
}
