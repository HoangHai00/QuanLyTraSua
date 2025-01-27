package com.example.trsahonghi.api

import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.MilkTea
import com.example.trsahonghi.api.model.request.BillAdminRequest
import com.example.trsahonghi.api.model.request.BillRequest
import com.example.trsahonghi.api.model.request.LoginRequest
import com.example.trsahonghi.api.model.request.RegisterRequest
import com.example.trsahonghi.api.model.request.RevenueRequest
import com.example.trsahonghi.api.model.response.AccountResponse
import com.example.trsahonghi.api.model.response.BillAdminResponse
import com.example.trsahonghi.api.model.response.BillResponse
import com.example.trsahonghi.api.model.response.LoginResponse
import com.example.trsahonghi.api.model.response.RegisterResponse
import com.example.trsahonghi.api.model.response.RevenueResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/api/Account/Registration")
    fun registerAccount(@Body registrationRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/api/Account/Login")
    fun loginAccount(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/Bill")
    fun submitOrder(@Body billRequest: BillRequest): Call<BillResponse>

    @POST("/api/Bill/NextStep")
    fun confirmOrder(@Query("id") id: String): Call<LoginResponse>

    @POST("/api/Bill/cancel")
    fun cancelOrder(@Query("id") id: String): Call<LoginResponse>

    @POST("/api/ThongKe")
    fun getRevenue(@Body revenueRequest: RevenueRequest): Call<List<RevenueResponse>>

    @GET("/api/Account/Token")
    fun loginTokenAccount(): Call<LoginResponse>

    @GET("/api/Account")
    fun getAccount(): Call<AccountResponse>

    @GET("/api/MonAn")
    fun getListFood(): Call<List<MilkTea>>

    @GET("/api/voucher")
    fun getListVoucher(): Call<List<Coupon>>

    @GET("/api/Bill/BillsByAdmin")
    fun getListOrderAdmin(
        @Query("Status") status: Int?,
        @Query("SdtNguoiNhan") sdtNguoiNhan: String?
    ): Call<List<BillAdminResponse>>

}