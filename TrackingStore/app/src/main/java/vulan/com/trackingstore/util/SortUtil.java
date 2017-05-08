package vulan.com.trackingstore.util;

import java.util.List;

import vulan.com.trackingstore.data.model.BeaconWithShop;
import vulan.com.trackingstore.data.model.Shop;

/**
 * Created by Thanh on 4/30/2017.
 */

public class SortUtil {
    public List<BeaconWithShop> sortListBeacon(List<BeaconWithShop> beaconWithShops) {
        int sizeOfList = beaconWithShops.size();
        int counter, position;
        BeaconWithShop temp = new BeaconWithShop();
        for (counter = 0; counter < sizeOfList - 1; counter++) {
            for (position = 0; position < sizeOfList - 1 - counter; position++) {
                if (beaconWithShops.get(position).getmDistance() > beaconWithShops.get(position + 1).getmDistance()) {
                    temp.swap(beaconWithShops.get(position));
                    beaconWithShops.get(position).swap(beaconWithShops.get(position + 1));
                    beaconWithShops.get(position + 1).swap(temp);
                }
            }
        }
        return beaconWithShops;
    }

    public List<Shop> sortListShop(List<Shop> shopList) {
        int sizeOfList = shopList.size();
        int counter, position;
        Shop temp = new Shop();
        for (counter = 0; counter < sizeOfList - 1; counter++) {
            for (position = 0; position < sizeOfList - 1 - counter; position++) {
                if (shopList.get(position).getmMeter() > shopList.get(position + 1).getmMeter()) {
                    temp.swap(shopList.get(position));
                    shopList.get(position).swap(shopList.get(position + 1));
                    shopList.get(position + 1).swap(temp);
                }
            }
        }
        return shopList;
    }
}
