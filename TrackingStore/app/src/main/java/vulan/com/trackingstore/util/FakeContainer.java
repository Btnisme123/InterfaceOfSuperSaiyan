package vulan.com.trackingstore.util;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Food;

/**
 * Created by VULAN on 10/21/2016.
 */

public class FakeContainer {
    public static List<DrawerRightItem> getRightItems() {
        List<DrawerRightItem> list = new ArrayList<>();
        list.add(new DrawerRightItem("GoGi Buffle", "50m"));
        list.add(new DrawerRightItem("KFC", "150m"));
        list.add(new DrawerRightItem("Lotteria", "100m"));
        list.add(new DrawerRightItem("GoGi Buffle", "100m"));
        list.add(new DrawerRightItem("KFC", "50m"));
        list.add(new DrawerRightItem("GoGi Buffle", "50m"));
        list.add(new DrawerRightItem("Lotteria", "50m"));
        list.add(new DrawerRightItem("GoGi Buffle", "50m"));
        return list;
    }

    public static Food getFood() {
        return new Food(R.drawable.ic_chicken, "Chicken", "Thịt gà thơm ngon ,bổ dưỡng ,giảm giá nhân ngày 20/10", 30);
    }

    public static DrawerRightItem getRightItem() {
        return new DrawerRightItem("BBQ", "20m");
    }
}
