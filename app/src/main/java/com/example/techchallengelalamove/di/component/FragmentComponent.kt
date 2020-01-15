package com.example.techchallengelalamove.di.component

import com.example.techchallengelalamove.di.module.DataModule
import com.example.techchallengelalamove.di.module.DomainModule
import com.example.techchallengelalamove.di.module.ViewModelFactoryModule
import com.example.techchallengelalamove.di.scope.FragmentScope
import com.example.techchallengelalamove.ui.deliveryDetail.DeliveryDetailFragment
import com.example.techchallengelalamove.ui.deliveryList.DeliveryListFragment
import dagger.Subcomponent

@Subcomponent(modules = [DataModule::class, DomainModule::class, ViewModelFactoryModule::class])
@FragmentScope
interface FragmentComponent {
    fun inject(deliveryListFragment: DeliveryListFragment)
    fun inject(deliveryDetailFragment: DeliveryDetailFragment)
}