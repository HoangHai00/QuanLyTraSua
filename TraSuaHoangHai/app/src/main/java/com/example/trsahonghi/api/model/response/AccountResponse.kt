package com.example.trsahonghi.api.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @Expose
    @SerializedName("userId")
    val userId: String,

    @Expose
    @SerializedName("sdt")
    val phoneNumber:  String,

    @Expose
    @SerializedName("ten")
    val name: String,

    @Expose
    @SerializedName("ngaySinh")
    val date: String,

    @Expose
    @SerializedName("role")
    val role: String,

) {
}