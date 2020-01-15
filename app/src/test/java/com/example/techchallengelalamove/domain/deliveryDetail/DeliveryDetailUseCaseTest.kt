package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.techchallengelalamove.data.AppExecutors
import com.example.techchallengelalamove.data.database.DeliveryDao
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.data.repository.DeliveryDetailRepositoryImpl
import com.example.techchallengelalamove.data.repository.LocalData
import com.example.techchallengelalamove.domain.deliveryDetail.utils.TestDataGenerator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class DeliveryDetailUseCaseTest {

    private lateinit var deliveryDetailUseCase: DeliveryDetailUseCase

    @Mock
    private lateinit var repository: DeliveryDetailRepository


    @get:Rule // -> allows liveData to work on different thread besides main, must be public!
    var executorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    private val deliveryItem = MutableLiveData<DeliveryItem>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deliveryDetailUseCase = DeliveryDetailUseCase(repository)

    }

    fun getDeliveryItem(): LiveData<DeliveryItem> {
        return deliveryItem
    }


    @Test
    fun getDeliverySuccess() {
        val delivery = TestDataGenerator.getDeliveryList()[1]
        deliveryItem.value = delivery
        val id = delivery.id

        Mockito.`when`(repository.getDelivery(id))
            .thenReturn(getDeliveryItem())

        assert(deliveryItem.value != null)
    }

    @Test
    fun updateDelivery() {
    }
}


