package com.example.techchallengelalamove.data.database

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.AppExecutors
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.data.repository.LocalData
import com.example.techchallengelalamove.domain.vo.BoundaryState
import javax.inject.Inject

class LocalDataImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val deliveryDao: DeliveryDao
) : LocalData {
    private val boundaryCallback = DeliveryBoundaryCallback()


    override fun getDeliveries(): LiveData<PagedList<DeliveryItem>> {
        val dataSourceFactory = deliveryDao.getDataSourcefactory()
        return LivePagedListBuilder(
            dataSourceFactory,
            DeliveryBoundaryCallback.DATABASE_PAGE_SIZE
        ).setBoundaryCallback(boundaryCallback).build()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Int>> {
        return boundaryCallback.boundaryState
    }

    override fun insertDeliveries(deliveryList: List<DeliveryItem>) {
        appExecutors.diskIO().execute({
            deliveryDao.insertAll(deliveryList)
        })
    }

    override fun refresh() {
        boundaryCallback.refresh()
    }

    override fun getDelivery(id: String): LiveData<DeliveryItem> {
        return deliveryDao.getDelivery(id)
    }

    override fun updateDelivery(status: Boolean, id: String) {
        appExecutors.diskIO().execute({
            deliveryDao.update(status, id)
        })
    }

}