package vulan.com.trackingstore.data.model;

import com.google.gson.annotations.SerializedName;

import static vulan.com.trackingstore.util.Constants.BASE_URL;

/**
 * Created by Thanh on 2/21/2017.
 */

public class Product {
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
    private String mPrice;
    @SerializedName("PromotionPrice")
    private String mPromotion;
    @SerializedName("Quantity")
    private int mQuantity;
    @SerializedName("Detail")
    private String mDetail;

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

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmPromotion() {
        return mPromotion;
    }

    public void setmPromotion(String mPromotion) {
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
}
