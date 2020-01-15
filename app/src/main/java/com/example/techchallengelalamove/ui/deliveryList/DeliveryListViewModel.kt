package com.example.techchallengelalamove.ui.deliveryList

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.techchallengelalamove.domain.deliveryList.DeliveryListUseCase
import com.example.techchallengelalamove.domain.vo.Direction
import com.example.techchallengelalamove.domain.vo.LoadingStatus
import javax.inject.Inject

class DeliveryListViewModel @Inject constructor(private val deliveryListUseCase: DeliveryListUseCase) :
    ViewModel() {
    val delivery = deliveryListUseCase.getDeliveries()

    //PagedList use BoundaryCallback object to send us callback about necessary events related to
    // data loading. Here we capture those events and fetch data from the network. The
    // DeliveryListUseCase.fetchMore() function returns loading status which we observe in UI to
    // show progress bar.
    val loadingStatus: LiveData<LoadingStatus> = Transformations.switchMap(
        deliveryListUseCase.getBoundaryState()
    ) { onBoundaryItemLoaded(it.itemData, it.direction) }

    @SuppressLint("LongLogTag")
    private fun onBoundaryItemLoaded(itemDate: Int, direction: Direction): LiveData<LoadingStatus> {
        Log.d("onBoundaryItemLoaded %s %s ", itemDate.toString() + direction)
        return deliveryListUseCase.fetchMore(itemDate, 20, direction)
    }

    fun refresh() {
        print("refreshing")
        deliveryListUseCase.refresh()
    }
}