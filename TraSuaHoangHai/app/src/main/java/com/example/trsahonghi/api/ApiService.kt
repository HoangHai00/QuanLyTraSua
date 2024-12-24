package com.example.trsahonghi.api

import com.example.trsahonghi.api.model.account.Account
import com.example.trsahonghi.api.model.request.login.LoginRequest
import com.example.trsahonghi.api.model.request.password.UpdatePassWordRequest
import com.example.trsahonghi.api.model.request.register.RegisterRequest
import com.example.trsahonghi.api.model.response.login.LoginResponse
import com.example.trsahonghi.api.model.response.password.UpdatePassWordResponse
import com.example.trsahonghi.api.model.response.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/api/Account")
    fun getAccount(): Call<List<Account>>

    @DELETE("/api/Account")
    fun deleteAccount(): Call<Void>

    @POST("/api/Account/Registration")
    fun registerAccount(@Body registrationRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/api/Account/Login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/Account/UpdatePassword")
    fun updatePassword(@Body updatePasswordRequest: UpdatePassWordRequest): Call<UpdatePassWordResponse>
}