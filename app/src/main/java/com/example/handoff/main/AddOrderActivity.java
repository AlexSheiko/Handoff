package com.example.handoff.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.handoff.R;
import com.example.handoff.base.BaseActivity;

public class AddOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickOrder(View view) {
        finish();
    }
}
