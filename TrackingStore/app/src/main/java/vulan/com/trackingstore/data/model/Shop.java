package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static vulan.com.trackingstore.util.Constants.BASE_URL;

/**
 * Created by Thanh on 2/16/2017.
 */

public class Shop implements Serializable {
    @SerializedName("ID")
    private int Id;
    @SerializedName("Logo")
    private String mUrlLogo;
    @SerializedName("Name")
    private String mShopName;
    @SerializedName("Address")
    private String mAddress;
    @SerializedName("PhoneNumber")
    private String mPhoneNum;
    @SerializedName("Description")
    private String mDescript;
    @SerializedName("Email")
    private String mEmail;
    @SerializedName("UserID")
    private int mUserId;
    private double mMeter;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getmUrlLogo() {
        return BASE_URL + mUrlLogo;
    }

    public void setmUrlLogo(String mUrlLogo) {
        this.mUrlLogo = mUrlLogo;
    }

    public String getmShopName() {
        return mShopName;
    }

    public void setmShopName(String mShopName) {
        this.mShopName = mShopName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhoneNum() {
        return mPhoneNum;
    }

    public void setmPhoneNum(String mPhoneNum) {
        this.mPhoneNum = mPhoneNum;
    }

    public String getmDescript() {
        return mDescript;
    }

    public void setmDescript(String mDescript) {
        this.mDescript = mDescript;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public double getmMeter() {
        return mMeter;
    }

    public void setmMeter(double mMeter) {
        this.mMeter = mMeter;
    }
}
