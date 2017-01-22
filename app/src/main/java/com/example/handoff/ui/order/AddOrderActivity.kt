package com.example.handoff.ui.order

import android.os.Bundle
import android.view.MenuItem
import android.view.View

import com.example.handoff.R
import com.example.handoff.ui.base.BaseActivity

class AddOrderActivity : BaseActivity() {

    internal var PLACE_PICKER_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_add)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun onClickOrder(view: View) {
        finish()
    }
}
