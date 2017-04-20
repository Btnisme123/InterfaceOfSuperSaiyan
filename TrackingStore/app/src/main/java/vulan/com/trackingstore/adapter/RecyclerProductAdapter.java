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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnRecyclerItemClickListener;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ProductDetailFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ProductFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by VULAN on 10/22/2016.
 */

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ItemHolder> {

    private Context mContext;
    private Activity mActivity;
    private List<ProductCategory> mCategoryList;
    private List<Product> mProductList;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;
    private int type;
    private Shop shop;

    public RecyclerProductAdapter(Activity context, List<ProductCategory> mCategoryList, List<Product> mProductList, int type, Shop shop) {
        this.mCategoryList = mCategoryList;
        this.mProductList = mProductList;
        mContext = context;
        mActivity = context;
        this.type = type;
        this.shop = shop;
    }

    public void setOnItemClick(OnRecyclerItemClickListener onItemClick) {
        this.mOnRecyclerItemClickListener = onItemClick;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        }
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
            final ProductCategory category = mCategoryList.get(position);
            holder.categoryName.setText(category.getmNameCategory());
            Glide.with(mContext)
                    .load(category.getmImageCategory())
                    .fitCenter()
                    .into(holder.imageCategory);
            holder.layoutCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductFragment productFragment = new ProductFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.ShopInfo.CATEGORY_ID, mCategoryList.get(position).getId());
                    bundle.putString(Constants.ShopInfo.CATEGORY_NAME, mCategoryList.get(position).getmNameCategory());
                    bundle.putSerializable(Constants.ShopInfo.SHOP_MODEL, shop);
                    productFragment.setArguments(bundle);
                    replaceFragment(productFragment, Constants.FragmentTag.PRODUCT);
                }
            });
        } else if (type == Constants.RecyclerViewType.PRODUCT_TYPE) {
            final Product product = mProductList.get(position);
            holder.mTextProductName.setText(product.getmName());
            Glide.with(mContext)
                    .load(product.getmImageProduct())
                    .fitCenter()
                    .into(holder.imageProduct);
            holder.mTextPrice.setText(product.getmPrice() + " VND");
            holder.mTextPromotion.setVisibility(View.GONE);
            holder.mTextWatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.ShopInfo.PRODUCT, product);
                    bundle.putSerializable(Constants.ShopInfo.SHOP_MODEL, shop);
                    productDetailFragment.setArguments(bundle);
                    replaceFragment(productDetailFragment, Constants.FragmentTag.PRODUCT_DETAIL);
                }
            });
        } else {
            final Product product = mProductList.get(position);
            holder.mTextProductName.setText(product.getmName());
            Glide.with(mContext)
                    .load(product.getmImageProduct())
                    .fitCenter()
                    .into(holder.imageProduct);
            int mPricePromotion = (int) (product.getmPrice() * (100 - product.getmPromotion()) / 100);
            holder.mTextPrice.setText(mPricePromotion + " VND");
            holder.mTextPromotion.setText("-" + product.getmPromotion() + "%");
            holder.mTextPromotion.setVisibility(View.VISIBLE);
            holder.mTextWatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.ShopInfo.PRODUCT, product);
                    bundle.putSerializable(Constants.ShopInfo.SHOP_MODEL, shop);
                    productDetailFragment.setArguments(bundle);
                    replaceFragment(productDetailFragment, Constants.FragmentTag.PRODUCT_DETAIL);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
            return mCategoryList.size();
        } else {
            return mProductList.size();
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        //category
        private TextView categoryName;
        private ImageView imageCategory;
        private RelativeLayout layoutCategory;
        private LinearLayout layoutProduct;
        //product
        private ImageView imageProduct;
        private TextView mTextProductName, mTextPrice, mTextWatch, mTextPromotion;

        public ItemHolder(View itemView) {
            super(itemView);
            if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
                categoryName = (TextView) itemView.findViewById(R.id.tv_category);
                imageCategory = (ImageView) itemView.findViewById(R.id.img_category);
                layoutCategory = (RelativeLayout) itemView.findViewById(R.id.layout_category);
            } else {
                imageProduct = (ImageView) itemView.findViewById(R.id.img_product_promo);
                mTextProductName = (TextView) itemView.findViewById(R.id.tv_product_name);
                mTextPrice = (TextView) itemView.findViewById(R.id.tv_price);
                mTextWatch = (TextView) itemView.findViewById(R.id.tv_watch);
                mTextPromotion = (TextView) itemView.findViewById(R.id.tv_promotion);
            }

        }

        private String processPrice(String price) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
            String newPrice = decimalFormat.format(price);
            return newPrice;
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