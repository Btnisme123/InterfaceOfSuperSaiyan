package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;


public class CategoryFragment extends BaseFragment {
    private RecyclerProductAdapter adapter;
    private RecyclerView mRecycleCategory;
    private ImageView mImageShop;
    private TextView mTextNameShop;
    private List<ProductCategory> productCategories;
    private Shop mShop;

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findView(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_category;
    }

    private void init() {
        mShop = (Shop) getArguments().getSerializable(Constants.ShopInfo.SHOP_MODEL);

        ApiRequest.getInstance().init();
        ApiRequest.getInstance().getCategoryByShopId(mShop.getId(), new Callback<List<ProductCategory>>() {
            @Override
            public void onResponse(Call<List<ProductCategory>> call, Response<List<ProductCategory>> response) {
                productCategories = response.body();
                mRecycleCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                adapter = new RecyclerProductAdapter(getActivity(), productCategories, null, Constants.RecyclerViewType.CATEGORY_TYPE, mShop);
                mRecycleCategory.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ProductCategory>> call, Throwable t) {
                Log.e("category", "load data failed");
            }
        });
        mTextNameShop.setText(mShop.getmShopName() + "");
        Glide.with(getActivity()).load(mShop.getmUrlLogo()).fitCenter().into(mImageShop);

    }

    private void findView(View rootView) {
        mRecycleCategory = (RecyclerView) rootView.findViewById(R.id.recycler_category);
        mTextNameShop = (TextView) rootView.findViewById(R.id.tv_name_shop_cate);
        mImageShop = (ImageView) rootView.findViewById(R.id.img_shop_category);
    }
}

