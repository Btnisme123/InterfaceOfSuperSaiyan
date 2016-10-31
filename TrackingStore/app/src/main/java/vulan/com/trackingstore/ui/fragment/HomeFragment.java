package vulan.com.trackingstore.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Food;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.dialog.HomeDialog;

import static android.R.attr.height;
import static android.R.attr.width;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public final static String TAG_HOME_FRAGMENT = "home fragment";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private Button mButtonIcon;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreateContentView(View rootView) {
        findView(rootView);
    }

    @Override
    protected boolean enableBackButton() {
        return false;
    }

    private void findView(View view) {
        mButtonIcon = (Button) view.findViewById(R.id.button_icon);

        mButtonIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_icon:
                Food food = FakeContainer.getFood();
                DrawerRightItem item = FakeContainer.getRightItem();
                Dialog homeDialog = new HomeDialog(getActivity(), food, item);
                homeDialog.show();
                break;
        }
    }
}
