package com.example.trsahonghi.api.model

import android.accounts.Account

data class User(
    var id: String? = null,
    var name: String? = null,
    var account : String? = null,
    var password: String? = null
) {
}