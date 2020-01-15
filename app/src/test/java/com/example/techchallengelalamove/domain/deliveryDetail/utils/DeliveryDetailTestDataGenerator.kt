package com.example.techchallengelalamove.domain.deliveryDetail.utils

import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.data.database.entity.Route
import com.example.techchallengelalamove.data.database.entity.Sender

object TestDataGenerator {
    fun getDeliveryList(): List<DeliveryItem> {
        var d1 = DeliveryItem(
            1, false, "1",
            "Minim veniam minim nisi ullamco consequat anim reprehenderit laboris aliquip voluptate sit.",
            "2014-10-06T10:45:38-08:00",
            "https://loremflickr.com/320/240/cat?lock=9953",
            "$2.20",
            "$1.23",
            Route("Noble Court", "Noble Street"),
            Sender("hardingwelch@comdom.com", "Harding Welch", "+1 (899) 523-3905")
        )

        var d2 = DeliveryItem(
            2, false, "2",
            "Minim veniam minim nisi ullamco consequat anim reprehenderit laboris aliquip voluptate sit.",
            "2014-10-06T10:45:38-08:00",
            "https://loremflickr.com/320/240/cat?lock=9953",
            "$2.20",
            "$1.23",
            Route("Noble Court", "Noble Street"),
            Sender("hardingwelch@comdom.com", "Harding Welch", "+1 (899) 523-3905")
        )

        var d3 = DeliveryItem(
            3, false, "3",
            "Minim veniam minim nisi ullamco consequat anim reprehenderit laboris aliquip voluptate sit.",
            "2014-10-06T10:45:38-08:00",
            "https://loremflickr.com/320/240/cat?lock=9953",
            "$2.20",
            "$1.23",
            Route("Noble Court", "Noble Street"),
            Sender("hardingwelch@comdom.com", "Harding Welch", "+1 (899) 523-3905")
        )

        return listOf(d1, d2, d3)
    }


}