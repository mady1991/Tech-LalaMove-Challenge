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

    companion object {
        const val DATABASE_PAGE_SIZE = 20
    }

    override fun onZeroItemsLoaded() {
        print("onZeroItemsLoaded")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(0)
    }

    @SuppressLint("LongLogTag")
    override fun onItemAtEndLoaded(itemAtEnd: DeliveryItem) {
        Log.i("onItemAtEndLoaded %d %s %s", itemAtEnd.id)
        _boundaryState.value = BoundaryState.itemLoadedAtBottom(itemAtEnd.deliveryNo)
    }

    fun refresh() {
        print("refresh")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(0)
    }


}