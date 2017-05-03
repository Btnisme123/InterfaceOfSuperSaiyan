package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

import static vulan.com.trackingstore.util.Constants.BASE_URL;

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
    @SerializedName("Logo")
    private String mUrlImage;
    @SerializedName("PhoneNumber")
    private String mShopPhone;
    @SerializedName("Description")
    private String mShopDescipt;
    @SerializedName("Email")
    private String mShopEmail;

    private String mNotificationShop;

    public String getmNotificationShop() {
        return mNotificationShop;
    }

    public void setmNotificationShop(String mNotificationShop) {
        this.mNotificationShop = mNotificationShop;
    }

    public String getmShopEmail() {
        return mShopEmail;
    }

    public void setmShopEmail(String mShopEmail) {
        this.mShopEmail = mShopEmail;
    }

    public String getmShopPhone() {
        return mShopPhone;
    }

    public void setmShopPhone(String mShopPhone) {
        this.mShopPhone = mShopPhone;
    }

    public String getmShopDescipt() {
        return mShopDescipt;
    }

    public void setmShopDescipt(String mShopDescipt) {
        this.mShopDescipt = mShopDescipt;
    }

    private double mDistance;

    public double getmDistance() {
        return mDistance;
    }

    public void setmDistance(double mDistance) {
        this.mDistance = mDistance;
    }

    public String getmUrlImage() {
        return BASE_URL + mUrlImage;
    }

    public String getmUrlImageWithoutBase() {
        return mUrlImage;
    }

    public void setmUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
    }

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

    public void swap(BeaconWithShop beaconWithShop) {
        mMacId = beaconWithShop.getmMacId();
        mLocationX = beaconWithShop.getmLocationX();
        mLocationY = beaconWithShop.getmLocationY();
        mShopId = beaconWithShop.getmShopId();
        mInfoShop = beaconWithShop.getmInfoShop();
        mName = beaconWithShop.getmName();
        mShopName = beaconWithShop.getmShopName();
        mShopAddress = beaconWithShop.getmShopAddress();
        mUrlImage = beaconWithShop.getmUrlImageWithoutBase();
        mShopPhone = beaconWithShop.getmShopPhone();
        mShopDescipt = beaconWithShop.getmShopDescipt();
        mShopEmail = beaconWithShop.getmShopEmail();
    }
}
