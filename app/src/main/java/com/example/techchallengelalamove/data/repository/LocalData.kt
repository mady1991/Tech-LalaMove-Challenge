package com.example.techchallengelalamove.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.vo.BoundaryState
import com.example.techchallengelalamove.domain.vo.Status

// This interface is in accordance to Dependency Inversion Principle by separating the higher
// repository class from lower database class.
interface LocalData {

    fun getDeliveries(): LiveData<PagedList<DeliveryItem>>
    fun getBoundaryState(): LiveData<BoundaryState<Int>>
    fun insertDeliveries(deliveryList: List<DeliveryItem>)
    fun refresh()
    fun retry()
    fun getDelivery(id: String): LiveData<DeliveryItem>

    fun updateDelivery(status: Boolean, id: String)
}