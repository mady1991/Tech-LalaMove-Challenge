package com.example.techchallengelalamove.domain.deliveryList

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.domain.deliveryList.usecases.DeliveryListFetchMoreUseCase
import com.example.techchallengelalamove.domain.vo.Direction
import com.example.techchallengelalamove.domain.vo.LoadingStatus
import javax.inject.Inject

class DeliveryListFetchMoreUseCaseImpl @Inject constructor(private val deliveryListFetchMoreUseCase: DeliveryListFetchMoreUseCase) {


    // Check which direction the event happened.
    // if user has scrolled to the bottom (no more data in database)
    // then direction will be BOTTOM. If there is no data (usually first time the app start)
    // then fetch deliveries for current date
    @SuppressLint("LongLogTag")
    fun fetchMore(offset: Int, limit: Int, direction: Direction): LiveData<LoadingStatus> {
        val fetchIndex = when (direction) {
            Direction.BOTTOM -> offset + 1
            else -> offset
        }

        val itemDiff = offset - fetchIndex

        return if (itemDiff > 0) {
            Log.d("fetchMore future date %s", fetchIndex.toString())
            //if it's a older index don't fetch deliveries. If repository is still loading some data
            // then return loading or success to hide the progress bar.
            deliveryListFetchMoreUseCase.returnLoadingOrSuccess()
        } else {
            Log.d("fetchMore starting: %s", fetchIndex.toString())
            // Discard a Delivery which doesn't have id because on behalf of id
            // we are fetching and update deliveries
            Log.e("Boundear", "calling  in DeliveryFetchMoreUseCase")
            deliveryListFetchMoreUseCase.fetchMore(fetchIndex, limit) { id -> id != null }
        }
    }

}