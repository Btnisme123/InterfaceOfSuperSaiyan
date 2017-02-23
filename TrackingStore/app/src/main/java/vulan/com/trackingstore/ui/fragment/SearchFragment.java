package vulan.com.trackingstore.ui.fragment;

import android.os.Bundle;
import android.view.View;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;

/**
 * Created by Thanh on 10/27/2016.
 */

public class SearchFragment extends BaseFragment {

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_search;
    }

    private void findViews(View view) {

    }
}
