package com.example.techchallengelalamove.data.database.converters

import androidx.room.TypeConverter
import com.example.techchallengelalamove.data.database.entity.Sender
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SenderConverter {
    @TypeConverter
    fun storeSenderToString(data: Sender): String {
        val gson = Gson()
        return gson.toJson(data)
    }

    @TypeConverter
    fun storeStringToSender(value: String): Sender {
        val route: Type = object : TypeToken<Sender>() {}.type
        return Gson().fromJson(value, route)
    }
}