package vulan.com.trackingstore.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Food;

/**
 * Created by vulan on 08/11/2016.
 */

public class FoodListCategoryAdapter extends RecyclerView.Adapter<FoodListCategoryAdapter.ItemHolder>{

    private ArrayList<Food> foodList=new ArrayList<>();;

    public FoodListCategoryAdapter(ArrayList<Food> foodList) {
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
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageBanner;
        public ItemHolder(View itemView) {
            super(itemView);
            imageBanner= (ImageView) itemView.findViewById(R.id.image_category);
        }
    }
}
