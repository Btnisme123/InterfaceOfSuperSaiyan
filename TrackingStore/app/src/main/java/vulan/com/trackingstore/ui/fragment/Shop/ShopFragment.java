package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.view.View;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;

/**
 * Created by Thanh on 10/21/2016.
 */

public class ShopFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateContentView(View rootView) {
        findViews(rootView);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_info_shop;
    }

    private void findViews(View view) {

    }
}
