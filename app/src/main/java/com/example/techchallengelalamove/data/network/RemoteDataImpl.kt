package com.example.techchallengelalamove.data.network

import android.util.Log
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.data.repository.RemoteData
import com.example.techchallengelalamove.domain.vo.ErrorCode
import com.example.techchallengelalamove.domain.vo.LoadingStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteDataImpl @Inject constructor(private val deliveryService: DeliveryService) :
    RemoteData {
    override fun fetchItems(
        offset: Int,
        limit: Int,
        onSuccess: (deliveries: List<DeliveryItem>) -> Unit,
        onStatus: (status: LoadingStatus) -> Unit
    ) {
        val call = deliveryService.getDeliveries(offset, limit)
        call.enqueue(object : Callback<List<DeliveryItem>?> {
            override fun onResponse(
                call: Call<List<DeliveryItem>?>?,
                response: Response<List<DeliveryItem>?>?
            ) {
                if (response != null) {
                    if (response.body()?.size == 0) {
                        onStatus(
                            LoadingStatus.error(
                                ErrorCode.NO_DATA
                            )
                        )
                    } else {
                        response.body()?.let {
                            onSuccess(it)
                            Log.d("fetchMore saved: %s", offset.toString())
                        }
                        onStatus(LoadingStatus.success())
                    }
                }
            }

            override fun onFailure(call: Call<List<DeliveryItem>?>?, t: Throwable?) {
                if (t is IOException) {
                    onStatus(
                        LoadingStatus.error(
                            ErrorCode.NETWORK_ERROR, t.message
                        )
                    )
                } else {
                    onStatus(
                        LoadingStatus.error(
                            ErrorCode.UNKNOWN_ERROR, null
                        )
                    )
                }
            }
        })

    }

}