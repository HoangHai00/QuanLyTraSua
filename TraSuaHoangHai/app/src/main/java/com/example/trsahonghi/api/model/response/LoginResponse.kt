package com.example.trsahonghi.api.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("accessToken")
    val accessToken: String,
)
