package com.example.trsahonghi.api.repository.food

import com.example.trsahonghi.api.model.response.ListFoodResponse
import retrofit2.Call
interface FoodRepository {
    fun getListFood(): Call<ListFoodResponse>
}