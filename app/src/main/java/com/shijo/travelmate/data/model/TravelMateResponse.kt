package com.shijo.travelmate.data.model

import com.squareup.moshi.Json

data class TravelMateResponse(
    @Json(name = "cust_name") val cust_name: String,
    @Json(name = "locations") val locations: List<Location>
)