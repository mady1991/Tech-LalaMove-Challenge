package com.example.techchallengelalamove.domain.vo

enum class Direction {
    NONE,
    BOTTOM
}

// PagedList use BoundaryCallback object to send us callback about necessary events related to data
// loading. We capture those events in this data class.
data class BoundaryState<T>(val itemData: T, val direction: Direction) {
    companion object {
        fun <T> zeroItemsLoaded(itemData: T): BoundaryState<T> {
            return BoundaryState(
                itemData,
                Direction.NONE
            )
        }

        fun <T> itemLoadedAtBottom(itemData: T): BoundaryState<T> {
            return BoundaryState(
                itemData,
                Direction.BOTTOM
            )
        }
    }
}