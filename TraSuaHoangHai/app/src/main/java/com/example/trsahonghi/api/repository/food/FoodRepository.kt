package com.example.trsahonghi.api.repository.food

import com.example.trsahonghi.api.model.MilkTea
import retrofit2.Call

interface FoodRepository {
    fun getListFood(): Call<List<MilkTea>>
}