package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.data.repository.DeliveryDetailRepositoryImpl
import com.example.techchallengelalamove.data.repository.LocalData
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.GetDetailUseCase
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.UpdateDetailUseCase
import com.example.techchallengelalamove.domain.deliveryDetail.utils.TestDataGenerator
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UpdateDetailUseCaseImplTest {
    @get:Rule
    var executorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: UpdateDetailUseCase
    private lateinit var updateDetailUseCaseImpl: UpdateDetailUseCaseImpl

    private lateinit var deliveryDetailRepositoryImpl: DeliveryDetailRepositoryImpl
    @Mock
    lateinit var localData: LocalData

    private val deliveryItem = MutableLiveData<DeliveryItem>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        updateDetailUseCaseImpl = UpdateDetailUseCaseImpl(useCase)
        deliveryDetailRepositoryImpl = DeliveryDetailRepositoryImpl(localData)


    }

    fun getDeliveryItem(): LiveData<DeliveryItem> {
        return deliveryItem
    }


    @Test
    fun updateDeliverySuccess() {
        val delivery = TestDataGenerator.getDeliveryList()[1]
        val updateDelivery = delivery.copy(favouriteStaus = true)

        useCase.updateDelivery(updateDelivery.favouriteStaus, updateDelivery.id)

        deliveryItem.value = updateDelivery

        Mockito.`when`(localData.getDelivery(delivery.id))
            .thenReturn(getDeliveryItem())

        assertNotEquals(updateDelivery.favouriteStaus, deliveryItem.value?.favouriteStaus)


    }

    @Test
    fun updateDeliveryError() {
        val delivery = TestDataGenerator.getDeliveryList()[1]
        val updateDelivery = delivery.copy(id = "XXXXXX", favouriteStaus = true)
        useCase.updateDelivery(updateDelivery.favouriteStaus, updateDelivery.id)

        deliveryItem.value = null
        Mockito.`when`(localData.getDelivery(delivery.id))
            .thenReturn(getDeliveryItem())

        assertNull(deliveryItem.value)
    }
}