package vulan.com.trackingstore.util.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import vulan.com.trackingstore.R;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;

    public GridItemDecoration(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mContext.getResources().getDimensionPixelOffset(R.dimen.common_size_7);
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.left = mContext.getResources().getDimensionPixelOffset(R.dimen.common_size_2);
            outRect.right = 6;
        } else {
            outRect.right = 0;
            outRect.left = mContext.getResources().getDimensionPixelOffset(R.dimen.common_size_2);
        }
    }
}
