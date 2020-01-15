package com.example.techchallengelalamove.di.module

import com.example.techchallengelalamove.data.database.LocalDataImpl
import com.example.techchallengelalamove.data.network.RemoteDataImpl
import com.example.techchallengelalamove.data.repository.LocalData
import com.example.techchallengelalamove.data.repository.RemoteData
import com.example.techchallengelalamove.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @FragmentScope
    fun bindLocalData(localDataImpl: LocalDataImpl): LocalData

    @Binds
    @FragmentScope
    fun bindRemoteData(remoteDataImpl: RemoteDataImpl): RemoteData
}