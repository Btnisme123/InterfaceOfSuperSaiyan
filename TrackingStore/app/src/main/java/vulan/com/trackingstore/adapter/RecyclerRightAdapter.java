package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnRecyclerItemClickListener;
import vulan.com.trackingstore.data.model.DrawerRightItem;

/**
 * Created by VULAN on 10/21/2016.
 */

public class RecyclerRightAdapter extends RecyclerView.Adapter<RecyclerRightAdapter.ItemHolder> {
    private List<DrawerRightItem> mNavigationDrawerLeftItems;
    private Context mContext;
    private OnRecyclerItemClickListener mOnRecyclerItemInteractListener;

    public RecyclerRightAdapter(Context context, List<DrawerRightItem> items
    ) {
        mContext = context;
        mNavigationDrawerLeftItems = items;
    }

    public void setOnClick(OnRecyclerItemClickListener onRecyclerItemInteractListener) {
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
    }

    @Override
    public int getItemCount() {
        return mNavigationDrawerLeftItems != null ? mNavigationDrawerLeftItems.size() : 0;
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