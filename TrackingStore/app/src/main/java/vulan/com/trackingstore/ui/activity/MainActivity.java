package vulan.com.trackingstore.ui.activity;


import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerLeftDrawerAdapter;
import vulan.com.trackingstore.data.model.BeaconWithDistance;
import vulan.com.trackingstore.data.model.BeaconWithShop;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.service.LocationUpdatesService;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.ui.fragment.HomeFragment;
import vulan.com.trackingstore.ui.fragment.ListShopFragment;
import vulan.com.trackingstore.ui.fragment.SearchFragment;
import vulan.com.trackingstore.ui.fragment.SettingsFragment;
import vulan.com.trackingstore.ui.fragment.Shop.ShopFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.NotificationUtil;
import vulan.com.trackingstore.util.SortUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String ACTION_BEACON_CHANGE = "vulan.com.trackingstore.ACTION_BEACON_CHANGE";
    private RequestBeaconReceiver requestBeaconReceiver;
    private int mLastSize = 0;
    private int mCurrentSize = 0;
    private String mMacIds = "";
    private Bundle bundle;
    private SortUtil sortUtil;

    private ImageView mButtonHome, mButtonListShop, mButtonSearch, mButtonSettings;
    private RecyclerView recyclerShopLeft;
    private List<BeaconWithShop> mShopList;
    private List<BeaconWithDistance> mBeaconList;
    private RecyclerLeftDrawerAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ImageView mButtonListLeft;
    private BeaconManager mBeaconManager;
    public static final String EXTRAS_BEACON = "extrasBeacon";
    public static final String SHOP_NAME = "shop name";
    public static final String METER = "meter";
    public static final BeaconRegion ALL_ESTIMOTE_BEACONS_REGION = new BeaconRegion("ranged region",
            null, null, null);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
        bundle = new Bundle();
        sortUtil = new SortUtil();
        boolean isNotifi = getIntent().getBooleanExtra(Constants.NOTIFICATION_SHOW, false);

        if (!isNotifi) {
            replaceFragment(new HomeFragment(), Constants.FragmentTag.HOME);
            updateIconMenu(Constants.Menu.MENU_HOME);
        } else {
            ListShopFragment listShopFragment = new ListShopFragment();
            Bundle bundleNotifi = getIntent().getExtras();
            List<Shop> shopList = bundleNotifi.getParcelableArrayList(Constants.NOTIFICATION_LIST);
            bundle.putParcelableArrayList(Constants.NOTIFICATION_LIST, (ArrayList<? extends Parcelable>) shopList);
            bundle.putBoolean(Constants.NOTIFICATION_SHOW, true);
            listShopFragment.setArguments(bundle);
            updateIconMenu(Constants.Menu.MENU_LIST_SHOP);
            replaceFragment(listShopFragment, Constants.FragmentTag.LIST);
        }

    }

    protected BaseFragment getFragment() {
        return new ShopFragment();
    }

    private void setUpBeacon() {
        mBeaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Log.e("beacon", "beacon ranging" + list.get(i).getMacAddress());
                    }
                    Log.e("beacon", list.size() + "");
                    mCurrentSize = list.size();
                    for (int i = 0; i < list.size(); i++) {
                        double distance = Math.pow(10d, ((double) list.get(i).getMeasuredPower() - list.get(i).getRssi()) / (10 * 2));
                        mBeaconList = new ArrayList<BeaconWithDistance>();
                        mBeaconList.add(new BeaconWithDistance(list.get(i).getMacAddress().toString(), distance));
                    }
                    if (mCurrentSize != mLastSize) {
                        Log.e("last", mLastSize + "");
                        String mMacIds = "";
                        for (int i = 0; i < list.size(); i++) {
                            mMacIds = mMacIds + list.get(i).getMacAddress().toString() + " ";
                        }

                        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAC_ID, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Constants.MAC_ID, mMacIds);
                        editor.commit();

                        Intent intent = new Intent();
                        intent.putExtra(Constants.MAC_ID, mMacIds);
                        intent.setAction(MainActivity.ACTION_BEACON_CHANGE);
                        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                        mLastSize = mCurrentSize;
                    }
                }
            }
        });
        mBeaconManager.setScanStatusListener(new BeaconManager.ScanStatusListener() {
            @Override
            public void onScanStart() {
                recyclerShopLeft.setEnabled(true);
                recyclerShopLeft.setAlpha(1.0f);
            }

            @Override
            public void onScanStop() {
                recyclerShopLeft.setEnabled(false);
                recyclerShopLeft.setAlpha(0.5f);
            }
        });
        requestBeaconReceiver = new RequestBeaconReceiver();
    }

    private void findView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mButtonHome = (ImageView) findViewById(R.id.btn_home);
        mButtonListShop = (ImageView) findViewById(R.id.btn_list_shop);
        mButtonSearch = (ImageView) findViewById(R.id.btn_search);
        mButtonSettings = (ImageView) findViewById(R.id.btn_settings);
        recyclerShopLeft = (RecyclerView) findViewById(R.id.recycler_shop_left);
        mButtonListLeft = (ImageView) findViewById(R.id.btn_near_shop);
    }

    public void init() {
        //list slide left
        mShopList = new ArrayList<>();
        recyclerShopLeft.setLayoutManager(new LinearLayoutManager(this));
        mButtonListLeft.setOnClickListener(this);
        mButtonHome.setOnClickListener(this);
        mButtonListShop.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        mButtonSettings.setOnClickListener(this);
        mBeaconManager = new BeaconManager(this);
        mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    protected void onDestroy() {
//        mBeaconManager.disconnect();
        Log.e("main", "destroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mBeaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpBeacon();
        if (SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            startScanning();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(requestBeaconReceiver,
                new IntentFilter(ACTION_BEACON_CHANGE));
        bindService(new Intent(getApplicationContext(), LocationUpdatesService.class), mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(requestBeaconReceiver);
//        unbindService(mServiceConnection);
    }

    private void startScanning() {

        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                mBeaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
            }
        });
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
                Toast.makeText(MainActivity.this, getString(R.string.map_loading), Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        replaceFragment(new HomeFragment(), Constants.FragmentTag.HOME);
                        updateIconMenu(Constants.Menu.MENU_HOME);
                    }
                }, 1000);
                break;
            case R.id.btn_list_shop:
                ListShopFragment listShopFragment = new ListShopFragment();
                bundle.putBoolean(Constants.NOTIFICATION_SHOW, false);
                listShopFragment.setArguments(bundle);
                replaceFragment(listShopFragment, Constants.FragmentTag.LIST);
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
            case R.id.btn_near_shop:
                if (!mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                    mDrawerLayout.openDrawer(GravityCompat.END);
                } else {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
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
                mButtonListLeft.setVisibility(View.VISIBLE);

                SharedPreferences sharedPreferences = getSharedPreferences(Constants.MAC_ID, MODE_PRIVATE);
                Intent intent = new Intent();
                intent.putExtra(Constants.MAC_ID, sharedPreferences.getString(Constants.MAC_ID, ""));
                intent.setAction(MainActivity.ACTION_BEACON_CHANGE);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
            case Constants.Menu.MENU_LIST_SHOP:
                mButtonHome.setImageResource(R.drawable.ic_home_transparent);
                mButtonListShop.setImageResource(R.drawable.ic_list_white);
                mButtonSearch.setImageResource(R.drawable.ic_search_transparent);
                mButtonSettings.setImageResource(R.drawable.ic_setting_transparent);
                mButtonListLeft.setVisibility(View.GONE);
                break;
            case Constants.Menu.MENU_SEARCH:
                mButtonHome.setImageResource(R.drawable.ic_home_transparent);
                mButtonListShop.setImageResource(R.drawable.ic_list_transparent);
                mButtonSearch.setImageResource(R.drawable.ic_search);
                mButtonSettings.setImageResource(R.drawable.ic_setting_transparent);
                mButtonListLeft.setVisibility(View.GONE);
                break;
            case Constants.Menu.MENU_SETTING:
                mButtonHome.setImageResource(R.drawable.ic_home_transparent);
                mButtonListShop.setImageResource(R.drawable.ic_list_transparent);
                mButtonSearch.setImageResource(R.drawable.ic_search_transparent);
                mButtonSettings.setImageResource(R.drawable.ic_setting);
                mButtonListLeft.setVisibility(View.GONE);
                break;
        }
    }

    private void changeUIHomeFrag(Context context, int positionNearest) {
        if (mShopList != null && mShopList.size() != 0) {
            HomeFragment.mLayoutAds.setVisibility(View.VISIBLE);
            Glide.with(context).load(mShopList.get(positionNearest).getmUrlImage()).fitCenter().into(HomeFragment.mImageBg);
            Glide.with(context).load(mShopList.get(positionNearest).getmUrlImage())
                    .fitCenter().into(HomeFragment.mLogoShop);
            HomeFragment.mTextShopName.setText(mShopList.get(positionNearest).getmShopName());
            HomeFragment.mTextAddress.setText(mShopList.get(positionNearest).getmShopAddress().trim());
            // setup map
            LatLng currentLocation = new LatLng(mShopList.get(positionNearest).getmLocationX(), mShopList.get(positionNearest).getmLocationY());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(currentLocation)
                    .zoom(17)
                    .build();
            HomeFragment.mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            HomeFragment.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    }

    public class RequestBeaconReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            mMacIds = intent.getStringExtra(Constants.MAC_ID);
            if (mMacIds.length() != 0) {
                mMacIds = mMacIds.substring(0, mMacIds.length() - 1);
                ApiRequest.getInstance().init();
                ApiRequest.getInstance().getListShopBeacon(mMacIds, new Callback<List<BeaconWithShop>>() {
                    @Override
                    public void onResponse(Call<List<BeaconWithShop>> call, Response<List<BeaconWithShop>> response) {
                        mShopList = response.body();
                        if (mShopList != null || mShopList.size() != 0) { // check null list shop
                            if (mBeaconList != null && mBeaconList.size() != 0) {// check null list beacon
                                for (int i = 0; i < mShopList.size(); i++) {
                                    for (int j = 0; j < mBeaconList.size(); j++) {
                                        if (mShopList.get(i).getmMacId().equals(mBeaconList.get(j).getmMacId())) {// check mac id
                                            mShopList.get(i).setmDistance(mBeaconList.get(j).getmDistance());
                                        }
                                    }
                                }
                            }
                            //process layout home
                            if (mShopList.size() > 1) {
                                sortUtil.bubbleSort(mShopList);
                            }
                            changeUIHomeFrag(context, 0);

                            adapter = new RecyclerLeftDrawerAdapter(MainActivity.this, mShopList);
                            recyclerShopLeft.setAdapter(adapter);
                        } else {
                            HomeFragment.mLayoutAds.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<BeaconWithShop>> call, Throwable t) {

                    }
                });

                //search
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Constants.STATUS_SEARCH, MODE_PRIVATE);
                if (sharedPreferences.getBoolean(Constants.STATUS_SEARCH, false)) {
                    sharedPreferences = MainActivity.this.getSharedPreferences(Constants.TAG_SEARCH, MODE_PRIVATE);
                    String tagSearch = sharedPreferences.getString(Constants.TAG_SEARCH, "");
                    if (tagSearch != "") {
                        tagSearch = tagSearch.substring(0, tagSearch.length() - 1);
                        Log.e("kw", tagSearch + "," + mMacIds);
                        ApiRequest.getInstance().getShopByKeyWord(tagSearch, mMacIds, new Callback<List<Shop>>() {
                            @Override
                            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                                List<Shop> shopList = response.body();
                                Log.e("requested", shopList.size() + "");
                                if (shopList.size() != 0 && shopList != null) {
                                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.Settings.NOTIFY_SETTING, MODE_PRIVATE);
                                    if (sharedPreferences.getBoolean(Constants.Settings.NOTIFY_SETTING, true)) {
                                        NotificationUtil.showNotifi(1, "TIFO", "Xung quanh có cửa hàng phù hợp với yêu cầu", getApplicationContext(), shopList);
                                        Log.e("notify", "search found");
                                    } else {
                                        Log.e("notify", "search found but not notify");
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Shop>> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        }
    }

    // A reference to the service used to get location updates.
    private LocationUpdatesService mService = null;

    // Tracks the bound state of the service.
    private boolean mBound = false;

    // Monitors the state of the connection to the service.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationUpdatesService.LocalBinder binder = (LocationUpdatesService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };
}
