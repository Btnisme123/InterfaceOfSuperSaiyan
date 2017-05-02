package vulan.com.trackingstore.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerDialogAdapter;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.Shop;

/**
 * Created by Thanh on 5/2/2017.
 */

public class DialogUtil extends Dialog {
    private Context mContext;
    private Activity mActivity;
    private View mView;
    private TextView mTextNoData;
    private RecyclerView mRecyclerDialog;
    private RecyclerDialogAdapter adapter;
    private List<Product> mProductList;
    private Shop mShop;

    public DialogUtil(Activity context, List<Product> productList, Shop shop) {
        super(context);
        this.mContext = context;
        this.mActivity = context;
        mShop = shop;
        mProductList = productList;
        mView = View.inflate(mContext, R.layout.dialog_compare, null);
        findViews(mView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCanceledOnTouchOutside(true);
        setContentView(mView);
        setCancelable(true);

        if (mProductList.size() > 0) {
            mRecyclerDialog.setLayoutManager(new LinearLayoutManager(mContext));
            adapter = new RecyclerDialogAdapter(mActivity, mProductList, mShop);
            adapter.setOnItemClickListener(new RecyclerDialogAdapter.OnItemClick() {
                @Override
                public void onClickItem(boolean isClicked) {
                    if (isClicked) {
                        dismiss();
                    }
                }
            });
            mRecyclerDialog.setAdapter(adapter);
            mRecyclerDialog.setVisibility(View.VISIBLE);
            mTextNoData.setVisibility(View.GONE);
        } else {
            mTextNoData.setVisibility(View.VISIBLE);
            mRecyclerDialog.setVisibility(View.GONE);
        }
    }

    private void findViews(View mView) {
        mRecyclerDialog = (RecyclerView) mView.findViewById(R.id.recycler_dialog);
        mTextNoData = (TextView) mView.findViewById(R.id.tv_nodata);
    }
}
