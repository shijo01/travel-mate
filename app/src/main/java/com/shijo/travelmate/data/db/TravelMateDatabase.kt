package com.shijo.travelmate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shijo.travelmate.data.model.Customer
import com.shijo.travelmate.data.model.Location

@Database(entities = [Location::class, Customer::class], version = 1, exportSchema = false)
abstract class TravelMateDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun locationDao(): LocationDao
}