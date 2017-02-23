package vulan.com.trackingstore.util;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.data.model.Restaurant;
import vulan.com.trackingstore.util.customview.CustomMarkerView;

/**
 * Created by VULAN on 10/21/2016.
 */

public class FakeContainer {
    public static List<DrawerRightItem> getRightItems() {
        List<DrawerRightItem> list = new ArrayList<>();
        list.add(new DrawerRightItem("GoGi Buffe", "50m"));
        list.add(new DrawerRightItem("KFC", "150m"));
        list.add(new DrawerRightItem("Lotteria", "100m"));
        list.add(new DrawerRightItem("GoGi Buffe", "100m"));
        list.add(new DrawerRightItem("KFC", "50m"));
        list.add(new DrawerRightItem("GoGi Buffe", "50m"));
        list.add(new DrawerRightItem("Lotteria", "50m"));
        list.add(new DrawerRightItem("GoGi Buffe", "50m"));
        return list;
    }

    public static DrawerRightItem getRightItem() {
        return new DrawerRightItem("Lotteria 139 Cau Giay Street", "20m");
    }


    public static List<ProductCategory> getListCategory(int type) {
        List<ProductCategory> categoryList = new ArrayList<>();
        if (type == 1) {
            categoryList.add(new ProductCategory(R.drawable.jeans, "Jean"));
            categoryList.add(new ProductCategory(R.drawable.blouse, "Blouse"));
            categoryList.add(new ProductCategory(R.drawable.ic_shirt, "Shirt"));
            categoryList.add(new ProductCategory(R.drawable.jacket, "Jacket"));
            categoryList.add(new ProductCategory(R.drawable.tie, "Accessories"));
        } else if (type == 2) {
            categoryList.add(new ProductCategory(R.drawable.jeans, "Jean"));
            categoryList.add(new ProductCategory(R.drawable.blouse, "Blouse"));
            categoryList.add(new ProductCategory(R.drawable.ic_shirt, "Shirt"));
            categoryList.add(new ProductCategory(R.drawable.dress, "Dress"));
        } else {
            categoryList.add(new ProductCategory(R.drawable.jeans, "Jean"));
            categoryList.add(new ProductCategory(R.drawable.ic_shirt, "Shirt"));
            categoryList.add(new ProductCategory(R.drawable.jacket, "Jacket"));
        }

        return categoryList;
    }

    public static List<Product> getListProduct(int type) {
        List<Product> products = new ArrayList<>();
        if (type == 1) {
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
        } else if (type == 2) {
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
        } else {
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
            products.add(new Product(R.drawable.jacket_product, "Jacket", "400000", "5"));
            products.add(new Product(R.drawable.dress_product, "Dress", "200000", "10"));
        }
        return products;
    }

    public static List<Restaurant> getListRestaurant(int numberRestaurant) {
        List<Restaurant> list = new ArrayList<>();
        Restaurant restaurantKFC = new Restaurant("KFC", R.drawable.kfc_logo);
        Restaurant restaurantMcDonald = new Restaurant("KFC", R.drawable.mc_donald);
        Restaurant lottle = new Restaurant("Lottle", R.drawable.lottle);
        Restaurant viettel = new Restaurant("Viettel", R.drawable.viettel_store);
        Restaurant fpt = new Restaurant("Fpt", R.drawable.fpt);
        switch (numberRestaurant) {
            case 1:
                list.add(restaurantKFC);
                break;
            case 2:
                list.add(restaurantKFC);
                list.add(restaurantMcDonald);
                break;
            case 3:
                list.add(restaurantKFC);
                list.add(restaurantMcDonald);
                list.add(lottle);
                break;
            case 4:
                list.add(restaurantKFC);
                list.add(restaurantMcDonald);
                list.add(lottle);
                list.add(viettel);
                break;
            case 5:
                list.add(restaurantKFC);
                list.add(restaurantMcDonald);
                list.add(lottle);
                list.add(viettel);
                list.add(fpt);
                break;
        }
        return list;
    }

    public static List<CustomMarkerView> getCustomMarker(Context context) {
        List<CustomMarkerView> list = new ArrayList<>();
        CustomMarkerView customMarkerView1 = new CustomMarkerView(context);
        CustomMarkerView customMarkerView2 = new CustomMarkerView(context);
        CustomMarkerView customMarkerView3 = new CustomMarkerView(context);
        customMarkerView1.setProperties(new LatLng(21.007380, 105.793139),
                1,
                Constants.StoreType.MOVIE_THEATER,
                "Store 1 ");
        customMarkerView2.setProperties(new LatLng(21.007480, 105.793139),
                1,
                Constants.StoreType.RESTAURANT,
                "Store 2 ");
        customMarkerView3.setProperties(new LatLng(21.007580, 105.793139),
                1,
                Constants.StoreType.MOVIE_THEATER,
                "Store 3 ");
        list.add(customMarkerView1);
        list.add(customMarkerView2);
        list.add(customMarkerView3);
        return list;
    }
}
