package com.example.techchallengelalamove.data.database

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.vo.BoundaryState

class DeliveryBoundaryCallback : PagedList.BoundaryCallback<DeliveryItem>() {
    private val _boundaryState = MutableLiveData<BoundaryState<Int>>()
    val boundaryState: LiveData<BoundaryState<Int>>
        get() = _boundaryState

    override fun onZeroItemsLoaded() {
        print("onZeroItemsLoaded")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(0)
    }

    @SuppressLint("LongLogTag")
    override fun onItemAtEndLoaded(itemAtEnd: DeliveryItem) {
        _boundaryState.value = BoundaryState.itemLoadedAtBottom(itemAtEnd.deliveryNo)

    }

    fun refresh() {
        print("refresh")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(0)
    }

    fun retry(lastValue: Int) {
        print("retry")
        _boundaryState.value = BoundaryState.itemLoadedAtBottom(lastValue)
    }


}