package com.example.trsahonghi.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BubbleTea(
    var image: Int? = null,
    var nameTea: String? = null,
    var price: String? = null,
    var ingredientType: IngredientType? = IngredientType()
) : Parcelable
