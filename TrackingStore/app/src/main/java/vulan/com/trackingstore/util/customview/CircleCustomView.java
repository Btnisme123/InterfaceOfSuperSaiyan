package vulan.com.trackingstore.util.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import vulan.com.trackingstore.R;

/**
 * Created by VULAN on 10/31/2016.
 */

public class CircleCustomView extends View {

    private Paint mPaint = new Paint();
    private int mRadius;
    private int mWidth;
    private int mHeight;
    private Context mContext;
    private View mView;
    private ImageView mImageFirst;
    private Bitmap mBitmap;


    public CircleCustomView(Context context) {
        super(context);
        init(context);
    }

    public CircleCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext=context;
        mBitmap=BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ball);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = 200;
        int alpha = 255;
//        int cx=(mWidth-mBitmap.getWidth())>>1;
//        int cy=(mHeight-mBitmap.getHeight())>>1;
//        canvas.drawBitmap(mBitmap,cx+size,cy+size,mPaint);
        mPaint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
        DashPathEffect[] dashPathEffects = new DashPathEffect[4];
        for (int i = 0; i < 4; i++) {
            dashPathEffects[i] = new DashPathEffect(new float[]{10, 10}, (float) size);
            mPaint.setPathEffect(dashPathEffects[i]);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(3);
            mPaint.setAlpha(alpha);
            canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, size, mPaint);
            size = size + 50;
            alpha = alpha - 60;
        }
        invalidate();
    }

    public int getRadius() {
        return mRadius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
    }
}
