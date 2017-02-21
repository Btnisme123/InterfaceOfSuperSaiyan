package vulan.com.trackingstore.util;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.CategoryList;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.ImageBanner;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.data.model.Restaurant;

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

    public static List<Object> getFoodDrinkList() {
        List<Object> items = new ArrayList<>();
        List<ProductCategory> itemFoods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            itemFoods.add(new Product(R.drawable.ic_chicken));
        }
        CategoryList categoryListFood = new CategoryList(itemFoods);
        CategoryList categoryListDrink = new CategoryList(itemFoods);
        CategoryList categoryListOther = new CategoryList(itemFoods);

        items.add(new ImageBanner("Food", R.drawable.food_icon));
        items.add(categoryListFood);
        items.add(new ImageBanner("Drink", R.drawable.drink_icon));
        items.add(categoryListDrink);
        items.add(new ImageBanner("Other", R.drawable.other_icon));
        items.add(categoryListOther);
        return items;
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
}
