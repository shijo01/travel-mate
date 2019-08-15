package com.shijo.travelmate.ui.travelmate.locationdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shijo.travelmate.data.db.LocationDao
import com.shijo.travelmate.data.model.Location
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(val locationDao: LocationDao) : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    fun getLocation(id: Int): LiveData<Location> {
        return locationDao.getLocationById(id)
    }


}