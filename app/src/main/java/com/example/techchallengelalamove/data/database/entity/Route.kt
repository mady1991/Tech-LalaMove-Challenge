package com.example.techchallengelalamove.data.database.entity

import com.google.gson.annotations.SerializedName

data class Route(
    @field:SerializedName("start")
    val start: String,
    @field:SerializedName("end")
    val end: String

)

