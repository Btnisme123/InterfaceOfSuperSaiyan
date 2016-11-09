package vulan.com.trackingstore.util.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by VULAN on 10/31/2016.
 */

public class CircleCustomView extends SurfaceView implements SurfaceHolder.Callback {
    private int mScreenWidth;
    private int mScreenHeight;
    private int mCircleX;
    private int mCircleY;
    private Bitmap mBitmapCircle;

     private long mTimeNow;
    private int mFrameCount=0;
    private int mFrameCountAvg=0;
    private long framesTimer=0;
    Paint mFpsPaint=new Paint();

    public CircleCustomView(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
