package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerProductAdapter;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.FakeContainer;

/**
 * Created by Thanh on 2/21/2017.
 */

public class ProductFragment extends BaseFragment {
    private RecyclerView recyclerProduct;
    private List<Product> products;
    RecyclerProductAdapter adapter;

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_product;
    }

    private void findViews(View rootView) {
        recyclerProduct = (RecyclerView) rootView.findViewById(R.id.recycler_product);
    }

    private void init() {
        products = new ArrayList<>();
        products = FakeContainer.getListProduct(1);
        recyclerProduct.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new RecyclerProductAdapter(getActivity(), null, products, Constants.RecyclerViewType.PRODUCT_TYPE);
        recyclerProduct.setAdapter(adapter);


    }
}
