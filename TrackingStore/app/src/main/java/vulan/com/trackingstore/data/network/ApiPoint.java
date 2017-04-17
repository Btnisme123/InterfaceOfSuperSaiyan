package vulan.com.trackingstore.data.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.data.model.Shop;

/**
 * Created by Vin on 2/10/17.
 */

public interface ApiPoint {

    @GET("ShopApi/getAll")
    Call<List<Shop>> getAllShop();

    @GET("CategoryApi/getByShop/")
    Call<List<ProductCategory>> getListCategory(@Query("shopId") int shopId);

    @GET("ProductApi/getInforByCategory/")
    Call<List<Product>> getListProduct(@Query("category") int categoryId);

}


