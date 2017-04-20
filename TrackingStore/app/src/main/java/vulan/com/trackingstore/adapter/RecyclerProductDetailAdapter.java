package vulan.com.trackingstore.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ProductDetailFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by Ominext on 4/20/2017.
 */

public class RecyclerProductDetailAdapter extends RecyclerView.Adapter<RecyclerProductDetailAdapter.ItemHolder> {
    private Context mContext;
    private Activity mActivity;
    private List<Product> mProductList;
    private Shop mShop;

    public RecyclerProductDetailAdapter(Activity mContext, List<Product> mProductList, Shop shop) {
        this.mContext = mContext;
        this.mActivity = mContext;
        this.mProductList = mProductList;
        this.mShop = shop;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_product_details, parent, false);
        return new RecyclerProductDetailAdapter.ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        Glide.with(mContext).load(mProductList.get(position).getmImageProduct()).fitCenter().into(holder.mImageProduct);
        holder.mImageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.ShopInfo.PRODUCT, mProductList.get(position));
                bundle.putSerializable(Constants.ShopInfo.SHOP_MODEL, mShop);
                productDetailFragment.setArguments(bundle);
                replaceFragment(productDetailFragment, Constants.FragmentTag.PRODUCT_DETAIL);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView mImageProduct;

        public ItemHolder(View itemView) {
            super(itemView);
            mImageProduct = (ImageView) itemView.findViewById(R.id.img_product_item_dt);
        }
    }

    public void replaceFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_left_enter, R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_container_shop, fragment, tag)
                .addToBackStack("").commit();
    }
}
