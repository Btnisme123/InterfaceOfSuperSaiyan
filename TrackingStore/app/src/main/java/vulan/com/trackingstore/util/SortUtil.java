package vulan.com.trackingstore.util;

import java.util.List;

import vulan.com.trackingstore.data.model.BeaconWithShop;

/**
 * Created by Thanh on 4/30/2017.
 */

public class SortUtil {
    public List<BeaconWithShop> bubbleSort(List<BeaconWithShop> beaconWithShops) {
        int numberOfStudents = beaconWithShops.size();
        int counter, position;
        BeaconWithShop temp = new BeaconWithShop();
        for (counter = 0; counter < numberOfStudents - 1; counter++) {
            for (position = 0; position < numberOfStudents - 1 - counter; position++) {
                if (beaconWithShops.get(position).getmDistance() > beaconWithShops.get(position + 1).getmDistance()) {
                    temp.swap(beaconWithShops.get(position));
                    beaconWithShops.get(position).swap(beaconWithShops.get(position + 1));
                    beaconWithShops.get(position + 1).swap(temp);
                }
            }
        }
        return beaconWithShops;
    }
}
