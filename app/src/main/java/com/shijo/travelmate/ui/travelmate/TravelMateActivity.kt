package com.shijo.travelmate.ui.travelmate

import android.os.Bundle
import com.shijo.travelmate.R
import com.shijo.travelmate.ui.travelmate.locationlist.LocationListFragment
import dagger.android.support.DaggerAppCompatActivity

class TravelMateActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.contentFrame, LocationListFragment(), LocationListFragment.TAG).commit()
    }
}
