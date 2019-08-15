package com.shijo.travelmate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey
    val name: String = ""
)