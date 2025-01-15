package com.example.trsahonghi.api.repository.food

import com.example.trsahonghi.api.ApiService
import com.example.trsahonghi.api.RetrofitInstance
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Item
import com.example.trsahonghi.api.model.MilkTea
import com.example.trsahonghi.api.model.request.BillRequest
import com.example.trsahonghi.api.model.response.BillResponse
import retrofit2.Call

class FoodRepositoryImpl(
    private val apiService: ApiService = RetrofitInstance.api
) : FoodRepository {
    override fun getListFood(): Call<List<MilkTea>> {
        return apiService.getListFood()
    }

    override fun getListVoucher(): Call<List<Coupon>> {
        return apiService.getListVoucher()
    }

    override fun submitOrder(
        sdtNgNhan: String?,
        status: Int?,
        address: String?,
        idVoucher: String?,
        item: List<Item>?
    ): Call<BillResponse> {
        val billRequest = BillRequest(
            sdtNgNhan = sdtNgNhan,
            status = status,
            address = address,
            idVoucher = idVoucher,
            item = item
        )
        return apiService.submitOrder(billRequest)
    }

}