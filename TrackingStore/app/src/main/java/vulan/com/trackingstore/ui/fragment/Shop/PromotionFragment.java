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
 * Created by Thanh on 2/20/2017.
 */

public class PromotionFragment extends BaseFragment {
    private RecyclerView mRecyclerPromotion;
    private TextView mTextShopName;
    private ImageView mImageShop;
    private List<Product> mProductList;
    private RecyclerProductAdapter mAdapter;
    private Shop mShop;

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_promotion;
    }

    private void findViews(View rootView) {
        mRecyclerPromotion = (RecyclerView) rootView.findViewById(R.id.recycler_product_promo);
        mTextShopName = (TextView) rootView.findViewById(R.id.tv_shop_name_promo);
        mImageShop = (ImageView) rootView.findViewById(R.id.img_shop_promo);
    }

    private void init() {
        mShop = (Shop) getArguments().getSerializable(Constants.ShopInfo.SHOP_MODEL);
        mTextShopName.setText(mShop.getmShopName());
        Glide.with(getActivity()).load(mShop.getmUrlLogo()).fitCenter().into(mImageShop);
        ApiRequest.getInstance().init();
        ApiRequest.getInstance().getListPromotion(mShop.getId(), new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mProductList = response.body();
                mAdapter = new RecyclerProductAdapter(getActivity(), null, mProductList, Constants.RecyclerViewType.PROMOTION_TYPE, mShop);
                mRecyclerPromotion.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                mRecyclerPromotion.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
}
