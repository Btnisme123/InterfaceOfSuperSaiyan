package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnRecyclerItemClickListener;
import vulan.com.trackingstore.data.model.Food;

/**
 * Created by VULAN on 10/22/2016.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ItemHolder> {

    private Context mContext;
    private ArrayList<Food> mFoodList;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnRecyclerItemClickListener mOnItemClickListener;

    public FoodAdapter(Context context, RecyclerView.LayoutManager layoutManager) {
        mFoodList = new ArrayList<>();
        mLayoutManager = layoutManager;
        mContext = context;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (mLayoutManager instanceof GridLayoutManager) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_food, parent, false);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_food, parent, false);
        }
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        Food food = mFoodList.get(position);
        holder.foodName.setText(food.getName());
        holder.foodPrice.setText(food.getPrice());
        holder.imageFood.setImageResource(food.getImageFood());
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public void addItem(Food food) {
        mFoodList.add(food);
        notifyItemInserted(mFoodList.size() - 1);
    }

    public void addItems(ArrayList<Food> foodArrayList) {
        mFoodList.addAll(foodArrayList);
        notifyDataSetChanged();
    }

    public Food getItem(int position) {
        return mFoodList.get(position);
    }

    public ArrayList<Food> getNewsList() {
        return mFoodList;
    }

    public void removeAllItemsIfExist() {
        if (mFoodList != null) {
            mFoodList.clear();
            notifyDataSetChanged();
        }
    }

    public void changeLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mFoodList.remove(position);
        this.notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        public TextView foodName;
        public ImageView imageFood;
        public TextView foodPrice;
        public LinearLayout linearItemFoods;

        public ItemHolder(View itemView) {
            super(itemView);
            foodName = (TextView) itemView.findViewById(R.id.text_food_name);
            imageFood = (ImageView) itemView.findViewById(R.id.image_food);
            foodPrice = (TextView) itemView.findViewById(R.id.text_food_price);
            linearItemFoods = (LinearLayout) itemView.findViewById(R.id.linear_item_news);
        }
    }
}