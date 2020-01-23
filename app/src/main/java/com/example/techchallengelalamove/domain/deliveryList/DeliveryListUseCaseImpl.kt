package com.example.techchallengelalamove.domain.deliveryList

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliveryListUseCase
import com.example.techchallengelalamove.domain.vo.BoundaryState
import javax.inject.Inject

class DeliveryListUseCaseImpl @Inject constructor(
    private val deliveryListRepository: DeliveryListUseCase
) {

    fun getDeliveries(): LiveData<PagedList<DeliveryItem>> {
        return deliveryListRepository.getDeliveries()
    }

    fun getBoundaryState(): LiveData<BoundaryState<Int>> {
        return deliveryListRepository.getBoundaryState()
    }

}