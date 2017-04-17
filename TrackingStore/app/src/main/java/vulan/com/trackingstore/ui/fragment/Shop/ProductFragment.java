package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerProductAdapter;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by Thanh on 2/21/2017.
 */

public class ProductFragment extends BaseFragment {
    private RecyclerView recyclerProduct;
    private List<Product> products;
    private RecyclerProductAdapter adapter;
    private TextView mTextCategory;
    private int mCategoryId;
    private String mCategoryName;

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
        mTextCategory = (TextView) rootView.findViewById(R.id.tv_category_product);
    }

    private void init() {
        mCategoryId = getArguments().getInt(Constants.ShopInfo.CATEGORY_ID);
        mCategoryName = getArguments().getString(Constants.ShopInfo.CATEGORY_NAME);
        mTextCategory.setText(mCategoryName);
        ApiRequest.getInstance().init();
        ApiRequest.getInstance().getListProduct(mCategoryId, new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                recyclerProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                adapter = new RecyclerProductAdapter(getActivity(), null, products, Constants.RecyclerViewType.PRODUCT_TYPE);
                recyclerProduct.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


    }
}
