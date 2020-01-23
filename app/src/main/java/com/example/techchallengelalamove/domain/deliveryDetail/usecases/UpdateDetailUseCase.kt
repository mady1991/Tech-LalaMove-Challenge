package com.example.techchallengelalamove.domain.deliveryDetail.usecases

interface UpdateDetailUseCase {
    fun updateDelivery(status: Boolean, id: String)
}