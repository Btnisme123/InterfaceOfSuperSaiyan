package vulan.com.trackingstore.data.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Food> mProductList = new ArrayList<>();

    public CategoryList(List<Food> productList) {
        mProductList = productList;
    }

    public List<Food> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Food> productList) {
        mProductList = productList;
    }
}
