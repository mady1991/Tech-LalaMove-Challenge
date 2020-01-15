package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import javax.inject.Inject

class DeliveryDetailUseCase @Inject constructor(
    private val repository: DeliveryDetailRepository
) {
    fun getDelivery(id: String): LiveData<DeliveryItem> {
        return repository.getDelivery(id)
    }

    fun updateDelivery(status: Boolean, id: String) {
        repository.updateDelivery(status, id)
    }


}