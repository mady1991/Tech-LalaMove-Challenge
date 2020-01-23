package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import javax.inject.Inject

class DeliveryDetailUseCaseImpl @Inject constructor(
    private val useCase: DeliveryDetailUseCase
) {
    fun getDelivery(id: String): LiveData<DeliveryItem> {
        return useCase.getDelivery(id)
    }
}