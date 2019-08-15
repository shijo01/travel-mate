package com.shijo.travelmate.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shijo.travelmate.data.model.Customer
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface CustomerDao {
    @Query("select * from customer")
    fun getAllCustomers(): Observable<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg customer: Customer)
}