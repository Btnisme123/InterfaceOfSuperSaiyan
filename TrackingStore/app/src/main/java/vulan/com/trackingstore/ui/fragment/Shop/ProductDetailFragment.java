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
import vulan.com.trackingstore.adapter.RecyclerProductDetailAdapter;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.DialogUtil;

/**
 * Created by Ominext on 4/18/2017.
 */

public class ProductDetailFragment extends BaseFragment {
    private Product product;
    private Shop mShop;
    private TextView mTextDescript, mTextPrice, mTextNameProduct, mTextNameShop, mTextCompare;
    private ImageView mImageProductSmall, mImageProduct, mImageShop;
    private RecyclerView mRecyclerProduct;
    private List<Product> mProductList;
    private RecyclerProductDetailAdapter mAdapter;

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
        mTextDescript = (TextView) rootView.findViewById(R.id.tv_descipt_detail);
        mTextNameProduct = (TextView) rootView.findViewById(R.id.tv_product_name_dt);
        mTextNameShop = (TextView) rootView.findViewById(R.id.tv_name_shop_dt);
        mTextPrice = (TextView) rootView.findViewById(R.id.tv_price_dt);
        mImageProductSmall = (ImageView) rootView.findViewById(R.id.img_product_small_dt);
        mImageProduct = (ImageView) rootView.findViewById(R.id.img_product_dt);
        mImageShop = (ImageView) rootView.findViewById(R.id.img_shop_dt);
        mRecyclerProduct = (RecyclerView) rootView.findViewById(R.id.recycler_product_dt);
        mTextCompare = (TextView) rootView.findViewById(R.id.tv_compare);
    }

    private void init() {
        product = (Product) getArguments().getSerializable(Constants.ShopInfo.PRODUCT);
        mShop = (Shop) getArguments().getSerializable(Constants.ShopInfo.SHOP_MODEL);

        mTextDescript.setText(product.getmDescipt() + "");
        mTextPrice.setText(product.getmPrice() + " VND");
        mTextNameShop.setText(mShop.getmShopName());
        mTextNameProduct.setText(product.getmName());
        Glide.with(getActivity()).load(product.getmImageProduct()).fitCenter().into(mImageProduct);
        Glide.with(getActivity()).load(product.getmImageProduct()).fitCenter().into(mImageProductSmall);
        Glide.with(getActivity()).load(mShop.getmUrlLogo()).fitCenter().into(mImageShop);

        ApiRequest.getInstance().init();
        ApiRequest.getInstance().getListProductByName(product.getmName() + "", new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                final List<Product> productList = response.body();
                if (productList != null) {
                    mTextCompare.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogUtil dialogUtil = new DialogUtil(getActivity(), productList, mShop);
                            dialogUtil.show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


        ApiRequest.getInstance().getListProduct(product.getmCategoryId(), new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mProductList = response.body();
                if (mProductList != null) {
                    mRecyclerProduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    mAdapter = new RecyclerProductDetailAdapter(getActivity(), mProductList, mShop);
                    mRecyclerProduct.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Product Detail", t.getMessage());
            }
        });
    }
}
