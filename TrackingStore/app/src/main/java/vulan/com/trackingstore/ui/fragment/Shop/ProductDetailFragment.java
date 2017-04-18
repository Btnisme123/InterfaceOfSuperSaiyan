package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.view.View;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by Ominext on 4/18/2017.
 */

public class ProductDetailFragment extends BaseFragment {
    private Product product;

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_product_details;
    }

    private void findViews(View rootView) {

    }

    private void init() {
        product = (Product) getArguments().getSerializable(Constants.ShopInfo.PRODUCT);
    }
}
