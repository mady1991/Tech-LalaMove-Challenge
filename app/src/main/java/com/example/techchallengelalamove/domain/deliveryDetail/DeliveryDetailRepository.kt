package com.example.techchallengelalamove.domain.deliveryDetail

import androidx.lifecycle.LiveData
import com.example.techchallengelalamove.data.database.entity.DeliveryItem

// As we need to interact with repostiory which is in data layer. Implementing Dependency Inversion
// Principle we create an interface which concrete respository class in data layer implements.
// This way we remove any dependency on data layer from domain layer
interface DeliveryDetailRepository {
    fun getDelivery(id: String): LiveData<DeliveryItem>
    fun updateDelivery(status: Boolean, id: String)


}