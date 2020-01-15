package com.example.techchallengelalamove.data.database.converters

import androidx.room.TypeConverter
import com.example.techchallengelalamove.data.database.entity.Route
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class RouteConverter {
    @TypeConverter
    fun storeRouteToString(data: Route): String {
        val gson = Gson()
        return gson.toJson(data)
    }

    @TypeConverter
    fun storeStringToRoute(value: String): Route {
        val route: Type = object : TypeToken<Route>() {}.type
        return Gson().fromJson(value, route)
    }
}