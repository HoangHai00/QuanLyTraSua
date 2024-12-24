package com.example.trsahonghi.api.repository.account

import com.example.trsahonghi.api.ApiService
import com.example.trsahonghi.api.RetrofitInstance
import com.example.trsahonghi.api.model.request.RegisterRequest
import com.example.trsahonghi.api.model.response.RegisterResponse
import retrofit2.Call

class AccountRepositoryImpl(
    private val apiService: ApiService = RetrofitInstance.api
) : AccountRepository {
    override fun register(
        phoneNumber: String?,
        fullName: String?,
        password: String?,
        dateOfBirth: String?,
        role: String?
    ): Call<String> {
        return apiService.registerAccount(
            RegisterRequest(
                phoneNumber,
                fullName, password, dateOfBirth, role
            )
        )
    }
}