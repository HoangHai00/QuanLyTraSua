package com.example.trsahonghi.api.repository.food

import com.example.trsahonghi.api.ApiService
import com.example.trsahonghi.api.RetrofitInstance
import com.example.trsahonghi.api.model.response.ListFoodResponse
import retrofit2.Call

class FoodRepositoryImpl(
    private val apiService: ApiService = RetrofitInstance.api
) : FoodRepository {
    override fun getListFood(): Call<ListFoodResponse> {
        return apiService.getListFood()
    }
}