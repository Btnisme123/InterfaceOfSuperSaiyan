package vulan.com.trackingstore.data.model;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by Thanh on 2/16/2017.
 */

public class Shop {
    private int mImageShop;
    private String mShopName;
    private String mAddress;
    private double mMeter;

    public Shop(int imgShop, String mShopName, String mAddress) {
        this.mImageShop = imgShop;
        this.mShopName = mShopName;
        this.mAddress = mAddress;
        mMeter = 0;
    }

    public Shop(int imgShop, String mShopName, String mAddress, double meter) {
        this.mImageShop = imgShop;
        this.mShopName = mShopName;
        this.mAddress = mAddress;
        mMeter = meter;
    }

    public double getMeter() {
        return mMeter;
    }

    public void setMeter(double mMeter) {
        this.mMeter = mMeter;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmShopName(String mShopName) {
        this.mShopName = mShopName;
    }

    public void setmImageShop(int mImageShop) {
        this.mImageShop = mImageShop;
    }

    public String getmAddress() {

        return mAddress;
    }

    public String getmShopName() {
        return mShopName;
    }

    public int getmImageShop() {
        return mImageShop;
    }
}
