package vulan.com.trackingstore.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.Shop.CategoryFragment;
import vulan.com.trackingstore.ui.fragment.Shop.PromotionFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ShopFragment;

/**
 * Created by Thanh on 2/16/2017.
 */

public class ShopPagerAdapter extends android.support.v13.app.FragmentPagerAdapter {
    private Context mContext;
    private int PAGE_COUNT = 3;
    private Activity mActivity;
    private Shop shop;

    public ShopPagerAdapter(android.app.FragmentManager fm, Activity context, Shop shop) {
        super(fm);
        mContext = context;
        mActivity = context;
        this.shop = shop;
    }

    @Override
    public android.app.Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ShopFragment();
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            case 2:
                fragment = new PromotionFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = mContext.getResources().getString(R.string.shop_title);
                break;
            case 1:
                title = mContext.getResources().getString(R.string.category_title);
                break;
            case 2:
                title = mContext.getResources().getString(R.string.promotion_title);
                break;
        }
        return title;
    }

    public void replaceFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = mActivity.getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_left_enter, R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack("").commit();
    }
}
