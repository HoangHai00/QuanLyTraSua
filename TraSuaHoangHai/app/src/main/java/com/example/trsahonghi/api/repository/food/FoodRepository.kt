package com.example.trsahonghi.api.repository.food

import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Item
import com.example.trsahonghi.api.model.MilkTea
import com.example.trsahonghi.api.model.response.BillResponse
import retrofit2.Call

interface FoodRepository {
    fun getListFood(): Call<List<MilkTea>>
    fun getListVoucher(): Call<List<Coupon>>
    fun submitOrder(
        sdtNgNhan: String?,
        status: Int?,
        address: String?,
        idVoucher: String?,
        item: List<Item>?
    ): Call<BillResponse>
}