package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Shop;

/**
 * Created by Thanh on 2/16/2017.
 */

public class ListShopAdapter extends BaseAdapter {
    private List<Shop> shopArrayList = new ArrayList<>();
    private Context mContext;
    LayoutInflater inflater;

    public ListShopAdapter(Context context, List<Shop> shopArrayList) {
        this.shopArrayList = shopArrayList;
        mContext = context;
        inflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return shopArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        Shop shop = shopArrayList.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list_shop, null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(shop.getmUrlLogo())
                .fitCenter()
                .into(holder.mImageShop);
        holder.mTextAddress.setText(shop.getmAddress());
        holder.mTextShopName.setText(shop.getmShopName());
        return convertView;
    }

    private class MyViewHolder {
        private ImageView mImageShop;
        private TextView mTextShopName, mTextAddress;

        public MyViewHolder(View itemView) {
            mImageShop = (ImageView) itemView.findViewById(R.id.img_shop);
            mTextShopName = (TextView) itemView.findViewById(R.id.tv_shop_name);
            mTextAddress = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}
