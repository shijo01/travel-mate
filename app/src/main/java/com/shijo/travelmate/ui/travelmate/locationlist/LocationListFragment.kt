package com.shijo.travelmate.ui.travelmate.locationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.shijo.travelmate.R
import com.shijo.travelmate.data.model.Location
import com.shijo.travelmate.databinding.LocationListBinding
import com.shijo.travelmate.ui.travelmate.locationdetail.LocationDetailFragment
import com.shijo.travelmate.utils.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LocationListFragment : DaggerFragment() {
    @Inject
    lateinit var locationListAdapter: LocationListAdapter
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: LocationListViewModel
    private lateinit var binding: LocationListBinding
    private var errorSnackbar: Snackbar? = null


    companion object {
        val TAG = LocationListFragment::class.java.simpleName
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location_list, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(
            LocationListViewModel::class.java
        )

        binding.viewModel = viewModel
        binding.recyclerViewLocationList.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewLocationList.layoutManager?.isMeasurementCacheEnabled = false;
        locationListAdapter.setLocationClickListener(object :
            LocationListAdapter.LocationClickListener {
            override fun onLocationClicked(location: Location) {
                fragmentManager?.beginTransaction()
                    ?.add(
                        R.id.contentFrame,
                        LocationDetailFragment.newInstance(location.id),
                        LocationDetailFragment.TAG
                    )?.addToBackStack(LocationDetailFragment.TAG)?.commit()

            }
        })
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.errorMessage.removeObservers(viewLifecycleOwner)
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->

            if (null != errorMessage) {
                showError(errorMessage)
            } else {
                hideError()
            }

        })
    }

    private fun hideError() {
        errorSnackbar?.dismiss()

    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

}