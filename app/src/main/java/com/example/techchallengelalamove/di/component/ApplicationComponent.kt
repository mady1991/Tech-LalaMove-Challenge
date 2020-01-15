package com.example.techchallengelalamove.di.component

import android.app.Application
import com.example.techchallengelalamove.di.module.DbModule
import com.example.techchallengelalamove.di.module.RetrofitModule
import com.example.techchallengelalamove.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DbModule::class, RetrofitModule::class])
@ApplicationScope
interface ApplicationComponent {
    fun plusFragmentComponent(): FragmentComponent


    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

}