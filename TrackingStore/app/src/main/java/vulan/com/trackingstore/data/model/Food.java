package vulan.com.trackingstore.data.model;

/**
 * Created by VULAN on 10/21/2016.
 */

public class Food {

    private int mImageFood;
    private String mName;
    private String mContent;
    private int mSaleoffPercentage;
    private int mPrice;

    public Food(int mImageFood, String mName, String mContent,int mSaleoffPercentage) {
        this.mImageFood = mImageFood;
        this.mName = mName;
        this.mContent = mContent;
        this.mSaleoffPercentage=mSaleoffPercentage;
    }

    public int getImageFood() {
        return mImageFood;
    }

    public void setImageFood(int mImageFood) {
        this.mImageFood = mImageFood;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public int getSaleoffPercentage() {
        return mSaleoffPercentage;
    }

    public void setSaleoffPercentage(int mSaleoffPercentage) {
        this.mSaleoffPercentage = mSaleoffPercentage;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }
}
