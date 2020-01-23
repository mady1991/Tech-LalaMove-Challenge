package com.example.techchallengelalamove.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.data.repository.LocalData
import com.example.techchallengelalamove.domain.vo.BoundaryState
import com.example.techchallengelalamove.utils.AppUtil.DATABASE_PAGE_SIZE
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocalDataImpl @Inject constructor(
    private val deliveryDao: DeliveryDao
) : LocalData {
    private val boundaryCallback = DeliveryBoundaryCallback()


    override fun getDeliveries(): LiveData<PagedList<DeliveryItem>> {
        val dataSourceFactory = deliveryDao.getDataSourcefactory()
        return LivePagedListBuilder(
            dataSourceFactory,
            DATABASE_PAGE_SIZE
        ).setBoundaryCallback(boundaryCallback).build()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Int>> {
        return boundaryCallback.boundaryState
    }

    override fun insertDeliveries(deliveryList: List<DeliveryItem>) {
        Completable
            .complete()
            .subscribeOn(Schedulers.io())
            .subscribe { deliveryDao.insertAll(deliveryList) }
    }

    override fun refresh() {
        boundaryCallback.refresh()
    }

    override fun retry() {
        boundaryCallback.boundaryState.value?.itemData?.let { boundaryCallback.retry(it) }
    }

    override fun getDelivery(id: String): LiveData<DeliveryItem> {
        return deliveryDao.getDelivery(id)
    }

    override fun updateDelivery(status: Boolean, id: String) {
        Completable
            .complete()
            .subscribeOn(Schedulers.io())
            .subscribe { deliveryDao.update(status, id) }


    }

}