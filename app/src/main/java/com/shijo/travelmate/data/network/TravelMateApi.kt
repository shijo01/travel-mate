package com.shijo.travelmate.data.network

import com.shijo.travelmate.data.model.TravelMateResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface TravelMateApi {
    @GET("v2/5c261ccb3000004f0067f6ec")
    fun getData(): Observable<TravelMateResponse>
}