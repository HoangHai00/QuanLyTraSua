package com.example.trsahonghi.api.model.response

import com.example.trsahonghi.api.model.MilkTea
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListFoodResponse(
    @Expose
    @SerializedName("listFood")
    val listFood: List<MilkTea>

)
