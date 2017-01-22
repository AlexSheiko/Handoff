package com.example.handoff.data.model.helper

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class Destination(val id: String,
                  val user_id: Int,
                  val type: Int,
                  val address1: String,
                  val address2: String,
                  val lat: Double,
                  val long: Double) : ClusterItem {

    override fun getPosition(): LatLng {
        return LatLng(lat, long)
    }
}