package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static vulan.com.trackingstore.util.Constants.BASE_URL;

/**
 * Created by Thanh on 2/21/2017.
 */

public class Product implements Serializable {
    @SerializedName("ID")
    private int Id;
    @SerializedName("Name")
    private String mName;
    @SerializedName("ShortName")
    private String mShortName;
    @SerializedName("Description")
    private String mDescipt;
    @SerializedName("Image")
    private String mImageProduct;
    @SerializedName("Price")
    private int mPrice;
    @SerializedName("Quantity")
    private int mQuantity;
    @SerializedName("Detail")
    private String mDetail;
    @SerializedName("CategoryID")
    private int mCategoryId;
    @SerializedName("TopHot")
    private int mPromotion;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmShortName() {
        return mShortName;
    }

    public void setmShortName(String mShortName) {
        this.mShortName = mShortName;
    }

    public String getmDescipt() {
        return mDescipt;
    }

    public void setmDescipt(String mDescipt) {
        this.mDescipt = mDescipt;
    }

    public String getmImageProduct() {
        return BASE_URL + mImageProduct;
    }

    public void setmImageProduct(String mImageProduct) {
        this.mImageProduct = mImageProduct;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getmPromotion() {
        return mPromotion;
    }

    public void setmPromotion(int mPromotion) {
        this.mPromotion = mPromotion;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmDetail() {
        return mDetail;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public int getmCategoryId() {
        return mCategoryId;
    }

    public void setmCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }
}
