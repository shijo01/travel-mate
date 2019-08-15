package com.shijo.travelmate.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "location")
data class Location(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Json(name = "date")
    val date: String? = "",
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "place")
    val place: String? = "",
    @Json(name = "rate")
    var rate: Int? = 0,
    @Json(name = "url")
    val url: String? = ""
)