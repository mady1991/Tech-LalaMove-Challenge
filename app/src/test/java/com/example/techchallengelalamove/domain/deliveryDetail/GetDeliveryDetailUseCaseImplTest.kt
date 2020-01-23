package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.GetDetailUseCase
import com.example.techchallengelalamove.domain.deliveryDetail.utils.TestDataGenerator
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class GetDeliveryDetailUseCaseImplTest {

    private lateinit var deliveryDetailUseCaseImpl: GetDetailUseCaseImpl

    @Mock
    private lateinit var useCase: GetDetailUseCase


    @get:Rule // -> allows liveData to work on different thread besides main, must be public!
    var executorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    private val deliveryItem = MutableLiveData<DeliveryItem>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deliveryDetailUseCaseImpl = GetDetailUseCaseImpl(useCase)

    }

    fun getDeliveryItem(): LiveData<DeliveryItem> {
        return deliveryItem
    }


    @Test
    fun getDeliverySuccess() {
        val delivery = TestDataGenerator.getDeliveryList()[1]
        deliveryItem.value = delivery

        Mockito.`when`(useCase.getDelivery(delivery.id))
            .thenReturn(getDeliveryItem())

        assert(deliveryItem.value?.deliveryFee != null)
    }

    @Test
    fun getDeliveryError() {
        val delivery = TestDataGenerator.getDeliveryList()[1]
        deliveryItem.value = null

        Mockito.`when`(useCase.getDelivery("WRONG_ID"))
            .thenReturn(getDeliveryItem())

        Assert.assertNull(deliveryItem.value)
    }
}


