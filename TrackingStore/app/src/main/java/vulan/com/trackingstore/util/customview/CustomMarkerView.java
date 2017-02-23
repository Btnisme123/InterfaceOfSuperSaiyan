package vulan.com.trackingstore.util.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.model.LatLng;

import vulan.com.trackingstore.R;

/**
 * Created by vulan on 20/02/2017.
 */

public class CustomMarkerView extends FrameLayout {
    //private final String FONT = "KaushanScriptRegular.otf";

    private LatLng mPosition;
    private int mId;
    private double mNumber;
    private int mType;
    private String mName;
    private RelativeLayout mMarkerBackground;


    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        mAvatar = avatar;
    }

    private int mAvatar;

    public LatLng getPosition() {
        return mPosition;
    }

    public void setPosition(LatLng position) {
        mPosition = position;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public double getNumber() {
        return mNumber;
    }

    public void setNumber(double number) {
        mNumber = number;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public RelativeLayout getMarkerBackground() {
        return mMarkerBackground;
    }

    public void setProperties(LatLng latLng, int id, int type, String name) {
        mPosition = latLng;
        mId = id;
        mType = type;
        mName = name;
    }

    public void setMarkerBackground(RelativeLayout markerBackground) {
        mMarkerBackground = markerBackground;
    }

    public void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mMarkerBackground.setBackground(drawable);
        }
    }

    public void setInvisibleBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mMarkerBackground.setVisibility(INVISIBLE);
        }
    }

    public CustomMarkerView(Context context) {
        super(context);
        init(context);
        setWillNotDraw(false);
    }

    public CustomMarkerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setWillNotDraw(false);
    }

    public void setVisible(boolean check) {
        if (check)
            mMarkerBackground.setVisibility(VISIBLE);
        else
            mMarkerBackground.setVisibility(GONE);
    }

    public void init(Context context) {
        View.inflate(context, R.layout.custom_marker, this);
        findView();
    }


    public void findView() {
        mMarkerBackground = (RelativeLayout) findViewById(R.id.marker_background);
    }
}
