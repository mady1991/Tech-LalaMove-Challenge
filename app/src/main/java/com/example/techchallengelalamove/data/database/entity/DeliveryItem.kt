package com.example.techchallengelalamove.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deliveries")
data class DeliveryItem(
    @PrimaryKey(autoGenerate = true)
    val deliveryNo: Int,
    val favouriteStaus: Boolean,
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("remarks")
    val remarks: String,
    @field:SerializedName("pickupTime")
    val pickupTime: String,
    @field:SerializedName("goodsPicture")
    val goodsPicture: String,
    @field:SerializedName("deliveryFee")
    val deliveryFee: String,
    @field:SerializedName("surcharge")
    val surcharge: String,
    @field:SerializedName("route")
    val route: Route,
    @field:SerializedName("sender")
    val sender: Sender
)
