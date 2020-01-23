package com.example.techchallengelalamove.domain.deliveryDetail

interface UpdateDetailUseCase {
    fun updateDelivery(status: Boolean, id: String)
}