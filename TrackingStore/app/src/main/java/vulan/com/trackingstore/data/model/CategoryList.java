package vulan.com.trackingstore.data.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<ProductCategory> mProductList = new ArrayList<>();

    public CategoryList(List<ProductCategory> productList) {
        mProductList = productList;
    }

    public List<ProductCategory> getProductList() {
        return mProductList;
    }

    public void setProductList(List<ProductCategory> productList) {
        mProductList = productList;
    }
}
