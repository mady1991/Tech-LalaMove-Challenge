package com.example.techchallengelalamove.data.repository

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryDetail.DeliveryDetailUseCase
import com.example.techchallengelalamove.domain.deliveryDetail.UpdateDetailUseCase
import javax.inject.Inject


class DeliveryDetailUseCaseImpl @Inject constructor(
    private val localData: LocalData
) : DeliveryDetailUseCase, UpdateDetailUseCase {

    override fun getDelivery(id: String): LiveData<DeliveryItem> {
        return localData.getDelivery(id)
    }

    override fun updateDelivery(status: Boolean, id: String) {
        localData.updateDelivery(status, id)
    }

}
