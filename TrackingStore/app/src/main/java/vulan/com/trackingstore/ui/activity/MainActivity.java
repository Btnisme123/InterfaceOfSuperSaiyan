package vulan.com.trackingstore.ui.activity;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerLeftDrawerAdapter;
import vulan.com.trackingstore.adapter.RecyclerRightAdapter;
import vulan.com.trackingstore.data.model.DrawerLeftItem;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Food;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.dialog.HomeDialog;
import vulan.com.trackingstore.util.widget.LinearItemDecoration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mLayoutSlideUp;
    private RecyclerView mLeftRecyclerDrawer, mRightRecyclerDrawer;
    private RecyclerLeftDrawerAdapter mRecyclerLeftDrawerAdapter;
    private RecyclerRightAdapter mRecyclerRightDrawerAdapter;
    private List<DrawerLeftItem> mDrawerLeftItemList;
    private List<DrawerRightItem> mDrawerRightItemList;
    private Button mButtonMenuLeft, mButtonMenuRight, mButtonIcon;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
    }

    private void findView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftRecyclerDrawer = (RecyclerView) findViewById(R.id.left_recycler_navigation_drawer);
        mRightRecyclerDrawer = (RecyclerView) findViewById(R.id.right_recycler_navigation_drawer);
        mButtonMenuLeft = (Button) findViewById(R.id.button_menu_left);
        mButtonMenuRight = (Button) findViewById(R.id.button_menu_right);
        mButtonIcon = (Button) findViewById(R.id.button_icon);
        mButtonMenuLeft.setOnClickListener(this);
        mButtonMenuRight.setOnClickListener(this);
        mButtonIcon.setOnClickListener(this);
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
                Food food = FakeContainer.getFood();
                DrawerRightItem item = FakeContainer.getRightItem();
                Dialog homeDialog = new HomeDialog(this, food, item);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(homeDialog.getWindow().getAttributes());
                layoutParams.width = 500;
                layoutParams.height = 500;
                homeDialog.getWindow().setAttributes(layoutParams);
                homeDialog.show();
                break;
        }
    }
}
