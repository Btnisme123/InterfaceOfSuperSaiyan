package vulan.com.trackingstore.ui.activity;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerLeftDrawerAdapter;
import vulan.com.trackingstore.adapter.RecyclerRightAdapter;
import vulan.com.trackingstore.data.model.DrawerLeftItem;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.HomeFragment;
import vulan.com.trackingstore.ui.fragment.RestaurantFragment;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.widget.LinearItemDecoration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mLayoutSlideUp;
    private RecyclerView mLeftRecyclerDrawer, mRightRecyclerDrawer;
    private RecyclerLeftDrawerAdapter mRecyclerLeftDrawerAdapter;
    private RecyclerRightAdapter mRecyclerRightDrawerAdapter;
    private List<DrawerLeftItem> mDrawerLeftItemList;
    private List<DrawerRightItem> mDrawerRightItemList;
    private ImageView mButtonMenuLeft, mButtonMenuRight;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mContainerLayout;

    private static final int HOME = 0;
    private static final int RESTAURANT = 1;
    private static final int SEARCH_DETAILS = 2;
    private static final int SEARCH = 3;
    private static final int SETTINGS = 4;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
        replaceFragment(new HomeFragment(), "home fragment");
    }

    protected BaseFragment getFragment() {
        return new RestaurantFragment();
    }

    private void findView() {
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(mToolbar);
        // mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        //mLayoutSlideUp= (LinearLayout) findViewById(R.id.layout_slide_up);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftRecyclerDrawer = (RecyclerView) findViewById(R.id.left_recycler_navigation_drawer);
        mRightRecyclerDrawer = (RecyclerView) findViewById(R.id.right_recycler_navigation_drawer);
        mButtonMenuLeft = (ImageView) findViewById(R.id.button_menu_left);
        mButtonMenuRight = (ImageView) findViewById(R.id.button_menu_right);
        mContainerLayout = (FrameLayout) findViewById(R.id.fragment_container);
        mButtonMenuLeft.setOnClickListener(this);
        mButtonMenuRight.setOnClickListener(this);
    }

    public void init() {
        mDrawerLeftItemList = new ArrayList<>();
        mDrawerRightItemList = new ArrayList<>();
        mDrawerRightItemList = FakeContainer.getRightItems();
        mDrawerLeftItemList.add(new DrawerLeftItem(getString(R.string.home_page), R.drawable.ic_home));
        mDrawerLeftItemList.add(new DrawerLeftItem(getString(R.string.restaurant_list), R.drawable.ic_list));
        mDrawerLeftItemList.add(new DrawerLeftItem(getString(R.string.search_detail), R.drawable.ic_search_detail));
        mDrawerLeftItemList.add(new DrawerLeftItem(getString(R.string.search), R.drawable.ic_search));
        mDrawerLeftItemList.add(new DrawerLeftItem(getString(R.string.setting), R.drawable.ic_setting));
        mRecyclerLeftDrawerAdapter = new RecyclerLeftDrawerAdapter(this, mDrawerLeftItemList);
        mLeftRecyclerDrawer.setLayoutManager(new LinearLayoutManager(this));
        mLeftRecyclerDrawer.addItemDecoration(new LinearItemDecoration(this));
        mLeftRecyclerDrawer.setAdapter(mRecyclerLeftDrawerAdapter);
        mRecyclerRightDrawerAdapter = new RecyclerRightAdapter(this, mDrawerRightItemList);
        mRightRecyclerDrawer.setLayoutManager(new LinearLayoutManager(this));
        mRightRecyclerDrawer.addItemDecoration(new LinearItemDecoration(this));
        mRightRecyclerDrawer.setAdapter(mRecyclerRightDrawerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_menu_left:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.button_menu_right:
                mDrawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.button_icon:

                break;
        }
    }

    public void addFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, getFragment()).commit();
    }

    public void replaceFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_left_enter, R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack("").commit();
    }

    public void popFragment() {
        getFragmentManager().popBackStack();
    }

    protected BaseFragment getCurrentFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            return (BaseFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        }
        return null;
    }
}
