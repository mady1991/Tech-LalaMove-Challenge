package com.example.techchallengelalamove.data.network

import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryService {

    companion object {
        const val ENDPOINT = "https://mock-api-mobile.dev.lalamove.com/"
    }

    @GET("v2/deliveries")
    fun getDeliveries(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): Call<List<DeliveryItem>>

}