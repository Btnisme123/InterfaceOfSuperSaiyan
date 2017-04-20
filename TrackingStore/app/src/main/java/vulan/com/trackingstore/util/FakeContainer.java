package vulan.com.trackingstore.util;

import android.content.Context;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.data.model.DrawerRightItem;
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

//    public static ArrayList<Shop> getListShop() {
//        ArrayList<Shop> shopArrayList = new ArrayList<>();
//
//        shopArrayList.add(new Shop(R.drawable.nike_logo, "Nike", "192 Cầu Giấy,Q.Cầu Giấy,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.adidas_logo, "Adidas", "65 Nguyễn Văn Huyên,Q.Cầu Giấy,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.blues_logo, "The Blues", "Indochina Xuân Thủy,Q.Cầu Giấy,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.nike_logo, "Vans", "74 Nguyễn Văn Cừ,Q.Long Biên,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.adidas_logo, "Famous", "139 Núi Trúc,Q.Cầu Giấy,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.blues_logo, "Mẹ và bé", "Hồ Tùng Mậu,Q.Bắc Từ Liêm,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.nike_logo, "Shop tên dài xem nhìn ra gì không", "Địa chỉ cũng thật là dài xem nhìn có bị xấu không còn chỉnh"));
//        shopArrayList.add(new Shop(R.drawable.adidas_logo, "Đông Nobita", "167 Võ Chí Công,Q.Tây Hồ,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.blues_logo, "Lân Quýt", "27 Phạm Hùng,Q.Nam Từ Liêm,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.nike_logo, "Dell", "165 Xuân Thủy,Q.Cầu Giấy,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.adidas_logo, "Sion", "Liên Minh Huyền Thoại,Q.Bắc Từ Liêm,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.blues_logo, "Clothes", "19 Mai Dịch,Q.Bắc Từ Liêm,Hà Nội"));
//        shopArrayList.add(new Shop(R.drawable.nike_logo, "Sport", "42 Tôn Đức Thắng,Q.Đống Đa,Hà Nội"));
//        return shopArrayList;
//    }



    public static double computeAccuracy(Beacon beacon) {
        if (beacon.getRssi() == 0) {
            return -1.0;
        } else {
            double ratio = (double) beacon.getRssi() / (double) beacon.getMeasuredPower();
            double rssiCorrection = 0.96D + Math.pow((double) Math.abs(beacon.getRssi()), 3.0D) % 10.0D / 150.0D;
            return ratio <= 1.0D ? Math.pow(ratio, 9.98D) * rssiCorrection : (0.103D + 0.89978D * Math.pow(ratio, 7.71D)) * rssiCorrection;
        }
    }





    public static List<CustomMarkerView> getCustomMarker(Context context) {
        List<CustomMarkerView> list = new ArrayList<>();
        CustomMarkerView customMarkerView1 = new CustomMarkerView(context);
        CustomMarkerView customMarkerView2 = new CustomMarkerView(context);
        CustomMarkerView customMarkerView3 = new CustomMarkerView(context);
        customMarkerView1.setProperties(new LatLng(21.04517, 105.78434), 1,
                Constants.StoreType.MOVIE_THEATER, "Store 1 ");
        customMarkerView2.setProperties(new LatLng(21.04601, 105.7851), 1,
                Constants.StoreType.RESTAURANT, "Store 2 ");
        customMarkerView3.setProperties(new LatLng(21.04609, 105.78357), 1,
                Constants.StoreType.MOVIE_THEATER, "Store 3 ");
        list.add(customMarkerView1);
        list.add(customMarkerView2);
        list.add(customMarkerView3);
        return list;
    }
}
