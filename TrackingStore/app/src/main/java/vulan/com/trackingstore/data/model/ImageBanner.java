package vulan.com.trackingstore.data.model;

/**
 * Created by vulan on 08/11/2016.
 */

public class ImageBanner {

    private String mTextTitle;
    private int mImageBanner;

    public ImageBanner(String mTextTitle, int mImageBanner) {
        this.mTextTitle = mTextTitle;
        this.mImageBanner = mImageBanner;
    }

    public ImageBanner() {
    }

    public int getImageBanner() {
        return mImageBanner;
    }

    public void setImageBanner(int mImageBanner) {
        this.mImageBanner = mImageBanner;
    }

    public String getTextTitle() {
        return mTextTitle;
    }

    public void setTextTitle(String mTextTitle) {
        this.mTextTitle = mTextTitle;
    }
}
