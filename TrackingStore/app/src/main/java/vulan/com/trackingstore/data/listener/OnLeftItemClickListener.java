package vulan.com.trackingstore.data.listener;

import com.estimote.coresdk.recognition.packets.Beacon;

/**
 * Created by VULAN on 10/21/2016.
 */

public interface OnLeftItemClickListener {
    void onLeftItemClick(Beacon beacon, String shopName, String meter);
}
