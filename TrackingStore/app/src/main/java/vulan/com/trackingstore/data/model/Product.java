package vulan.com.trackingstore.data.model;

/**
 * Created by Thanh on 2/21/2017.
 */

public class Product {
    private int mImageProduct;
    private String mName;
    private String mPrice;
    private String mPromotion;

    public Product(int mImageProduct, String mName, String mPrice, String mPromotion) {
        this.mImageProduct = mImageProduct;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mPromotion = mPromotion;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmImageProduct() {
        return mImageProduct;
    }

    public void setmImageProduct(int mImageProduct) {
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
}
