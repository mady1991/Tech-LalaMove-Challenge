package com.example.techchallengelalamove.data.database.entity

import com.google.gson.annotations.SerializedName

data class Sender(
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("email")
    val email: String
)

