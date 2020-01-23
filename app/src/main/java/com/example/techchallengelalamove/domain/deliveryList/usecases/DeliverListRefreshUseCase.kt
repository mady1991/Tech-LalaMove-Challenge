package com.example.techchallengelalamove.domain.deliveryList.usecases

interface DeliverListRefreshUseCase {
    fun refresh()

    fun retry()
}