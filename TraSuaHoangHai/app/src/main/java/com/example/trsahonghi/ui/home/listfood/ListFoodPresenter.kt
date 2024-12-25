package com.example.trsahonghi.ui.home.listfood

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.IngredientType
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants

class ListFoodPresenter(
    private val view: ListFoodContract.View,
    private val foodRepository: FoodRepository
) :
    CommonPresenter(view, view), ListFoodContract.Presenter {
    private var _listFood = MutableLiveData<MutableList<BubbleTea>>()
    override fun listFood() = _listFood

    override fun updateListFood(listFood: List<BubbleTea>) {
        _listFood.value = _listFood.value?.map { item ->
            listFood.find { it.nameTea == item.nameTea }?.let {
                item.copy(ingredientType = it.ingredientType)
            } ?: item.copy(ingredientType = IngredientType())
        }?.toMutableList()
    }

    override fun getListFood() {
        baseCallApi(foodRepository.getListFood(),
            onSuccess = { response ->
                _listFood.value = response?.map { foodResponse ->
                    BubbleTea(
                        id = foodResponse.id,
                        nameTea = foodResponse.nameFood,
                        price = foodResponse.price.toString(),
                        description = foodResponse.description
                    )
                }?.toMutableList()
            },
            onError = {

            })

    }

    override fun addAmountBubbleTea(bubbleTea: BubbleTea) {
        if (bubbleTea.ingredientType?.type.isNullOrEmpty()) {
            view.showBottomSheet(bubbleTea)
        } else {
            val currentQuantity = bubbleTea.ingredientType?.quantity?.toIntOrNull() ?: 1
            bubbleTea.ingredientType?.quantity = (currentQuantity + 1).toString()
            updateIngredientType(bubbleTea)
        }

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

    override fun getBroadcastAction(listFood: MutableList<BubbleTea>): Pair<String, List<BubbleTea>> {
        val filteredList = listFood.filter { it.ingredientType?.quantity != null }
        val action = if (filteredList.isNotEmpty()) {
            Constants.Actions.NOTIFY_SHOW_CART
        } else {
            Constants.Actions.NOTIFY_HIDE_CART
        }
        return Pair(action, filteredList)
    }


    override fun updateIngredientType(bubbleTea: BubbleTea) {
        _listFood.value = _listFood.value?.map { item ->
            if (item.nameTea == bubbleTea.nameTea) {
                item.copy(ingredientType = bubbleTea.ingredientType)
            } else {
                item
            }
        }?.toMutableList()
    }
}