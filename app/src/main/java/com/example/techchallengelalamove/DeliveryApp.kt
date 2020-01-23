package com.example.techchallengelalamove

import android.app.Application
import com.example.techchallengelalamove.di.component.ApplicationComponent
import com.example.techchallengelalamove.di.component.DaggerApplicationComponent

class DeliveryApp : Application() {

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent = appComponent
}
