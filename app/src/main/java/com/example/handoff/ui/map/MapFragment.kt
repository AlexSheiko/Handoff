package com.example.handoff.ui.map

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handoff.R
import com.example.handoff.data.model.Order
import com.example.handoff.data.model.helper.Destination
import com.example.handoff.ui.base.BaseFragment
import com.example.handoff.ui.order.AddOrderActivity
import com.example.handoff.ui.order.DetailActivity
import com.example.handoff.ui.signin.WelcomeActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.fragment_map.*
import org.jetbrains.anko.startActivity
import rx.Observable

class MapFragment : BaseFragment(), MapMvpView, OnMapReadyCallback {

    private var mPresenter = MapPresenter()
    lateinit var mClusterManager: ClusterManager<Destination>
    lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
        initMapFragment()
        initBottomSheet()

        setOnClickListeners(view)
    }

    private fun initBottomSheet() {
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.peekHeight = dpToPixels(200)
        behavior.state = STATE_COLLAPSED
    }

    fun initMapFragment() {
        if (ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(ACCESS_FINE_LOCATION), 1)
            return
        }
        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initPresenter()
            initMapFragment()
        }
    }

    private fun initPresenter() {
        mPresenter.attachView(this)
        mPresenter.loadOrders(activity.getToken())
    }

    private fun setOnClickListeners(view: View) {
        fab.setOnClickListener { mPresenter.createOrder() }

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

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        map.isBuildingsEnabled = false
        map.isMyLocationEnabled = true
        map.setPadding(0, dpToPixels(150), dpToPixels(4), dpToPixels(250))

        mClusterManager = ClusterManager<Destination>(activity, map)
        map.setOnCameraIdleListener(mClusterManager)
        map.setOnMarkerClickListener(mClusterManager)
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

    override fun showOrders(obs: Observable<List<Order>>) {
        val builder = LatLngBounds.Builder()
        obs.flatMap { Observable.from(it) }
                .doOnNext {
                    mClusterManager.addItem(it.destination)
                    builder.include(it.destination.position)
                }
                .subscribe({
                    val bounds = builder.build()
                    val padding = 0
                    val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                    mMap.animateCamera(cu)
                }, { t -> handleError(t) })
    }

    private fun handleError(t: Throwable) {
        if (t.message.toString().contains("malformed")) {
            // TODO: Refresh access token goo.gl/3Cc3kE
            logout() // TODO: Remove after refreshing tokens
        }
        t.printStackTrace()
    }

    private fun logout() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
        prefs.edit().clear().apply()
        activity.startActivity<WelcomeActivity>()
        activity.finishAffinity()
    }

    override fun showAddOrder() {
        startActivity(Intent(activity, AddOrderActivity::class.java))
    }

    override fun navigateToDetails(order: Order) {
    }

    override fun navigateToSettings() {
    }
}
