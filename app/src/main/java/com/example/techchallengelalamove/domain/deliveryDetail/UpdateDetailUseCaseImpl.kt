package com.example.techchallengelalamove.domain.deliveryDetail

import com.example.techchallengelalamove.domain.deliveryDetail.usecases.UpdateDetailUseCase
import javax.inject.Inject

class UpdateDetailUseCaseImpl @Inject constructor(private val useCase: UpdateDetailUseCase) {

    fun updateDelivery(status: Boolean, id: String) {
        useCase.updateDelivery(status, id)
    }

}