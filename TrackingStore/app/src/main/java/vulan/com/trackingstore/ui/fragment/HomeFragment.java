package vulan.com.trackingstore.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.customview.CustomMarkerView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    private GoogleMap mMap;
    private MapView mMapView;
    public static RelativeLayout mLayoutAds;
    public static ImageView mLogoShop, mImageBg;
    public static TextView mTextShopName, mTextAddress, mTextWatchMore;

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
    }

    @Override
    public void onPause() {
        super.onPause();
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
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        init();
    }


    private void init() {
        LatLng tesLatLng = new LatLng(21.04613, 105.78432);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(tesLatLng)
                .zoom(17)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tesLatLng));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//        mCustomMarkerViewList = getCustomMarker(getActivity());
        getCustomMarker(getActivity());

        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new HomeFragment.MarkerInfoAdapter());
        mMap.setOnInfoWindowClickListener(this);
        mMap.setBuildingsEnabled(true);
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
                            mShopList.get(i).getmShopId(), mShopList.get(i).getmName());
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
            CustomMarkerView customMarkerView = mMarkerPointHashMap.get(marker);
            textView.setText(String.format("Store ID: %s", customMarkerView.getId()));
            return view;
        }
    }
}
