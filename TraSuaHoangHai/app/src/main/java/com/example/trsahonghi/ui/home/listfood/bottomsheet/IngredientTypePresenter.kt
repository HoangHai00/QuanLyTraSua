package com.example.trsahonghi.ui.home.listfood.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.IngredientType
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants

class IngredientTypePresenter(
    private val view: IngredientTypeContract.View,
    private val bubbleTea: BubbleTea?
) : CommonPresenter(), IngredientTypeContract.Presenter {

    private val _bubbleTea = MutableLiveData<BubbleTea>(bubbleTea)
    private val _type = MutableLiveData<String>().apply {
        value = _bubbleTea.value?.ingredientType?.type ?: Constants.Type.SIZE_M
    }

    private val _quantity = MutableLiveData<String>().apply {
        value = _bubbleTea.value?.ingredientType?.quantity?: "1"
    }

    override fun updateBubbleTea() {
        val ingredientType = IngredientType(
            if (_quantity.value.isNullOrEmpty()) "1" else _quantity.value,
            _type.value
        )
        _bubbleTea.value = _bubbleTea.value?.copy(ingredientType = ingredientType)
    }



    override fun subQuantity() {
        val current = _quantity.value?.toIntOrNull() ?: 1
        if (current > 1) { // Đảm bảo không giảm dưới 1
            _quantity.value = (current - 1).toString()
        }
    }

    override fun addQuantity() {
        val current = _quantity.value?.toIntOrNull() ?: 1
        _quantity.value = (current + 1).toString()
    }


    override fun setType(type: String) {
        _type.value = type
    }

    override fun bubbleTea() = _bubbleTea

    override fun type() = _type

    override fun quantity() = _quantity
}