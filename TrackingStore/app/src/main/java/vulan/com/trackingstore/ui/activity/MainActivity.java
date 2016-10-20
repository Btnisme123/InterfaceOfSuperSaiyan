package vulan.com.trackingstore.ui.activity;


import vulan.com.trackingstore.ui.base.BaseActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseFragment getFragment() {
        return new HomeFragment();
    }

    @Override
    protected void onCreateContentView() {
    }
}
