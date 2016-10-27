package vulan.com.trackingstore.util.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import vulan.com.trackingstore.R;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public LinearItemDecoration(Context context) {
        mSpace = context.getResources().getDimensionPixelSize(R.dimen.common_size_7);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (position == 0 && outRect.top == 0) {
            outRect.top = mSpace;
        }
        outRect.bottom = mSpace;
        outRect.right = mSpace;
        outRect.left = mSpace;
    }
}
