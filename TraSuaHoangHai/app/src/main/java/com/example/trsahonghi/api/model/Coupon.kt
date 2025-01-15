package com.example.trsahonghi.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coupon(
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("dateFrom")
    @Expose
    val dateFrom: String,

    @SerializedName("dateTo")
    @Expose
    val dateTo: String,

    @SerializedName("detail")
    @Expose
    val detail: String?, // Có thể null

    @SerializedName("discount")
    @Expose
    val discount: Double
)
