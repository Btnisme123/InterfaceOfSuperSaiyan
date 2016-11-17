package vulan.com.trackingstore.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Food;
import vulan.com.trackingstore.data.model.Restaurant;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.customview.CircleCustomView;
import vulan.com.trackingstore.util.dialog.HomeDialog;


public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private List<CircleImageView> mListImage;
    private CircleCustomView mCircleCustomView;
    private RelativeLayout mLayout;
    private List<Restaurant> mListRestaurant;
    private float mHeight;
    private float mWidth;
    private boolean mIsFirst = true;

    @Override
    protected void onCreateContentView(final View rootView) {
        findView(rootView);
        mLayout.addView(mCircleCustomView);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mIsFirst) {
                    mIsFirst = false;
                    mHeight = rootView.getHeight();
                    mWidth = rootView.getWidth();
                    init();
                }
            }
        });

    }

    private void init() {
        mListImage = new ArrayList<>();
        mListRestaurant = new ArrayList<>();
        mListRestaurant = FakeContainer.getListRestaurant(5);
        switch (mListRestaurant.size()) {
            case 5:
                for (int i = 0; i < 5; i++) {
                    CircleImageView circleImageView = new CircleImageView(getActivity());
                    ImageView imageView = new ImageView(getActivity());
                    RippleBackground background=new RippleBackground(getActivity());
                    circleImageView.setImageResource(mListRestaurant.get(i).getImageBanner());
                    circleImageView.setBackgroundColor(Color.TRANSPARENT);
                    imageView.setBackgroundResource(R.drawable.alpha);
                    mLayout.addView(circleImageView);
                    mLayout.addView(imageView);
                    circleImageView.getLayoutParams().height = 100;
                    circleImageView.getLayoutParams().width = 100;
                    imageView.getLayoutParams().width = 150;
                    imageView.getLayoutParams().height = 150;
                    AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
                    float heightIcon = circleImageView.getLayoutParams().height;
                    float heightCircle = imageView.getLayoutParams().height;
                    circleImageView.setOnClickListener(this);
                    switch (i) {
                        case 0:
                            setDimension(circleImageView, imageView, 1 / 2.0, 1 / 5.0, heightIcon, heightCircle);
                            break;
                        case 1:
                            setDimension(circleImageView, imageView, 9 / 10.0, 2 / 5.0, heightIcon, heightCircle);
                            break;
                        case 2:
                            setDimension(circleImageView, imageView, 8 / 10.0, 4 / 6.0, heightIcon, heightCircle);
                            break;
                        case 3:
                           setDimension(circleImageView, imageView, 2 / 10.0, 4 / 6.0, heightIcon, heightCircle);
                            break;
                        case 4:
                           setDimension(circleImageView, imageView, 1 / 10.0, 2 / 5.0, heightIcon, heightCircle);
                            break;
                    }
                    animationDrawable.start();
                   // background.startRippleAnimation();
                }
                break;
        }
        mLayout.invalidate();
    }

    private void setDimension(View icon, View imageView, double subX, double subY, float heightIcon, float heightImage) {
        icon.setX((float) subX * mWidth - heightIcon / 2);
        icon.setY((float) subY * mHeight - heightIcon / 2);
        imageView.setX((float) subX * mWidth - heightImage / 2);
        imageView.setY((float) subY * mHeight - heightImage / 2);
    }

    private void findView(View rootView) {
        mLayout = (RelativeLayout) rootView.findViewById(R.id.layout_home);
        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getActivity(), " " + motionEvent.getX() + ": " + motionEvent.getY(), Toast.LENGTH_SHORT).show();
                Log.e("" + mWidth, ": " + mHeight);
                return false;
            }
        });
        mCircleCustomView = new CircleCustomView(getActivity());
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    public void onClick(View view) {
        Food food = FakeContainer.getFood();
        DrawerRightItem item = FakeContainer.getRightItem();
        Dialog homeDialog = new HomeDialog(getActivity(), food, item);
        homeDialog.show();
    }
}
