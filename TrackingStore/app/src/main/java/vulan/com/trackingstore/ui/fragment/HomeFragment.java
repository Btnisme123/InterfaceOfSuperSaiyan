package vulan.com.trackingstore.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    public final static String TAG_HOME_FRAGMENT = "home fragment";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

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

    }
}
