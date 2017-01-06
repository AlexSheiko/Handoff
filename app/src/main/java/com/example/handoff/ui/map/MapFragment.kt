package com.example.handoff.ui.map

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handoff.R
import com.example.handoff.data.model.Order
import com.example.handoff.ui.order.AddOrderActivity
import com.example.handoff.ui.order.DetailActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment(), MapMvpView, OnMapReadyCallback {

    private var mPresenter = MapPresenter()
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter.attachView(this)
        mPresenter.initView()

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val bottomSheet = view.findViewById(R.id.bottomSheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior!!.peekHeight = dpToPixels(200)
        bottomSheetBehavior.state = STATE_COLLAPSED

        setOnClickListeners(view)
    }

    private fun setOnClickListeners(view: View) {
        fab.setOnClickListener { mPresenter.onAddOrderClicked() }

        val listener = View.OnClickListener { view ->
            val productInfoContainer = view.findViewById(R.id.productInfoContainer)
            val intent = Intent(activity, DetailActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, productInfoContainer, "details")
            startActivity(intent, options.toBundle())
        }
        view.findViewById(R.id.card1).setOnClickListener(listener)
        view.findViewById(R.id.card2).setOnClickListener(listener)
        view.findViewById(R.id.card3).setOnClickListener(listener)
        view.findViewById(R.id.card4).setOnClickListener(listener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.detachView()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isBuildingsEnabled = false

        // Add a marker in Hong Kong and move the camera
        val hongKong = LatLng(22.2847202, 114.153556)
        mMap.addMarker(MarkerOptions().position(hongKong).title("You are here")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_current_position)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hongKong, 18f))

        addDummyMarkers()
    }

    private fun addDummyMarkers() {
        val marker1 = LatLng(22.285752, 114.153876)
        mMap.addMarker(MarkerOptions().position(marker1).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)))

        val marker2 = LatLng(22.283837, 114.153856)
        mMap.addMarker(MarkerOptions().position(marker2).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)))

        val marker3 = LatLng(22.282815, 114.155760)
        mMap.addMarker(MarkerOptions().position(marker3).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)))

        val marker4 = LatLng(22.285253, 114.152930)
        mMap.addMarker(MarkerOptions().position(marker4).title("Additional Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_pickup)))
    }

    private fun dpToPixels(dp: Int): Int {
        return applyDimension(COMPLEX_UNIT_DIP, dp.toFloat(),
                resources.displayMetrics).toInt()
    }

    companion object {
        fun newInstance(): Fragment {
            return MapFragment()
        }
    }

    /**
     * MVP method implementation
     */

    override fun showFeed(orders: List<Order>) {
    }

    override fun showAddOrder() {
        startActivity(Intent(activity, AddOrderActivity::class.java))
    }

    override fun navigateToDetails(order: Order) {
    }

    override fun navigateToSettings() {
    }
}