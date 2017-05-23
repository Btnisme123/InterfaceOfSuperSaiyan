package vulan.com.trackingstore.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Thanh on 5/23/2017.
 */

public class ToastUtil {
    public static void makeToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
