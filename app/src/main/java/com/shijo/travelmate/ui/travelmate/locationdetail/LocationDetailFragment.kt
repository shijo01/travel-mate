package com.shijo.travelmate.ui.travelmate.locationdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.shijo.travelmate.R
import com.shijo.travelmate.databinding.LocationDetailBinding
import com.shijo.travelmate.utils.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_location_detail.*
import javax.inject.Inject

class LocationDetailFragment : DaggerFragment() {
    @Inject
    lateinit var requestManager: RequestManager
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewModel: LocationDetailViewModel
    private lateinit var binding: LocationDetailBinding
    private var locationId: Int? = 0

    companion object {
        fun newInstance(id: Int): LocationDetailFragment {
            val args = Bundle()
            args.putInt("id", id)
            val locationDetailFragment = LocationDetailFragment()
            locationDetailFragment.arguments = args
            return locationDetailFragment
        }

        val TAG = LocationDetailFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationId = arguments?.getInt("id")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_location_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(
            LocationDetailViewModel::class.java
        )

        subscribeObservers()
        imageViewBack.setOnClickListener { activity?.onBackPressed() }
    }

    private fun subscribeObservers() {
        if (locationId != null) {
            viewModel.getLocation(locationId!!).removeObservers(viewLifecycleOwner)
            viewModel.getLocation(locationId!!).observe(this, Observer { location ->
                Log.d(TAG, "subscribeObservers: $location")
                binding.location = location
                binding.imageUrl = location.url
            })
        }


    }

}