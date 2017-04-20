package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerProductAdapter;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.Shop;
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
    private TextView mTextCategory, mTextShopName;
    private ImageView mImageShop;
    private int mCategoryId;
    private String mCategoryName;
    private Shop mShop;

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
        mTextShopName = (TextView) rootView.findViewById(R.id.tv_name_shop_pr);
        mImageShop = (ImageView) rootView.findViewById(R.id.img_shop_product);
    }

    private void init() {
        mCategoryId = getArguments().getInt(Constants.ShopInfo.CATEGORY_ID);
        mCategoryName = getArguments().getString(Constants.ShopInfo.CATEGORY_NAME);
        mShop = (Shop) getArguments().getSerializable(Constants.ShopInfo.SHOP_MODEL);

        mTextCategory.setText(mCategoryName);
        ApiRequest.getInstance().init();
        ApiRequest.getInstance().getListProduct(mCategoryId, new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                recyclerProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                adapter = new RecyclerProductAdapter(getActivity(), null, products, Constants.RecyclerViewType.PRODUCT_TYPE, mShop);
                recyclerProduct.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        mTextShopName.setText(mShop.getmShopName());
        Glide.with(getActivity()).load(mShop.getmUrlLogo()).fitCenter().into(mImageShop);

    }
}
