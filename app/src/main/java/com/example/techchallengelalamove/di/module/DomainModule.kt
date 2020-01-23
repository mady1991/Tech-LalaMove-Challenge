package com.example.techchallengelalamove.di.module

import com.example.techchallengelalamove.data.repository.DeliveryDetailRepositoryImpl
import com.example.techchallengelalamove.data.repository.DeliveryListRepositoryImpl
import com.example.techchallengelalamove.di.scope.FragmentScope
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.GetDetailUseCase
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.UpdateDetailUseCase
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliverListRefreshUseCase
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliveryListFetchMoreUseCase
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliveryListUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @FragmentScope
    fun bindDeliveryListRepository(deliveryListRepositoryImpl: DeliveryListRepositoryImpl): DeliveryListUseCase

    @Binds
    @FragmentScope
    fun bindDeliveryListRefreshRepository(deliveryListRepositoryImpl: DeliveryListRepositoryImpl): DeliverListRefreshUseCase

    @Binds
    @FragmentScope
    fun bindDeliveryListFetchMoreRepository(deliveryListRepositoryImpl: DeliveryListRepositoryImpl): DeliveryListFetchMoreUseCase


    @Binds
    @FragmentScope
    fun bindDeliveryDetailRepository(deliveryDetailRepositoryImpl: DeliveryDetailRepositoryImpl): GetDetailUseCase

    @Binds
    @FragmentScope
    fun bindDeliveryUpdateDetailRepository(deliveryDetailRepositoryImpl: DeliveryDetailRepositoryImpl): UpdateDetailUseCase


}