package vulan.com.trackingstore.util;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.CategoryList;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Food;
import vulan.com.trackingstore.data.model.ImageBanner;

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

    public static Food getFood() {
        return new Food(R.drawable.ic_chicken, "Chicken", "Thịt gà thơm ngon ,bổ dưỡng ,giảm giá nhân ngày 20/10", 30);
    }

    public static DrawerRightItem getRightItem() {
        return new DrawerRightItem("Lotteria 139 Cau Giay Street", "20m");
    }

    public static List<Food> getListFood() {
        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if(i%2==0){
                foodList.add(new Food(R.drawable.ic_chicken, "Nem chua", 20, 14));
            }
            foodList.add(new Food(R.drawable.nemran, "Nem rán ", 20, 14));
        }
        return foodList;
    }

    public static List<Object> getFoodDrinkList() {
        List<Object> items = new ArrayList<>();
        List<Food> itemFoods = new ArrayList<>();
        for(int i=0;i<10;i++){
            itemFoods.add(new Food(R.drawable.ic_chicken));
        }
        CategoryList categoryListFood=new CategoryList(itemFoods);
        CategoryList categoryListDrink=new CategoryList(itemFoods);
        CategoryList categoryListOther=new CategoryList(itemFoods);

        items.add(new ImageBanner("Food",R.drawable.food_icon));
        items.add(categoryListFood);
        items.add(new ImageBanner("Drink",R.drawable.drink_icon));
        items.add(categoryListDrink);
        items.add(new ImageBanner("Other",R.drawable.other_icon));
        items.add(categoryListOther);
        return items;
    }
}
