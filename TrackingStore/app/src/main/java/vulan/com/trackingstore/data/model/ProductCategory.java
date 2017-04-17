package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

import static vulan.com.trackingstore.util.Constants.BASE_URL;

/**
 * Created by VULAN on 10/21/2016.
 */

public class ProductCategory {
    @SerializedName("ID")
    private int Id;
    @SerializedName("Name")
    private String mNameCategory;
    @SerializedName("ShortName")
    private String mShortName;
    @SerializedName("DisplayOrder")
    private String mOrder;
    @SerializedName("ShopID")
    private int mShopId;
    @SerializedName("Image")
    private String mImageCategory;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getmNameCategory() {
        return mNameCategory;
    }

    public void setmNameCategory(String mNameCategory) {
        this.mNameCategory = mNameCategory;
    }

    public String getmShortName() {
        return mShortName;
    }

    public void setmShortName(String mShortName) {
        this.mShortName = mShortName;
    }

    public String getmOrder() {
        return mOrder;
    }

    public void setmOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public int getmShopId() {
        return mShopId;
    }

    public void setmShopId(int mShopId) {
        this.mShopId = mShopId;
    }

    public String getmImageCategory() {
        return BASE_URL + mImageCategory;
    }

    public void setmImageCategory(String mImageCategory) {
        this.mImageCategory = mImageCategory;
    }
}
