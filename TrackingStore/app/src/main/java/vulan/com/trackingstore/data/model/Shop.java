package vulan.com.trackingstore.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static vulan.com.trackingstore.util.Constants.BASE_URL;

/**
 * Created by Thanh on 2/16/2017.
 */

public class Shop implements Serializable, Parcelable {
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
    private double mMeter;

    public Shop(int id, String mUrlLogo, String mShopName, String mAddress, String mPhoneNum, String mDescript, String mEmail) {
        this.Id = id;
        this.mUrlLogo = mUrlLogo;
        this.mShopName = mShopName;
        this.mAddress = mAddress;
        this.mPhoneNum = mPhoneNum;
        this.mDescript = mDescript;
        this.mEmail = mEmail;
    }

    public Shop() {

    }

    protected Shop(Parcel in) {
        Id = in.readInt();
        mUrlLogo = in.readString();
        mShopName = in.readString();
        mAddress = in.readString();
        mPhoneNum = in.readString();
        mDescript = in.readString();
        mEmail = in.readString();
        mMeter = in.readDouble();
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

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

    public double getmMeter() {
        return mMeter;
    }

    public void setmMeter(double mMeter) {
        this.mMeter = mMeter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(mUrlLogo);
        dest.writeString(mShopName);
        dest.writeString(mAddress);
        dest.writeString(mPhoneNum);
        dest.writeString(mDescript);
        dest.writeString(mEmail);
        dest.writeDouble(mMeter);
    }

    public void swap(Shop shop) {
        Id = shop.getId();
        mUrlLogo = shop.getmUrlLogo();
        mAddress = shop.getmAddress();
        mPhoneNum = shop.getmPhoneNum();
        mDescript = shop.getmDescript();
        mEmail = shop.getmEmail();
        mShopName = shop.getmShopName();
        mMeter = shop.getmMeter();
    }
}
