package com.example.techchallengelalamove.ui.deliveryList

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.techchallengelalamove.domain.deliveryList.DeliverListRefreshUseCaseImpl
import com.example.techchallengelalamove.domain.deliveryList.DeliveryListFetchMoreUseCaseImpl
import com.example.techchallengelalamove.domain.deliveryList.DeliveryListUseCaseImpl
import com.example.techchallengelalamove.domain.vo.Direction
import com.example.techchallengelalamove.domain.vo.LoadingStatus
import com.example.techchallengelalamove.utils.toSingleEvent
import javax.inject.Inject

class DeliveryListViewModel @Inject constructor(
    private val deliveryListUseCase: DeliveryListUseCaseImpl,
    private val deliveryListRefreshUseCaseImpl: DeliverListRefreshUseCaseImpl,
    private val deliveryListFetchMoreUseCaseImpl: DeliveryListFetchMoreUseCaseImpl
) :
    ViewModel() {
    val delivery = deliveryListUseCase.getDeliveries()

    //PagedList use BoundaryCallback object to send us callback about necessary events related to
    // data loading. Here we capture those events and fetch data from the network. The
    // DeliveryListUseCase.fetchMore() function returns loading status which we observe in UI to
    // show progress bar.
    var loadingStatus = Transformations.switchMap(deliveryListUseCase.getBoundaryState()) {
        Log.e("Boundear", "calling Viewmodel in transformation")
        onBoundaryItemLoaded(
            it.itemData,
            it.direction
        )
    }.toSingleEvent()

    @SuppressLint("LongLogTag")
    private fun onBoundaryItemLoaded(item: Int, direction: Direction): LiveData<LoadingStatus> {

        Log.e("Boundear", "calling Viewmodel in BoundaryItem")
        return deliveryListFetchMoreUseCaseImpl.fetchMore(item, 20, direction)
    }

    fun refresh() {
        print("refreshing")
        deliveryListRefreshUseCaseImpl.refresh()
    }

    fun retry() {
        print("retry")
        deliveryListRefreshUseCaseImpl.retry()
    }
}