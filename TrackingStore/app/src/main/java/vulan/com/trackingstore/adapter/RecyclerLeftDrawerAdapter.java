package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.estimote.coresdk.recognition.packets.Beacon;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnLeftItemClickListener;
import vulan.com.trackingstore.data.model.Shop;

/**
 * Created by VULAN on 10/21/2016.
 */

public class RecyclerLeftDrawerAdapter extends RecyclerView.Adapter<RecyclerLeftDrawerAdapter.ItemHolder> {
    private List<Shop> mNavigationDrawerLeftItems;
    private Context mContext;
    private OnLeftItemClickListener mOnRecyclerItemInteractListener;
    private List<Beacon> beaconList = new ArrayList<>();

    public RecyclerLeftDrawerAdapter(Context context, List<Shop> items
    ) {
        mContext = context;
        mNavigationDrawerLeftItems = items;
    }

    public void setOnClick(OnLeftItemClickListener onRecyclerItemInteractListener) {
        mOnRecyclerItemInteractListener = onRecyclerItemInteractListener;
    }

    @Override
    public RecyclerLeftDrawerAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_shop_left_recycler, parent, false));
    }

    public List<Beacon> getBeaconList() {
        return beaconList;
    }

    public void setBeaconList(List<Beacon> beaconList) {
        this.beaconList = beaconList;
    }

    @Override
    public void onBindViewHolder(final RecyclerLeftDrawerAdapter.ItemHolder holder, int position) {
        final Shop item = mNavigationDrawerLeftItems.get(position);
        holder.mImageShop.setImageResource(item.getmImageShop());
        holder.mTextName.setText(item.getmShopName());
        holder.mTextAddress.setText(item.getmAddress());
        holder.mTextDistance.setText(String.format("%f  m", (float) item.getMeter()));
        if (position % 2 != 0) {
            holder.mTextDistance.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.mLayoutShop.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            holder.mTextName.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            holder.mTextAddress.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
        holder.mTextName.setSelected(true);
        holder.mTextAddress.setSelected(true);
        holder.mLayoutShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRecyclerItemInteractListener.onLeftItemClick(
                        beaconList.get(holder.getAdapterPosition()),
                        item.getmShopName(),
                        beaconList.get(holder.getAdapterPosition()).getMacAddress().toString());
            }
        });
    }

    public void setList(List<Shop> shops) {
        mNavigationDrawerLeftItems.clear();
        mNavigationDrawerLeftItems.addAll(shops);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mNavigationDrawerLeftItems != null ? mNavigationDrawerLeftItems.size() : 0;
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
            mTextDistance = (TextView) itemView.findViewById(R.id.tv_distance_menu);
        }
    }
}
