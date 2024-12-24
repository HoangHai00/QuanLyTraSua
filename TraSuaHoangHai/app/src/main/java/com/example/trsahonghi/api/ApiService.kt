package com.example.trsahonghi.api

import com.example.trsahonghi.api.model.request.RegisterRequest
import com.example.trsahonghi.api.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/Account/Registration")
    fun registerAccount(@Body registrationRequest: RegisterRequest): Call<String>
}