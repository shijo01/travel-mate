package com.shijo.travelmate.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shijo.travelmate.data.model.Location
import io.reactivex.Observable

@Dao
interface LocationDao {
    @Query("select * from location")
    fun getAllLocations(): Observable<List<Location>>

    @Query("SELECT * FROM location WHERE id == :id")
    fun getLocationById(id: Int): LiveData<Location>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg location: Location)
}