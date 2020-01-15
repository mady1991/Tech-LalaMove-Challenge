package com.example.techchallengelalamove.di.module

import android.app.Application
import androidx.room.Room
import com.example.techchallengelalamove.data.database.DeliveryDB
import com.example.techchallengelalamove.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DbModule() {

    @Provides
    @ApplicationScope
    fun provideDeliveryDB(application: Application) =
        Room.databaseBuilder(application, DeliveryDB::class.java, "deliveries").build()

    @Provides
    @ApplicationScope
    fun provideDeliveryDao(deliveryDB: DeliveryDB) = deliveryDB.getDeliveryDao()

}