package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnRightItemCLickListener;
import vulan.com.trackingstore.data.model.DrawerRightItem;

/**
 * Created by VULAN on 10/21/2016.
 */

public class RecyclerRightAdapter extends RecyclerView.Adapter<RecyclerRightAdapter.ItemHolder> {
    private List<DrawerRightItem> mNavigationDrawerLeftItems;
    private Context mContext;
    private OnRightItemCLickListener mOnRecyclerItemInteractListener;

    public RecyclerRightAdapter(Context context, List<DrawerRightItem> items
    ) {
        mContext = context;
        mNavigationDrawerLeftItems = new ArrayList<>(items);
    }

    public void setOnClick(OnRightItemCLickListener onRecyclerItemInteractListener) {
        mOnRecyclerItemInteractListener = onRecyclerItemInteractListener;
    }

    @Override
    public RecyclerRightAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerRightAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_right_recycler_navigation_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerRightAdapter.ItemHolder holder, final int position) {
        DrawerRightItem item = mNavigationDrawerLeftItems.get(position);
        holder.mTextMeter.setText(item.getMeter());
        holder.mTextTitle.setText(item.getTitle());
        holder.mTextTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnRecyclerItemInteractListener!=null){
                    mOnRecyclerItemInteractListener.onRightItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNavigationDrawerLeftItems != null ? mNavigationDrawerLeftItems.size() : 0;
    }

    public void removeItem(int position) {
        mNavigationDrawerLeftItems.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int position, DrawerRightItem model) {
        mNavigationDrawerLeftItems.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        DrawerRightItem category = mNavigationDrawerLeftItems.remove(fromPosition);
        mNavigationDrawerLeftItems.add(toPosition, category);
        notifyItemMoved(fromPosition, toPosition);
    }

    private void applyAndAnimateRemovals(List<DrawerRightItem> categoryProducts) {
        int size = mNavigationDrawerLeftItems.size();
        for (int i = size - 1; i >= 0; i--) {
            DrawerRightItem category = mNavigationDrawerLeftItems.get(i);
            if (!categoryProducts.contains(category)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAddition(List<DrawerRightItem> categoryProducts) {
        for (int i = 0, count = categoryProducts.size(); i < count; i++) {
            DrawerRightItem categoryProduct = categoryProducts.get(i);
            if (!mNavigationDrawerLeftItems.contains(categoryProduct)) {
                addItem(i, categoryProduct);
            }
        }
    }

    private void applyAndAnimateMoveItems(List<DrawerRightItem> categoryProducts) {
        int size = categoryProducts.size();
        for (int toPosition = size - 1; toPosition >= 0; toPosition--) {
            DrawerRightItem category = categoryProducts.get(toPosition);
            int fromPosition = mNavigationDrawerLeftItems.indexOf(category);
            if (fromPosition != 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public void animateTo(List<DrawerRightItem> list) {
        applyAndAnimateAddition(list);
        applyAndAnimateMoveItems(list);
        applyAndAnimateRemovals(list);
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public TextView mTextMeter;
        public LinearLayout mlinearDrawer;
        public TextView mTextTitle;

        public ItemHolder(View itemView) {
            super(itemView);
            mlinearDrawer = (LinearLayout) itemView.findViewById(R.id.linear_navigation_drawer);
            mTextMeter = (TextView) itemView.findViewById(R.id.text_meter);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}