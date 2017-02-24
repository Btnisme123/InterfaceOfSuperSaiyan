package vulan.com.trackingstore.data.model;

/**
 * Created by Thanh on 2/16/2017.
 */

public class Shop {
    private int mImageShop;
    private String mShopName;
    private String mAddress;

    public Shop(int imgShop,String mShopName,String mAddress){
        this.mImageShop = imgShop;
        this.mShopName = mShopName;
        this.mAddress = mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmShopName(String mShopName) {
        this.mShopName = mShopName;
    }

    public void setmImageShop(int mImageShop) {
        this.mImageShop = mImageShop;
    }

    public String getmAddress() {

        return mAddress;
    }

    public String getmShopName() {
        return mShopName;
    }

    public int getmImageShop() {
        return mImageShop;
    }
}
