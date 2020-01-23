package com.example.techchallengelalamove.domain.deliveryList

import com.example.techchallengelalamove.data.repository.DeliveryListRepositoryImpl
import com.example.techchallengelalamove.data.repository.LocalData
import com.example.techchallengelalamove.data.repository.RemoteData
import com.example.techchallengelalamove.utils.NetworkUtil
import org.junit.Before

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DeliveryListRepositoryImplTest {

    lateinit var deliveryListRepositoryImpl: DeliveryListRepositoryImpl

    @Mock
    lateinit var remoteData: RemoteData
    @Mock
    lateinit var localData: LocalData
    @Mock
    lateinit var networkUtil: NetworkUtil

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deliveryListRepositoryImpl = DeliveryListRepositoryImpl(remoteData, localData, networkUtil)
    }
}