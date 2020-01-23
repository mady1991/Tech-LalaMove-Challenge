package com.example.techchallengelalamove.domain.deliveryList.usecases

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.domain.vo.LoadingStatus

interface DeliveryListFetchMoreUseCase {

    fun returnLoadingOrSuccess(): LiveData<LoadingStatus>

    fun fetchMore(
        offSet: Int,
        limit: Int,
        predicate: (String?) -> (Boolean)
    ): LiveData<LoadingStatus>
}