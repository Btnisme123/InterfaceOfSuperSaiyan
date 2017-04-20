package vulan.com.trackingstore.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.activity.MainActivity;

/**
 * Created by Thanh on 4/21/2017.
 */

public class NotificationUtil {
    public static void showNotifi(int id, String title, String content, Context context) {
        NotificationManager localNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intentOpenNotifi = new Intent(context, MainActivity.class);
        intentOpenNotifi.putExtra("showNotification", true);
        PendingIntent localPendingIntent = PendingIntent.getActivity(context, 0, intentOpenNotifi, PendingIntent.FLAG_UPDATE_CURRENT);
//        RemoteViews remoteViews = new RemoteViews(ShopPushApplication.get().getPackageName(),
//                R.layout.layout_warning);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_app)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_app))
                .setContentTitle(title)
                .setAutoCancel(true)
                .setLights(Color.RED, 3000, 3000)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{100, 100})
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setContentText(content)
//                .setCustomBigContentView(remoteViews)
//                .setContent(remoteViews)
                ;
        mBuilder.setContentIntent(localPendingIntent);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("showNotification", true);
        // The stack builder object will contain an artificial back stack for
        // the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
//        remoteViews.setString(R.id.messageTv, "setText", content);
//        remoteViews.setTextViewText(R.id.messageTv, content);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());
    }
}
