package vulan.com.trackingstore.ui.activity;


import vulan.com.trackingstore.ui.base.BaseActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.HomeFragment;
import vulan.com.trackingstore.ui.fragment.RestaurantFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseFragment getFragment() {
        return new RestaurantFragment();
    }

    @Override
    protected void onCreateContentView() {
    }
}
