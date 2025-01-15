package com.example.trsahonghi.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("soLuong")
    @Expose
    val soLuong: Int,

    @SerializedName("size")
    @Expose
    val size: String,

    @SerializedName("idMonAn")
    @Expose
    val idMonAn: String
)
