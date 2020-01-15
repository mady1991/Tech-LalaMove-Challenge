package com.example.techchallengelalamove.di.module

import com.example.techchallengelalamove.data.network.DeliveryService
import com.example.techchallengelalamove.data.network.ItemTypeAdapterFactory
import com.example.techchallengelalamove.di.scope.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun provideDeliveryService(retrofit: Retrofit): DeliveryService =
        retrofit.create(DeliveryService::class.java)

    @Provides
    @ApplicationScope
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit =

        Retrofit.Builder()
            .baseUrl(DeliveryService.ENDPOINT)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @ApplicationScope
    fun provideOkHttpCLient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    @Provides
    @ApplicationScope
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapterFactory(ItemTypeAdapterFactory())
        .create()

    @Provides
    @ApplicationScope
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val url = request.url().newBuilder()
            .build()
        val newRequest = request.newBuilder()
            .url(url)
            .build()
        chain.proceed(newRequest)
    }
}

