package vulan.com.trackingstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.listener.OnTagRemoveListener;
import vulan.com.trackingstore.data.model.TagSearch;

/**
 * Created by Thanh on 2/27/2017.
 */

public class RecyclerTagAdapter extends RecyclerView.Adapter<RecyclerTagAdapter.ItemHolder> {
    private ArrayList<TagSearch> tagSearchArrayList;
    private Context mContext;
    private OnTagRemoveListener onTagRemoveListener;

    public RecyclerTagAdapter(Context mContext, ArrayList<TagSearch> tagSearchArrayList) {
        this.mContext = mContext;
        this.tagSearchArrayList = tagSearchArrayList;
    }

    public void setOnTagRemoveClick(OnTagRemoveListener onTagRemoveListener) {
        this.onTagRemoveListener = onTagRemoveListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_search_tag, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        final TagSearch tagSearch = tagSearchArrayList.get(position);
        holder.mTextTagContent.setText(tagSearch.getTagContent());
        holder.mButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove item
                tagSearchArrayList.remove(position);
                onTagRemoveListener.onTagRemove();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagSearchArrayList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTextTagContent;
        private ImageView mButtonClear;

        public ItemHolder(View itemView) {
            super(itemView);
            mTextTagContent = (TextView) itemView.findViewById(R.id.tv_tag_content);
            mButtonClear = (ImageView) itemView.findViewById(R.id.btn_clear_tag);
        }

    }
}
