package com.example.techchallengelalamove.domain.deliveryList

import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliverListRefreshUseCase
import javax.inject.Inject

class DeliverListRefreshUseCaseImpl @Inject constructor(private val deliveryListRefreshUseCase: DeliverListRefreshUseCase) {

    fun refresh() {
        deliveryListRefreshUseCase.refresh()
    }

    fun retry() {
        deliveryListRefreshUseCase.retry()
    }
}