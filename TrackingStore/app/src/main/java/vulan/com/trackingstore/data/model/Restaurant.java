package vulan.com.trackingstore.data.model;

/**
 * Created by vulan on 15/11/2016.
 */

public class Restaurant {

    private String mTextTitle;
    private int mImage;

    public Restaurant(String mTextTitle, int mImage) {
        this.mTextTitle = mTextTitle;
        this.mImage = mImage;
    }

    public Restaurant() {
    }

    public int getImageBanner() {
        return mImage;
    }

    public void setImageBanner(int mImageBanner) {
        this.mImage = mImageBanner;
    }

    public String getTextTitle() {
        return mTextTitle;
    }

    public void setTextTitle(String mTextTitle) {
        this.mTextTitle = mTextTitle;
    }
}
