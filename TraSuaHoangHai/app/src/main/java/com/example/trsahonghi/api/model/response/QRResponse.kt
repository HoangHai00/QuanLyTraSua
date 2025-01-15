package com.example.trsahonghi.api.model.response

import com.example.trsahonghi.api.model.QRData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QRResponse(
    @SerializedName("code")
    @Expose val code: String,

    @SerializedName("desc")
    @Expose val desc: String,

    @SerializedName("data")
    @Expose val data: QRData
)
