package vulan.com.trackingstore.ui.activity;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.HomeFragment;
import vulan.com.trackingstore.ui.fragment.ListShopFragment;
import vulan.com.trackingstore.ui.fragment.SearchFragment;
import vulan.com.trackingstore.ui.fragment.SettingsFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ShopFragment;
import vulan.com.trackingstore.util.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mButtonHome, mButtonListShop, mButtonSearch, mButtonSettings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
        replaceFragment(new HomeFragment(), Constants.FragmentTag.HOME);
        updateIconMenu(Constants.Menu.MENU_HOME);
    }

    protected BaseFragment getFragment() {
        return new ShopFragment();
    }

    private void findView() {
        mButtonHome = (ImageView) findViewById(R.id.btn_home);
        mButtonListShop = (ImageView) findViewById(R.id.btn_list_shop);
        mButtonSearch = (ImageView) findViewById(R.id.btn_search);
        mButtonSettings = (ImageView) findViewById(R.id.btn_settings);
    }

    public void init() {
        mButtonHome.setOnClickListener(this);
        mButtonListShop.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        mButtonSettings.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                replaceFragment(new HomeFragment(), Constants.FragmentTag.HOME);
                updateIconMenu(Constants.Menu.MENU_HOME);
                break;
            case R.id.btn_list_shop:
                replaceFragment(new ListShopFragment(), Constants.FragmentTag.LIST);
                updateIconMenu(Constants.Menu.MENU_LIST_SHOP);
                break;
            case R.id.btn_search:
                replaceFragment(new SearchFragment(), Constants.FragmentTag.SEARCH);
                updateIconMenu(Constants.Menu.MENU_SEARCH);
                break;
            case R.id.btn_settings:
                replaceFragment(new SettingsFragment(), Constants.FragmentTag.SETTINGS);
                updateIconMenu(Constants.Menu.MENU_SETTING);
                break;
        }
    }

    private void updateIconMenu(int code) {
        switch (code) {
            case Constants.Menu.MENU_HOME:
                mButtonHome.setImageResource(R.drawable.ic_home);
                mButtonListShop.setImageResource(R.drawable.ic_list_transparent);
                mButtonSearch.setImageResource(R.drawable.ic_search_transparent);
                mButtonSettings.setImageResource(R.drawable.ic_setting_transparent);
                break;
            case Constants.Menu.MENU_LIST_SHOP:
                mButtonHome.setImageResource(R.drawable.ic_home_transparent);
                mButtonListShop.setImageResource(R.drawable.ic_list_white);
                mButtonSearch.setImageResource(R.drawable.ic_search_transparent);
                mButtonSettings.setImageResource(R.drawable.ic_setting_transparent);
                break;
            case Constants.Menu.MENU_SEARCH:
                mButtonHome.setImageResource(R.drawable.ic_home_transparent);
                mButtonListShop.setImageResource(R.drawable.ic_list_transparent);
                mButtonSearch.setImageResource(R.drawable.ic_search);
                mButtonSettings.setImageResource(R.drawable.ic_setting_transparent);
                break;
            case Constants.Menu.MENU_SETTING:
                mButtonHome.setImageResource(R.drawable.ic_home_transparent);
                mButtonListShop.setImageResource(R.drawable.ic_list_transparent);
                mButtonSearch.setImageResource(R.drawable.ic_search_transparent);
                mButtonSettings.setImageResource(R.drawable.ic_setting);
                break;
        }
    }
}
