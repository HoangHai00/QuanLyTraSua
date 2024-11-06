package com.example.trsahonghi.api.model

data class BubbleTea(
    var image: Int? = null,
    var nameTea: String? = null,
    var price: String? = null,
    var ingredientType: IngredientType? = IngredientType(),
)
