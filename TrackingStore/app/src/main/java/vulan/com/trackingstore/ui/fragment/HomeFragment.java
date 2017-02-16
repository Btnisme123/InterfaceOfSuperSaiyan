package vulan.com.trackingstore.ui.fragment;

import android.view.View;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;


public class HomeFragment extends BaseFragment {
    @Override
    protected void onCreateContentView(final View rootView) {
        findView(rootView);
    }


    private void findView(View rootView) {

    }

    private void init() {
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home_main;
    }


}
