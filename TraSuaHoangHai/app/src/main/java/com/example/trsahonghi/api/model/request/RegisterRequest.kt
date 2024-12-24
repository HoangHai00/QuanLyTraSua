package com.example.trsahonghi.api.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @Expose
    @SerializedName("sdt")
    val phoneNumber: String? = null,

    @Expose
    @SerializedName("ten")
    val fullName: String? = null,

    @Expose
    @SerializedName("matKhau")
    val passWord: String? = null,

    @Expose
    @SerializedName("ngaySinh")
    val dateOfBirth: String? = null,

    @Expose
    @SerializedName("role")
    val role: String? = null,
)
