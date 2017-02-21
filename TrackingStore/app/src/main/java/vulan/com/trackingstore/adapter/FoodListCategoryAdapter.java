package vulan.com.trackingstore.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnFoodListClickListener;
import vulan.com.trackingstore.data.model.ProductCategory;

/**
 * Created by vulan on 08/11/2016.
 */

public class FoodListCategoryAdapter extends RecyclerView.Adapter<FoodListCategoryAdapter.ItemHolder>{

    private ArrayList<ProductCategory> foodList=new ArrayList<>();
    private OnFoodListClickListener mOnFoodListClickListener;

    public FoodListCategoryAdapter(ArrayList<ProductCategory> foodList) {
        this.foodList = foodList;
    }

    @Override
    public FoodListCategoryAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_food,parent,false);
        ItemHolder itemHolder=new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(FoodListCategoryAdapter.ItemHolder holder, int position) {
        holder.imageBanner.setImageResource(R.drawable.ic_chicken);
        holder.imageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnFoodListClickListener!=null){
                    mOnFoodListClickListener.onItemFoodClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setOnFoodListClickListener(OnFoodListClickListener onFoodListClickListener) {
        this.mOnFoodListClickListener = onFoodListClickListener;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageBanner;
        public ItemHolder(View itemView) {
            super(itemView);
            imageBanner= (ImageView) itemView.findViewById(R.id.image_category);
        }
    }
}
