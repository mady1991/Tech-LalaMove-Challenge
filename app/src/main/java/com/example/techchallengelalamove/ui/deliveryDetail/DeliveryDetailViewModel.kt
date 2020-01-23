package com.example.techchallengelalamove.ui.deliveryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.deliveryDetail.GetDetailUseCaseImpl
import com.example.techchallengelalamove.domain.deliveryDetail.UpdateDetailUseCaseImpl
import javax.inject.Inject

class DeliveryDetailViewModel @Inject constructor(
    private val deliveryDetailUseCaseImpl: GetDetailUseCaseImpl,
    private val updateDetailUseCaseImpl: UpdateDetailUseCaseImpl
) :
    ViewModel() {

    private val deliveryID = MutableLiveData<String>()

    var deliveryDetail: LiveData<DeliveryItem> = Transformations
        .switchMap(deliveryID) { id ->
            deliveryDetailUseCaseImpl.getDelivery(id)
        }

    fun setDeliveryId(id: String) {
        // We need the Delivery data only once. If it's already there no need to set id again as it
        // will force to get the data again fron the database. This check is helpful in case of
        // configuration changes as we call setDeliveryId from fragment.
        if (deliveryID.value != id) {
            deliveryID.value = id
        }
    }

    fun updateDelivery(status: Boolean) {
        updateDetailUseCaseImpl.updateDelivery(status, deliveryID.value!!)
    }

}