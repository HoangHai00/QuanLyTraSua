package com.example.trsahonghi.ui.home.homegroup.bottomsheet

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.IngredientType
import com.example.trsahonghi.base.CommonPresenter

class CartPresenter(
    private val view: CartContract.View,
   listBubbleTea: List<BubbleTea>
) : CommonPresenter(), CartContract.Presenter {
    private val _listBubbleTea = MutableLiveData<List<BubbleTea>>(listBubbleTea)
    override fun listBubbleTea() = _listBubbleTea
    override fun addAmountBubbleTea(bubbleTea: BubbleTea) {
        val currentQuantity = bubbleTea.ingredientType?.quantity?.toIntOrNull() ?: 1
        bubbleTea.ingredientType?.quantity = (currentQuantity + 1).toString()
        updateIngredientType(bubbleTea)
    }

    override fun subAmountBubbleTea(bubbleTea: BubbleTea) {
        val currentQuantity = bubbleTea.ingredientType?.quantity?.toIntOrNull() ?: 1
        if (currentQuantity > 1) {
            bubbleTea.ingredientType?.quantity = (currentQuantity - 1).toString()
        } else {
            bubbleTea.ingredientType = IngredientType()
        }
        updateIngredientType(bubbleTea)
    }

    override fun updateIngredientType(bubbleTea: BubbleTea) {
        _listBubbleTea.value = _listBubbleTea.value
            ?.mapNotNull { item ->
                if (item.nameTea == bubbleTea.nameTea) {
                    if (bubbleTea.ingredientType == null || bubbleTea.ingredientType!!.quantity.isNullOrEmpty()) {
                        null
                    } else {
                        item.copy(ingredientType = bubbleTea.ingredientType)
                    }
                } else {
                    item
                }
            }?.toMutableList()
    }

    override fun removeBubbleTea(position: Int) {
        val currentList = _listBubbleTea.value?.toMutableList() ?: return
        if (position in currentList.indices) {
            currentList.removeAt(position)
            _listBubbleTea.value = currentList
        }
    }
}