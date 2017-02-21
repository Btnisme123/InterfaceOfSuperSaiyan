package vulan.com.trackingstore.data.model;

/**
 * Created by VULAN on 10/21/2016.
 */

public class ProductCategory {

    private int mImageCategory;
    private String mNameCategory;

    public ProductCategory(int mImageCategory, String mNameCategory) {
        this.mImageCategory = mImageCategory;
        this.mNameCategory = mNameCategory;
    }

    public int getmImageCategory() {
        return mImageCategory;
    }

    public void setmImageCategory(int mImageCategory) {
        this.mImageCategory = mImageCategory;
    }

    public String getmNameCategory() {
        return mNameCategory;
    }

    public void setmNameCategory(String mNameCategory) {
        this.mNameCategory = mNameCategory;
    }
}
