package com.shijo.travelmate.ui.travelmate.locationlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shijo.travelmate.R
import com.shijo.travelmate.data.model.Customer
import com.shijo.travelmate.data.model.Location
import com.shijo.travelmate.data.model.TravelMateResponse
import com.shijo.travelmate.databinding.LocationListHeaderBinding

class LocationListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var data: TravelMateResponse
    private lateinit var locationClickListener: LocationClickListener
    private lateinit var customer: Customer
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 2) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.location_list_item, parent, false)
//            val binding =
//                LocationListItemBinding.inflate(LayoutInflater.from(parent.context))
            LocationViewHolder(view)
        } else {
            val binding =
                LocationListHeaderBinding.inflate(LayoutInflater.from(parent.context))
            HeaderViewHolder(binding)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            1
        } else {
            2
        }
    }

    override fun getItemCount(): Int {
        if (!::data.isInitialized) {
            return 0
        }
        return data.locations.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 1 && position == 0) {
            (holder as HeaderViewHolder).bind(customer)
        } else {
            (holder as LocationViewHolder).bind(data.locations[position - 1])
        }

    }

    fun setData(@NonNull data: TravelMateResponse) {
        this.data = data
        customer = Customer(data.cust_name)
        notifyDataSetChanged()
    }

    fun setLocationClickListener(locationClickListener: LocationClickListener) {
        this.locationClickListener = locationClickListener
    }


    inner class LocationViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val textViewLocation = view.findViewById<TextView>(R.id.textViewLocation)
        val textViewDate = view.findViewById<TextView>(R.id.textViewDate)
        val imageViewLocation = view.findViewById<ImageView>(R.id.imageViewLocation)
        fun bind(location: Location) {
            textViewDate.text = location.date
            textViewLocation.text = location.place
            Glide.with(view.context).load(location.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewLocation)
            view.setOnClickListener { locationClickListener.onLocationClicked(location) }
        }

    }

    inner class HeaderViewHolder(private val binding: LocationListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customer: Customer) {
            binding.customer = customer
        }
    }

    interface LocationClickListener {
        public fun onLocationClicked(location: Location)
    }


}