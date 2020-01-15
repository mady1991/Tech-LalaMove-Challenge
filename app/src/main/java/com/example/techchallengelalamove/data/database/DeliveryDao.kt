package com.example.techchallengelalamove.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.techchallengelalamove.data.database.entity.DeliveryItem


@Dao
interface DeliveryDao {

    @Query("SELECT deliveryNo, favouriteStaus, id, remarks, pickupTime, goodsPicture, deliveryFee, surcharge, route, sender  FROM deliveries")
    fun getDataSourcefactory(): DataSource.Factory<Int, DeliveryItem>

    @Query("SELECT * FROM deliveries WHERE id= :id")
    fun getDelivery(id: String): LiveData<DeliveryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(delvery: List<DeliveryItem>)


    @Query("UPDATE deliveries SET favouriteStaus = :favouriteStaus WHERE id =:id")
    fun update(favouriteStaus: Boolean?, id: String)
}