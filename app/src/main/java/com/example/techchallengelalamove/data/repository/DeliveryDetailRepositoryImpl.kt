package com.example.techchallengelalamove.data.repository

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.GetDetailUseCase
import com.example.techchallengelalamove.domain.deliveryDetail.usecases.UpdateDetailUseCase
import javax.inject.Inject


class DeliveryDetailRepositoryImpl @Inject constructor(
    private val localData: LocalData
) : GetDetailUseCase,
    UpdateDetailUseCase {

    override fun getDelivery(id: String): LiveData<DeliveryItem> {
        return localData.getDelivery(id)
    }

    override fun updateDelivery(status: Boolean, id: String) {
        localData.updateDelivery(status, id)
    }

}
