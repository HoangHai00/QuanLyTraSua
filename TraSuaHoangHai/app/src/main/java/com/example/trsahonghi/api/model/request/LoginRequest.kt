package com.example.trsahonghi.api.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose
    @SerializedName("sdt")
    val phoneNumber: String? = null,

    @Expose
    @SerializedName("pass")
    val passWord: String? = null
)
