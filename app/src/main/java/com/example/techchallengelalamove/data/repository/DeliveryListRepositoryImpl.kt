package com.example.techchallengelalamove.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.AppExecutors
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryList.DeliveryListRepository
import com.example.techchallengelalamove.domain.vo.BoundaryState
import com.example.techchallengelalamove.domain.vo.LoadingStatus
import com.example.techchallengelalamove.domain.vo.Status
import javax.inject.Inject

class DeliveryListRepositoryImpl @Inject constructor(
    private val appExecutors: AppExecutors, private val remoteData: RemoteData,
    private val localData: LocalData
) : DeliveryListRepository {
    private val loadingStatus = MutableLiveData<LoadingStatus>()
    override fun getDeliveries(): LiveData<PagedList<DeliveryItem>> {
        return localData.getDeliveries()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Int>> {
        return localData.getBoundaryState()
    }

    @SuppressLint("LongLogTag")
    override fun fetchMore(offSet: Int, limit: Int, predicate: (String?) -> Boolean): LiveData<LoadingStatus> {
        if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.loading()
            Log.d("fetchMore starting: %s", offSet.toString())
            remoteData.fetchItems(offSet, limit, { delivery ->
                appExecutors.diskIO().execute {
                    localData.insertDeliveries(delivery.filter { predicate(it.id) })
                    Log.d("fetchMore saved: %s", offSet.toString())
                }
            }, { status ->
                loadingStatus.value = status

            })
        } else {
            Log.d("fetchMore already loading %s", offSet.toString())
        }
        return loadingStatus
    }


    override
    fun returnLoadingOrSuccess(): LiveData<LoadingStatus> {
        if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.success()
        }
        return loadingStatus
    }

    override fun refresh() {
        localData.refresh()
    }

}