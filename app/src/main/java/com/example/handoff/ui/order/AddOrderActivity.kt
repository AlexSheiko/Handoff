package com.example.handoff.ui.order

import android.content.Intent
import android.os.Bundle
import com.example.handoff.R
import com.example.handoff.ui.base.BaseActivity
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.location.places.ui.PlacePicker.getPlace
import kotlinx.android.synthetic.main.activity_order_add.*
import org.jetbrains.anko.onClick

class AddOrderActivity : BaseActivity() {

    internal var PICKUP_REQUEST = 1
    internal var HANDOFF_REQUEST = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_add)

        setOnClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val place = getPlace(data, this)

            when (requestCode) {
                PICKUP_REQUEST -> pickupField.setText(place.address)
                HANDOFF_REQUEST -> handoffField.setText(place.address)
            }
        }
    }

    private fun setOnClickListeners() {
        pickupField.onClick { selectLocation(PICKUP_REQUEST) }
        handoffField.onClick { selectLocation(HANDOFF_REQUEST) }
        orderButton.onClick { submitOrder() }
    }

    private fun selectLocation(code: Int) {
        val builder = PlacePicker.IntentBuilder()

        startActivityForResult(builder.build(this), code)
    }

    fun submitOrder() {
        finish()
    }
}
