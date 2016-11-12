package com.example.handoff.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handoff.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private BottomSheetBehavior<View> mBottomSheetBehavior;

    public MapFragment() {
    }

    /**
     * Returns a new instance of this fragment
     */
    public static Fragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddOrderActivity.class));
            }
        });

        View bottomSheet = view.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(dpToPixels(200));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View productInfoContainer = view.findViewById(R.id.productInfoContainer);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), productInfoContainer, "details");
                startActivity(intent, options.toBundle());
            }
        };
        view.findViewById(R.id.card1).setOnClickListener(listener);
        view.findViewById(R.id.card2).setOnClickListener(listener);
        view.findViewById(R.id.card3).setOnClickListener(listener);
        view.findViewById(R.id.card4).setOnClickListener(listener);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setBuildingsEnabled(false);

        // Add a marker in Hong Kong and move the camera
        LatLng hongKong = new LatLng(22.2847202, 114.153556);
        mMap.addMarker(new MarkerOptions().position(hongKong).title("You are here")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_current_position)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hongKong, 18f));

        addDummyMarkers();
    }

    private void addDummyMarkers() {
        LatLng marker1 = new LatLng(22.285752, 114.153876);
        mMap.addMarker(new MarkerOptions().position(marker1).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));

        LatLng marker2 = new LatLng(22.283837, 114.153856);
        mMap.addMarker(new MarkerOptions().position(marker2).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));

        LatLng marker3 = new LatLng(22.282815, 114.155760);
        mMap.addMarker(new MarkerOptions().position(marker3).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));

        LatLng marker4 = new LatLng(22.285253, 114.152930);
        mMap.addMarker(new MarkerOptions().position(marker4).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)));
    }

    private int dpToPixels(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
