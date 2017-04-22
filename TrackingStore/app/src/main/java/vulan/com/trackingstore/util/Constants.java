package vulan.com.trackingstore.util;

/**
 * Created by VULAN on 10/21/2016.
 */

public class Constants {
    public static final String BASE_URL = "http://doanbeacon.com/";

    public static final class Menu {
        public static final int MENU_HOME = 0;
        public static final int MENU_LIST_SHOP = 1;
        public static final int MENU_SEARCH = 2;
        public static final int MENU_SETTING = 3;
    }

    public static final class FragmentTag {
        public static final String HOME = "home fragment";
        public static final String LIST = "list fragment";
        public static final String SEARCH = "search fragment";
        public static final String SETTINGS = "setting fragment";
        public static final String SHOP = "shop fragment";
        public static final String PRODUCT = "product fragment";
        public static final String PRODUCT_DETAIL = "product detail fragment";
    }

    public static final class RecyclerViewType {
        public static final int CATEGORY_TYPE = 1;
        public static final int PROMOTION_TYPE = 2;
        public static final int PRODUCT_TYPE = 3;

    }

    public static final class StoreType {
        public static final int RESTAURANT = 1;
        public static final int MOVIE_THEATER = 2;
        public static final int PRODUCT_TYPE = 3;

    }

    public static final class ShopInfo {
        public static final String SHOP_MODEL = "shop";
        public static final String SHOP_ID = "shop id";
        public static final String CATEGORY_ID = "category id";
        public static final String CATEGORY_NAME = "category name";
        public static final String PRODUCT = "product";
    }

    public static final String MAC_ID = "mac ids";
    public static final String STATUS_SEARCH = "status search";
    public static final String NOTIFICATION_SHOW = "showNotification";
    public static final String SEARCH_KEYWORD= "showNotification";
}
