package vulan.com.trackingstore.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnRecyclerItemClickListener;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ProductFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.CustomDialog;

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
    private int[] color = {R.color.colorCategory1, R.color.colorCategory2,
            R.color.colorCategory3, R.color.colorCategory4,
            R.color.colorCategory5, R.color.colorCategory6};

    public RecyclerProductAdapter(Activity context, List<ProductCategory> mCategoryList, List<Product> mProductList, int type) {
        this.mCategoryList = mCategoryList;
        this.mProductList = mProductList;
        mContext = context;
        mActivity = context;
        this.type = type;
    }

    public void setOnItemClick(OnRecyclerItemClickListener onItemClick) {
        this.mOnRecyclerItemClickListener = onItemClick;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_category, parent, false);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        }
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
            ProductCategory product = mCategoryList.get(position);
            holder.categoryName.setText(product.getmNameCategory());
            holder.imageCategory.setImageResource(product.getmImageCategory());
            if (position < color.length) {
                holder.layoutCategory.setBackgroundColor(mContext.getResources().getColor(color[position]));
            } else {
                int colorPosition = position;
                while (colorPosition >= color.length) {
                    colorPosition = colorPosition - color.length;
                }
                if (colorPosition == 5) {
                    holder.layoutCategory.setBackgroundColor(mContext.getResources().getColor(color[0]));
                } else {
                    holder.layoutCategory.setBackgroundColor(mContext.getResources().getColor(color[colorPosition + 1]));
                }
            }
            holder.layoutCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new ProductFragment(), Constants.FragmentTag.PRODUCT);
                }
            });
        } else {
            final Product product = mProductList.get(position);
            holder.productName.setText(product.getmName());
            holder.imageProduct.setImageResource(product.getmImageProduct());
            holder.price.setText(product.getmPrice() + " VND");
            holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mOnRecyclerItemClickListener.onRecyclerItemClick(position);
                    CustomDialog customDialog = new CustomDialog(mContext,product,mProductList);
                    customDialog.show();
                }
            });
            if (type == Constants.RecyclerViewType.PROMOTION_TYPE) {
                holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                int newPrice = Integer.parseInt(product.getmPrice()) * (100 - Integer.parseInt(product.getmPromotion())) / 100;
                holder.pricePromotion.setText(newPrice + " VND");
            } else {
                holder.pricePromotion.setVisibility(View.GONE);
            }
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

    public void addItem(ProductCategory food) {
        mCategoryList.add(food);
        notifyItemInserted(mCategoryList.size() - 1);
    }

    public void addItems(List<ProductCategory> foodArrayList) {
        mCategoryList.addAll(foodArrayList);
        notifyDataSetChanged();
    }

    public ProductCategory getItem(int position) {
        return mCategoryList.get(position);
    }

    public List<ProductCategory> getNewsList() {
        return mCategoryList;
    }

    public void removeAllItemsIfExist() {
        if (mCategoryList != null) {
            mCategoryList.clear();
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        mCategoryList.remove(position);
        this.notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        //category
        private TextView categoryName;
        private ImageView imageCategory;
        private RelativeLayout layoutCategory;
        private LinearLayout layoutProduct;
        //product
        private ImageView imageProduct;
        private TextView productName, price, pricePromotion;

        public ItemHolder(View itemView) {
            super(itemView);
            if (type == Constants.RecyclerViewType.CATEGORY_TYPE) {
                categoryName = (TextView) itemView.findViewById(R.id.tv_category);
                imageCategory = (ImageView) itemView.findViewById(R.id.img_category);
                layoutCategory = (RelativeLayout) itemView.findViewById(R.id.layout_category);
            } else {
                imageProduct = (ImageView) itemView.findViewById(R.id.img_product);
                productName = (TextView) itemView.findViewById(R.id.tv_name_product);
                price = (TextView) itemView.findViewById(R.id.tv_price);
                pricePromotion = (TextView) itemView.findViewById(R.id.tv_promotion);
                layoutProduct = (LinearLayout) itemView.findViewById(R.id.layout_product);
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