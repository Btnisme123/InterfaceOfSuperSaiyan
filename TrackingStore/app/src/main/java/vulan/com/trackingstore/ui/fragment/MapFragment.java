package vulan.com.trackingstore.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.customview.CustomMarkerView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private MapView mMapView;
    private View mView;
    private List<CustomMarkerView> mCustomMarkerViewList = new ArrayList<>();
    private HashMap<Marker, CustomMarkerView> mMarkerPointHashMap = new HashMap<>();

    @Override
    protected void onCreateContentView(View rootView) {
        mView = rootView;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.activity_maps;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        com.google.android.gms.maps.MapFragment fragment = (com.google.android.gms.maps.MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//        fragment.getMapAsync(this);
        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        mMap = googleMap;
        setGroundOverlay();
        init();
    }


    private void init() {
        mCustomMarkerViewList = FakeContainer.getCustomMarker(getActivity());
        for (CustomMarkerView customMarkerView : mCustomMarkerViewList) {
            drawMarker(customMarkerView);
        }
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(new MapFragment.MarkerInfoAdapter());
        mMap.setOnInfoWindowClickListener(this);
    }

    private void setGroundOverlay() {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.picture_aeon);
        LatLng tesLatLng = new LatLng(21.007380, 105.793139);
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions()
               // .image(bitmapDescriptor)
                .position(tesLatLng, 116f, 150f)
                .bearing(53f);
//        mMap.addGroundOverlay(groundOverlayOptions);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(tesLatLng)
                .zoom(10)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tesLatLng));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void drawMarker(CustomMarkerView customMarkerView) {
        View markerView = ((LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_marker_view, null);
        markerView.setBackground(getResources().getDrawable(R.drawable.background_profile));
        LatLng latLng = new LatLng(customMarkerView.getPosition().latitude
                , customMarkerView.getPosition().longitude);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
        mMarkerPointHashMap.put(marker, customMarkerView);
    }

    private Bitmap createBitmapFromView(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new DrawerLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onLowMemory() {
        if(mMapView!=null){
            mMapView.onLowMemory();
        }
        super.onLowMemory();
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
            textView.setText("1");
            return view;
        }
    }
}
