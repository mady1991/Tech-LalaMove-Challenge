package com.example.techchallengelalamove.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliverListRefreshUseCase
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliveryListFetchMoreUseCase
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliveryListUseCase
import com.example.techchallengelalamove.domain.vo.BoundaryState
import com.example.techchallengelalamove.domain.vo.ErrorCode
import com.example.techchallengelalamove.domain.vo.LoadingStatus
import com.example.techchallengelalamove.domain.vo.Status
import com.example.techchallengelalamove.utils.NetworkUtil
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeliveryListRepositoryImpl @Inject constructor(
    private val remoteData: RemoteData,
    private val localData: LocalData,
    private val networkUtil: NetworkUtil
) : DeliveryListUseCase,
    DeliverListRefreshUseCase,
    DeliveryListFetchMoreUseCase {
    private val loadingStatus = MutableLiveData<LoadingStatus>()
    override fun getDeliveries(): LiveData<PagedList<DeliveryItem>> {
        return localData.getDeliveries()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Int>> {
        Log.e("BOUNDARY", "DeliveryListReposiataryIMPl get Boundray = ${loadingStatus.value}")
        return localData.getBoundaryState()
    }

    @SuppressLint("LongLogTag")
    override fun fetchMore(
        offSet: Int,
        limit: Int,
        predicate: (String?) -> Boolean
    ): LiveData<LoadingStatus> {

        if (!networkUtil.isInternetAvailable()) {
            loadingStatus.value = LoadingStatus.error(ErrorCode.NO_INTERNET)
            Log.e("Boundear", "calling  in DeliveryListResposistary")
        } else if (loadingStatus.value == null || loadingStatus.value?.status != Status.LOADING) {
            loadingStatus.value = LoadingStatus.loading()
            Log.d("fetchMore starting: %s", offSet.toString())
            remoteData.fetchItems(offSet, limit, { delivery ->
                Completable
                    .complete()
                    .subscribeOn(Schedulers.io())
                    .subscribe {
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

    override fun retry() {
        localData.retry()
    }

}