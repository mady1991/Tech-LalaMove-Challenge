package com.example.techchallengelalamove.di.module

import com.example.techchallengelalamove.data.repository.DeliveryDetailRepositoryImpl
import com.example.techchallengelalamove.data.repository.DeliveryListRepositoryImpl
import com.example.techchallengelalamove.di.scope.FragmentScope
import com.example.techchallengelalamove.domain.deliveryDetail.DeliveryDetailRepository
import com.example.techchallengelalamove.domain.deliveryList.DeliveryListRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @FragmentScope
    fun bindDeliveryListRepository(deliveryListRepositoryImpl: DeliveryListRepositoryImpl): DeliveryListRepository

    @Binds
    @FragmentScope
    fun bindDeliveryDetailRepository(deliveryDetailRepositoryImpl: DeliveryDetailRepositoryImpl): DeliveryDetailRepository

}