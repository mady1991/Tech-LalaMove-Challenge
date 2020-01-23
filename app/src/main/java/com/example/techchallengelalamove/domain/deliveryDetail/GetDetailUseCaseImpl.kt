package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.GetDetailUseCase
import javax.inject.Inject

class GetDetailUseCaseImpl @Inject constructor(
    private val useCase: GetDetailUseCase
) {
    fun getDelivery(id: String): LiveData<DeliveryItem> {
        return useCase.getDelivery(id)
    }
}