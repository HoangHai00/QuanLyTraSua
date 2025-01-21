package com.example.trsahonghi.api.repository.food

import com.example.trsahonghi.api.ApiService
import com.example.trsahonghi.api.RetrofitInstance
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Item
import com.example.trsahonghi.api.model.MilkTea
import com.example.trsahonghi.api.model.request.BillAdminRequest
import com.example.trsahonghi.api.model.request.BillRequest
import com.example.trsahonghi.api.model.request.RevenueRequest
import com.example.trsahonghi.api.model.response.BillAdminResponse
import com.example.trsahonghi.api.model.response.BillResponse
import com.example.trsahonghi.api.model.response.LoginResponse
import com.example.trsahonghi.api.model.response.RevenueResponse
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

    override fun getRevenue(fromDate: String, toDate: String): Call<List<RevenueResponse>> {
        return apiService.getRevenue(RevenueRequest(fromDate, toDate))
    }

    override fun getListOrderAdmin(
        sdtNgNhan: String?,
        status: Int?
    ): Call<List<BillAdminResponse>> {
        return apiService.getListOrderAdmin(status, sdtNgNhan)
    }

    override fun confirmOrder(id: String): Call<LoginResponse> {
        return apiService.confirmOrder(id)
    }

    override fun cancelOrder(id: String): Call<LoginResponse> {
        return apiService.cancelOrder(id)
    }

}