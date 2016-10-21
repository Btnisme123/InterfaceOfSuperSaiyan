package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnRecyclerItemClickListener;
import vulan.com.trackingstore.data.model.DrawerLeftItem;

/**
 * Created by VULAN on 10/21/2016.
 */

public class RecyclerLeftDrawerAdapter extends RecyclerView.Adapter<RecyclerLeftDrawerAdapter.ItemHolder> {
    private List<DrawerLeftItem> mNavigationDrawerLeftItems;
    private Context mContext;
    private OnRecyclerItemClickListener mOnRecyclerItemInteractListener;

    public RecyclerLeftDrawerAdapter(Context context, List<DrawerLeftItem> items
    ) {
        mContext = context;
        mNavigationDrawerLeftItems = items;
    }

    public void setOnClick(OnRecyclerItemClickListener onRecyclerItemInteractListener) {
        mOnRecyclerItemInteractListener = onRecyclerItemInteractListener;
    }

    @Override
    public RecyclerLeftDrawerAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_left_recycler_navigation_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerLeftDrawerAdapter.ItemHolder holder, final int position) {
        DrawerLeftItem item = mNavigationDrawerLeftItems.get(position);
        holder.mImageIcon.setImageResource(item.getImageDrawble());
        holder.mTextTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNavigationDrawerLeftItems != null ? mNavigationDrawerLeftItems.size() : 0;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView mImageIcon;
        public LinearLayout mlinearDrawer;
        public TextView mTextTitle;

        public ItemHolder(View itemView) {
            super(itemView);
            mlinearDrawer = (LinearLayout) itemView.findViewById(R.id.linear_navigation_drawer);
            mImageIcon = (ImageView) itemView.findViewById(R.id.image_icon);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}
