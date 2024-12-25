package com.example.trsahonghi.api.repository.account

import com.example.trsahonghi.api.model.response.AccountResponse
import com.example.trsahonghi.api.model.response.LoginResponse
import com.example.trsahonghi.api.model.response.RegisterResponse
import retrofit2.Call

interface AccountRepository {
    fun register(
        phoneNumber: String?,
        fullName: String?,
        password: String?,
        dateOfBirth: String?,
        role: String?,
    ): Call<RegisterResponse>

    fun login(
        phoneNumber: String?,
        password: String?,
    ): Call<LoginResponse>

    fun loginToken():Call<LoginResponse>

    fun getAccount(): Call<AccountResponse>
}