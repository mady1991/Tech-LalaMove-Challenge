package com.example.techchallengelalamove.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techchallengelalamove.di.scope.ApplicationScope
import com.example.techchallengelalamove.di.scope.FragmentScope
import com.example.techchallengelalamove.ui.deliveryDetail.DeliveryDetailViewModel
import com.example.techchallengelalamove.ui.deliveryList.DeliveryListViewModel
import com.example.techchallengelalamove.ui.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @FragmentScope
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(DeliveryDetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DeliveryDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(DeliveryListViewModel::class)
    abstract fun bindListViewModel(viewModel: DeliveryListViewModel): ViewModel
}