package com.example.techchallengelalamove.data.network

import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryService {


    @GET("v2/deliveries")
    fun getDeliveries(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<DeliveryItem>>

}