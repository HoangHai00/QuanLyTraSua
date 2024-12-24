package com.example.trsahonghi.api.model.account

data class Account(
    val accountId: Int,
    val accountNumber: String,
    val accountName: String,
    val balance: Double,
    val accountType: String,
){}
