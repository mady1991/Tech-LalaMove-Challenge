package com.example.techchallengelalamove.domain.deliveryList

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.vo.BoundaryState
import com.example.techchallengelalamove.domain.vo.LoadingStatus

// As we need to interact with repostiory which is in data layer. Implementing Dependency Inversion
// Principle we create an interface which concrete respository class in data layer implements.
// This way we remove any dependency on data layer from domain layer
interface DeliveryListRepository {
    fun getDeliveries(): LiveData<PagedList<DeliveryItem>>
    fun getBoundaryState(): LiveData<BoundaryState<Int>>
    fun fetchMore(offSet: Int, limit: Int, predicate: (String?) -> (Boolean)): LiveData<LoadingStatus>
    fun returnLoadingOrSuccess(): LiveData<LoadingStatus>
    fun refresh()
}