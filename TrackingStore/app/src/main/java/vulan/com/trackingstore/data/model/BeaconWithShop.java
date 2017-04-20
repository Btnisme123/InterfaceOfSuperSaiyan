package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ominext on 4/20/2017.
 */

public class BeaconWithShop {
    @SerializedName("MACID")
    private String mMacId;
    @SerializedName("LocationX")
    private double mLocationX;
    @SerializedName("LocationY")
    private double mLocationY;
    @SerializedName("ShopID")
    private int mShopId;
    @SerializedName("Information")
    private String mInfoShop;
    @SerializedName("Name")
    private String mName;
    @SerializedName("ShopName")
    private String mShopName;
    @SerializedName("ShopAddress")
    private String mShopAddress;

    public String getmMacId() {
        return mMacId;
    }

    public void setmMacId(String mMacId) {
        this.mMacId = mMacId;
    }

    public double getmLocationX() {
        return mLocationX;
    }

    public void setmLocationX(double mLocationX) {
        this.mLocationX = mLocationX;
    }

    public double getmLocationY() {
        return mLocationY;
    }

    public void setmLocationY(double mLocationY) {
        this.mLocationY = mLocationY;
    }

    public int getmShopId() {
        return mShopId;
    }

    public void setmShopId(int mShopId) {
        this.mShopId = mShopId;
    }

    public String getmInfoShop() {
        return mInfoShop;
    }

    public void setmInfoShop(String mInfoShop) {
        this.mInfoShop = mInfoShop;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmShopName() {
        return mShopName;
    }

    public void setmShopName(String mShopName) {
        this.mShopName = mShopName;
    }

    public String getmShopAddress() {
        return mShopAddress;
    }

    public void setmShopAddress(String mShopAddress) {
        this.mShopAddress = mShopAddress;
    }
}
