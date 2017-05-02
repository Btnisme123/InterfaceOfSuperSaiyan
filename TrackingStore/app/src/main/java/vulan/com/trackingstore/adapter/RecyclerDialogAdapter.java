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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ProductDetailFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by Thanh on 5/2/2017.
 */

public class RecyclerDialogAdapter extends RecyclerView.Adapter<RecyclerDialogAdapter.ItemHolder> {
    private List<Product> mProductList;
    private Context mContext;
    private Activity mActivity;
    private Shop mShop;
    private OnItemClick onItemClick;

    public interface OnItemClick {
        void onClickItem(boolean isClicked);
    }

    public void setOnItemClickListener(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public RecyclerDialogAdapter(Activity context, List<Product> productList, Shop shop) {
        mContext = context;
        mActivity = context;
        mProductList = productList;
        mShop = shop;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerDialogAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_shop_left_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        Product item = mProductList.get(position);
        Glide.with(mContext).load(item.getmImageProduct()).fitCenter().into(holder.mImageShop);
        holder.mTextName.setText(item.getmName() + "");
        holder.mTextAddress.setText(item.getmPrice() + " VND");
        holder.mTextName.setSelected(true);
        holder.mTextAddress.setSelected(true);
        holder.mLayoutShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.ShopInfo.PRODUCT, mProductList.get(position));
                bundle.putSerializable(Constants.ShopInfo.SHOP_MODEL, mShop);
                productDetailFragment.setArguments(bundle);
                replaceFragment(productDetailFragment, Constants.FragmentTag.PRODUCT_DETAIL);
                if (onItemClick != null) {
                    onItemClick.onClickItem(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView mImageShop;
        private RelativeLayout mLayoutShop;
        private TextView mTextName, mTextAddress, mTextDistance;

        public ItemHolder(View itemView) {
            super(itemView);
            mLayoutShop = (RelativeLayout) itemView.findViewById(R.id.layout_shop_menu);
            mImageShop = (ImageView) itemView.findViewById(R.id.img_shop_menu);
            mTextName = (TextView) itemView.findViewById(R.id.tv_name_menu);
            mTextAddress = (TextView) itemView.findViewById(R.id.tv_address_menu);
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
