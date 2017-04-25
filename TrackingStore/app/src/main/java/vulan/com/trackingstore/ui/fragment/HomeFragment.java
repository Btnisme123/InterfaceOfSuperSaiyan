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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.BeaconWithShop;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.service.LocationUpdatesService;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.customview.CustomMarkerView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    private static GoogleMap mMap;
    private MapView mMapView;
    public static RelativeLayout mLayoutAds;
    public static ImageView mLogoShop, mImageBg;
    public static TextView mTextShopName, mTextAddress;
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
        mLogoShop = (ImageView) rootView.findViewById(R.id.img_logo_home);
        mImageBg = (ImageView) rootView.findViewById(R.id.img_background);
    }

    private void init(Bundle savedInstanceState) {
        MapsInitializer.initialize(getActivity());
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        locationReceiver = new LocationReceiver();
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
            setUpMap(Double.parseDouble(mLocation[0]), Double.parseDouble(mLocation[1]));
        }
    }


    private void init() {
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new HomeFragment.MarkerInfoAdapter());
        mMap.setOnInfoWindowClickListener(this);
        mMap.setBuildingsEnabled(true);
    }

    public void setUpMap(double mLatitude, double mLongtitude) {
        currentLocation = new LatLng(mLatitude, mLongtitude);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLocation)
                .zoom(17)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_user);
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
                            mShopList.get(i).getmShopId(), mShopList.get(i).getmShopName(), mShopList.get(i).getmUrlImage());
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
        BitmapDescriptor bitmapDescriptor;
        switch (customMarkerView.getType()) {
            default:
                bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        }

        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
                .icon(bitmapDescriptor));
        mMarkerPointHashMap.put(marker, customMarkerView);
    }

//    private Bitmap createBitmapFromView(View view) {
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        view.setLayoutParams(new DrawerLayout.LayoutParams
//                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
//        view.buildDrawingCache();
//        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        view.draw(canvas);
//        return bitmap;
//    }

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
            ImageView imageView = (ImageView) view.findViewById(R.id.image_marker);
            CustomMarkerView customMarkerView = mMarkerPointHashMap.get(marker);
            textView.setText(customMarkerView.getName());
            Glide.with(getActivity()).load(customMarkerView.getmUrl()).fitCenter().into(imageView);
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
                String mLocation = location.getLatitude() + "," + location.getLongitude();
                editor.putString(Constants.Location.COORDINATE, mLocation);
                editor.commit();
                setUpMap(location.getLatitude(), location.getLongitude());
            }
        }
    }
}
