package com.example.techchallengelalamove.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.techchallengelalamove.data.database.converters.RouteConverter
import com.example.techchallengelalamove.data.database.converters.SenderConverter
import com.example.techchallengelalamove.data.database.entity.DeliveryItem

@Database(
    entities = [DeliveryItem::class],
    version = 1, exportSchema = false
)
@TypeConverters(RouteConverter::class, SenderConverter::class)
abstract class DeliveryDB : RoomDatabase() {

    abstract fun getDeliveryDao(): DeliveryDao
}