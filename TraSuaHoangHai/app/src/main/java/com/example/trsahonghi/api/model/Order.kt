package com.example.trsahonghi.api.model

data class Order(
    var itemList: List<BubbleTea>,
    var id: String? = null,
    var name: String? = null,
    var orderTime: String? = null,
    var phoneNumber: String? = null,
    var status: String? = null,
    var totalAmount: String? = null
)
