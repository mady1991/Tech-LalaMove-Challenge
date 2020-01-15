package com.example.techchallengelalamove.data.repository

import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.domain.vo.LoadingStatus

// This interface is in accordance to Dependency Inversion Principle by separating the higher
// repository class from lower network class.
interface RemoteData {
    fun fetchItems(
        offset: Int,
        limit: Int,
        onSuccess: (movies: List<DeliveryItem>) -> Unit,
        onStatus: (status: LoadingStatus) -> Unit
    )
}