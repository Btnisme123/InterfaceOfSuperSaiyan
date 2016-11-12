package vulan.com.trackingstore.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnFoodListClickListener;
import vulan.com.trackingstore.data.model.CategoryList;
import vulan.com.trackingstore.data.model.ImageBanner;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.FoodFragment;
import vulan.com.trackingstore.util.widget.LinearItemDecoration;


public class FoodCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnFoodListClickListener {
    private final int TYPE_BANNER_FIRST = 2;
    private final int TYPE_BANNER_SECOND= 4;
    private final int TYPE_BANNER_THIRD= 6;
    private final int TYPE_CATEGORY_FOOD = 1;
    private final int TYPE_CATEGORY_DRINK = 3;
    private final int TYPE_CATEGORY_OTHER = 5;
    private List<Object> mItems;
    private Context mContext;
    private Activity mActivity;

    public FoodCategoryAdapter(List<Object> items, Context context) {
        mItems = items;
        mContext = context;
        mActivity= (Activity) context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        switch (viewType){
            case TYPE_BANNER_FIRST:
            case TYPE_BANNER_SECOND:
            case TYPE_BANNER_THIRD:
                view=inflater.inflate(R.layout.item_category_banner,parent,false);
                viewHolder=new BannerHolder(view);
                break;
            case TYPE_CATEGORY_FOOD:
            case TYPE_CATEGORY_DRINK:
            case TYPE_CATEGORY_OTHER:
                view=inflater.inflate(R.layout.item_list_category_food,parent,false);
                viewHolder=new ListItemHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case TYPE_BANNER_FIRST:
            case TYPE_BANNER_SECOND:
            case TYPE_BANNER_THIRD:
                BannerHolder viewHolder = (BannerHolder) holder;
                configureBannerViewHolder(viewHolder, position);
                break;
            case TYPE_CATEGORY_FOOD:
            case TYPE_CATEGORY_DRINK:
            case TYPE_CATEGORY_OTHER:
                ListItemHolder viewListHolder = (ListItemHolder) holder;
                configureRecyclerViewHolder(viewListHolder, position);
                break;
        }
    }

    public void configureBannerViewHolder(BannerHolder holder,int position){
        ImageBanner imageBanner= (ImageBanner) mItems.get(position);
        if(imageBanner!=null){
            holder.imageBanner.setImageResource(imageBanner.getImageBanner());
            holder.textBanner.setText(imageBanner.getTextTitle());
        }
    }

    public void configureRecyclerViewHolder(ListItemHolder holder,int position){
        CategoryList foods= (CategoryList) mItems.get(position);
        if(foods!=null){
          FoodListCategoryAdapter adapter=new FoodListCategoryAdapter((ArrayList)foods.getProductList());
            adapter.setOnFoodListClickListener(this);
            LinearItemDecoration linearItemDecoration=new LinearItemDecoration(mContext);
            linearItemDecoration.setIsHorizontal(true);
            holder.recyclerView.addItemDecoration(linearItemDecoration);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            holder.recyclerView.setAdapter(adapter);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof ImageBanner) {
            switch (position) {
                case TYPE_BANNER_FIRST:
                    return TYPE_BANNER_FIRST;
                case TYPE_BANNER_SECOND:
                    return TYPE_BANNER_SECOND;
                default:
                    return TYPE_BANNER_THIRD;
            }
        } else {
            switch (position) {
                case TYPE_CATEGORY_DRINK:
                    return TYPE_CATEGORY_DRINK;
                case TYPE_CATEGORY_FOOD:
                    return TYPE_CATEGORY_FOOD;
                default:
                    return TYPE_CATEGORY_DRINK;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onItemFoodClick() {
       replaceFragment(new FoodFragment(),FoodFragment.FOOD_FRAGMENT_TAG);
    }

    public void replaceFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_left_enter, R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack("").commit();
    }

    public class BannerHolder extends RecyclerView.ViewHolder{
        public ImageView imageBanner;
        public TextView textBanner;
        public BannerHolder(View itemView) {
            super(itemView);
            imageBanner= (ImageView) itemView.findViewById(R.id.image_category_banner);
            textBanner= (TextView) itemView.findViewById(R.id.text_category_banner);
        }
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{

        public RecyclerView recyclerView;
        public ListItemHolder(View itemView) {
            super(itemView);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.recycler_food_category);
        }
    }
}
