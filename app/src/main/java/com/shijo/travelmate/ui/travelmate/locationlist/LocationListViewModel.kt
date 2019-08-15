package com.shijo.travelmate.ui.travelmate.locationlist

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shijo.travelmate.R
import com.shijo.travelmate.data.db.CustomerDao
import com.shijo.travelmate.data.db.LocationDao
import com.shijo.travelmate.data.model.Customer
import com.shijo.travelmate.data.model.TravelMateResponse
import com.shijo.travelmate.data.network.TravelMateApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationListViewModel @Inject constructor(
    val api: TravelMateApi,
    val customerDao: CustomerDao,
    val locationDao: LocationDao,
    val locationListAdapter: LocationListAdapter
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val progressBarVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadTravelMateApiData() }

    companion object {
        private val TAG = LocationListViewModel::class.java.simpleName
    }

    init {
        //Initial loading of data when user opens the app.
        loadTravelMateApiData()
    }

    private fun loadTravelMateApiData() {

        compositeDisposable.add(locationDao.getAllLocations()
            .concatMap { dbLocationList ->
                if (dbLocationList.isNullOrEmpty())
                    api.getData().concatMap { t: TravelMateResponse ->
                        locationDao.insertAll(*t.locations.toTypedArray())
                        customerDao.insertAll(Customer(t.cust_name))
                        Observable.just(t)
                    }
                else
                    customerDao.getAllCustomers().concatMap { t: List<Customer> ->
                        val customer = t[0]
                        val travelMateResponse =
                            TravelMateResponse(cust_name = customer.name, locations = dbLocationList)
                        Observable.just(travelMateResponse)
                    }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { getDataStarted() }
            .doOnTerminate { getDataFinished() }
            .subscribe({ data -> onDataRetrieved(data) }, { onGetDataError(it) })
        )

    }

    private fun onGetDataError(throwable: Throwable) {
        errorMessage.value = R.string.loading_error
        progressBarVisibility.value = View.GONE
        Log.d(TAG, "onGetDataError: $throwable")

    }

    private fun onDataRetrieved(data: TravelMateResponse) {
        locationListAdapter.setData(data)
        progressBarVisibility.value = View.GONE

    }

    private fun getDataFinished() {
        progressBarVisibility.value = View.GONE

    }

    private fun getDataStarted() {
        progressBarVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun getProgressBarVisibility(): MutableLiveData<Int> {
        return progressBarVisibility
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}