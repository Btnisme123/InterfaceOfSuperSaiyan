package vulan.com.trackingstore.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.ListShopAdapter;
import vulan.com.trackingstore.data.model.BeaconWithShop;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.service.LocationUpdatesService;
import vulan.com.trackingstore.ui.activity.MainActivity;
import vulan.com.trackingstore.ui.activity.ShopActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.SortUtil;
import vulan.com.trackingstore.util.customview.CustomMarkerView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    public static GoogleMap mMap;
    private MapView mMapView;
    public static RelativeLayout mLayoutAds;
    public static ImageView mLogoShop, mImageBg;
    public static TextView mTextShopName, mTextAddress, mTextNotifi;
    private SharedPreferences sharedPreferences;

    private LatLng currentLocation;
    private Location location;

    private LocationReceiver locationReceiver;
    public static final String ACTION_LOCATION_CHANGE = "vulan.com.trackingstore.ACTION_LOCATION_CHANGE";

    private List<CustomMarkerView> mCustomMarkerViewList = new ArrayList<>();
    private HashMap<Marker, CustomMarkerView> mMarkerPointHashMap = new HashMap<>();

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findView(rootView);
        init(savedInstanceState);

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home_main;
    }

    private void findView(View rootView) {
        mMapView = (MapView) rootView.findViewById(R.id.map_view);
        mLayoutAds = (RelativeLayout) rootView.findViewById(R.id.layout_ads);
        mTextAddress = (TextView) rootView.findViewById(R.id.tv_address_home);
        mTextShopName = (TextView) rootView.findViewById(R.id.tv_shop_name_home);
        mLogoShop = (ImageView) rootView.findViewById (R.id.img_logo_home);
        mImageBg = (ImageView) rootView.findViewById(R.id.img_background);
        mTextNotifi = (TextView) rootView.findViewById(R.id.tv_notify_shop);
    }

    private void init(Bundle savedInstanceState) {
        MapsInitializer.initialize(getActivity());
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        locationReceiver = new LocationReceiver();
        mTextNotifi.setSelected(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(locationReceiver,
                new IntentFilter(ACTION_LOCATION_CHANGE));
        mMapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(locationReceiver);
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        init();
        sharedPreferences = getActivity().getSharedPreferences(Constants.Location.LOCATION_HOME, MODE_PRIVATE);
        String mCoordinate = sharedPreferences.getString(Constants.Location.COORDINATE, "");
        if (!mCoordinate.equals("") && mMap != null) {
            String[] mLocation = mCoordinate.split(",");
            setUpMap(Double.parseDouble(mLocation[0]), Double.parseDouble(mLocation[1]), 0);
        }
    }


    private void init() {
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new HomeFragment.MarkerInfoAdapter());
        mMap.setOnInfoWindowClickListener(this);
        mMap.setBuildingsEnabled(true);
    }

    public void setUpMap(double mLatitude, double mLongtitude, int type) {
        mMap.clear();
        currentLocation = new LatLng(mLatitude, mLongtitude);
        if (type == 0) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(currentLocation)
                    .zoom(17)
                    .build();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_current_location);
        mMap.addMarker(new MarkerOptions().position(currentLocation)
                .icon(bitmapDescriptor));
        getCustomMarker(getActivity());
    }

    private void getCustomMarker(final Context context) {
        mCustomMarkerViewList = new ArrayList<>();
        ApiRequest.getInstance().init();
        ApiRequest.getInstance().getAllBeacon(new Callback<List<BeaconWithShop>>() {
            @Override
            public void onResponse(Call<List<BeaconWithShop>> call, Response<List<BeaconWithShop>> response) {
                List<BeaconWithShop> mShopList = response.body();
                for (int i = 0; i < mShopList.size(); i++) {
                    CustomMarkerView customMarkerView = new CustomMarkerView(context);
                    customMarkerView.setProperties(new LatLng(mShopList.get(i).getmLocationX(), mShopList.get(i).getmLocationY()),
                            mShopList.get(i).getmShopId(), mShopList.get(i).getmShopName(),
                            mShopList.get(i).getmUrlImage(), mShopList.get(i).getmShopAddress());
                    mCustomMarkerViewList.add(customMarkerView);
                }
                for (CustomMarkerView customMarkerView : mCustomMarkerViewList) {
                    drawMarker(customMarkerView);
                }
            }

            @Override
            public void onFailure(Call<List<BeaconWithShop>> call, Throwable t) {

            }
        });
    }

    private void drawMarker(CustomMarkerView customMarkerView) {
        View markerView = ((LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_marker_view, null);
        markerView.setBackground(getResources().getDrawable(R.drawable.background_profile));
        LatLng latLng = new LatLng(customMarkerView.getPosition().latitude
                , customMarkerView.getPosition().longitude);
//        BitmapDescriptor bitmapDescriptor;
//        bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker);
//
//        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
//                .icon(bitmapDescriptor));
//        marker.showInfoWindow();
//        mMarkerPointHashMap.put(marker, customMarkerView);
        IconGenerator iconFactory = new IconGenerator(getActivity());
        addIcon(iconFactory, customMarkerView.getName(), latLng, customMarkerView);
    }

    private void addIcon(IconGenerator iconFactory, CharSequence text, LatLng position, CustomMarkerView customMarkerView) {
        MarkerOptions markerOptions = new MarkerOptions().
                icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text))).
                position(position).
                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

        Marker marker = mMap.addMarker(markerOptions);
        mMarkerPointHashMap.put(marker, customMarkerView);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        CustomMarkerView customMarkerView = mMarkerPointHashMap.get(marker);
    }

    public class MarkerInfoAdapter implements GoogleMap.InfoWindowAdapter {
        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_info_marker, null);
            TextView textView = (TextView) view.findViewById(R.id.text_marker);
            TextView textAddress = (TextView) view.findViewById(R.id.text_address);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_marker);
            CustomMarkerView customMarkerView = mMarkerPointHashMap.get(marker);
            if (customMarkerView != null) {
                if (customMarkerView.getName() != null && customMarkerView.getmUrl() != null) {
                    textView.setText(customMarkerView.getName());
                    textAddress.setText(customMarkerView.getmAddress().trim());
                    Glide.with(getActivity()).load(customMarkerView.getmUrl()).fitCenter().into(imageView);
                }
            }
            return view;
        }
    }

    public class LocationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            if (location != null) {
                sharedPreferences = getActivity().getSharedPreferences(Constants.Location.LOCATION_HOME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                final String mLocation = location.getLatitude() + "," + location.getLongitude();
                editor.putString(Constants.Location.COORDINATE, mLocation);
                editor.commit();
                setUpMap(location.getLatitude(), location.getLongitude(), MainActivity.haveBeacon);

                //set up list when location change
                if (ListShopFragment.shopArrayList != null) {
                    if (ListShopFragment.mListShop != null) {
                        sharedPreferences = getActivity().getSharedPreferences(Constants.Settings.LIST_SETTING, MODE_PRIVATE);
                        ApiRequest.getInstance().getAllShop(new Callback<List<Shop>>() {
                            @Override
                            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                                ListShopFragment.shopArrayList = response.body();
                                if (sharedPreferences.getBoolean(Constants.Settings.LIST_SETTING, false)) {
                                    ListShopFragment.mDistanceShop = new float[1];
//                                sharedPreferences = getActivity().getSharedPreferences(Constants.Location.LOCATION_HOME, MODE_PRIVATE);
//                                String mLocation = sharedPreferences.getString(Constants.Location.COORDINATE, "");
                                    if (mLocation != null && !mLocation.equals("")) {
                                        String[] mCoordinate = mLocation.split(",");
                                        for (int i = 0; i < ListShopFragment.shopArrayList.size(); i++) {
                                            Location.distanceBetween(ListShopFragment.shopArrayList.get(i).getmLatitude(), ListShopFragment.shopArrayList.get(i).getmLongtitude()
                                                    , Double.parseDouble(mCoordinate[0])
                                                    , Double.parseDouble(mCoordinate[1])
                                                    , ListShopFragment.mDistanceShop);
                                            ListShopFragment.shopArrayList.get(i).setmMeter(ListShopFragment.mDistanceShop[0]);
                                        }
                                        ListShopFragment.sortUtil = new SortUtil();
                                        ListShopFragment.sortUtil.sortListShop(ListShopFragment.shopArrayList);
                                    }
                                }
                                ListShopFragment.adapter = new ListShopAdapter(getActivity(), ListShopFragment.shopArrayList);
                                ListShopFragment.mListShop.setAdapter(ListShopFragment.adapter);
                                ListShopFragment.mListShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(getActivity(), ShopActivity.class);
                                        intent.putExtra(Constants.ShopInfo.SHOP_MODEL, (Serializable) ListShopFragment.shopArrayList.get(i));
                                        startActivity(intent);
                                    }
                                });
                                ListShopFragment.adapter.notifyDataSetChanged();
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
}
