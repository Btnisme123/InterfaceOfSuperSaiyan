package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ominext on 5/3/2017.
 */

public class NotificationShop {
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String mName;
    @SerializedName("TopHot")
    private String mContent;
    @SerializedName("Status")
    private int mStatus;
    @SerializedName("ShopID")
    private int mShopId;
    @SerializedName("ShopName")
    private String mShopName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public int getmShopId() {
        return mShopId;
    }

    public void setmShopId(int mShopId) {
        this.mShopId = mShopId;
    }

    public String getmShopName() {
        return mShopName;
    }

    public void setmShopName(String mShopName) {
        this.mShopName = mShopName;
    }
}
