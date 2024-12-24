package com.example.trsahonghi.api.repository.account

import com.example.trsahonghi.api.model.response.RegisterResponse
import retrofit2.Call

interface AccountRepository {
    fun register(
        phoneNumber: String?,
        fullName: String?,
        password: String?,
        dateOfBirth: String?,
        role: String?,
    ): Call<String>
}