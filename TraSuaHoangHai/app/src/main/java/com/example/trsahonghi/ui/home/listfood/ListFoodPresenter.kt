package com.example.trsahonghi.ui.home.listfood

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.IngredientType
import com.example.trsahonghi.base.CommonPresenter

class ListFoodPresenter(
    private val view: ListFoodContract.View
) :
    CommonPresenter(), ListFoodContract.Presenter {
    private var _listFood = MutableLiveData<MutableList<BubbleTea>>()
    override fun listFood() = _listFood

    override fun getListFood() {
        _listFood.value = mutableListOf(
            BubbleTea(R.drawable.trasuachanchauduongden, "Trà sữa trân châu hoàng gia", "25000"),
            BubbleTea(R.drawable.trasuachanchauthachzaizai, "Trà sữa panđa", "25000"),
            BubbleTea(R.drawable.ic_best_sell_1, "Trà sữa 3 anh em", "25000"),
            BubbleTea(R.drawable.ic_best_sell_1, "Trà sữa hoàng gia kem phomai", "30000"),
            BubbleTea(R.drawable.trasuachanchauthachluu, "Trà sữa thạch lựu", "20000"),
            BubbleTea(R.drawable.trasua3loai, "Trà sữa trân châu + milo", "30000"),
            BubbleTea(R.drawable.trasua3loai, "Trà sữa trân châu + soda", "20000"),
            BubbleTea(R.drawable.trasua3loai, "Trà sữa chân châu + lô hội", "20000"),
            BubbleTea(R.drawable.trasuamattra, "Trà sữa chân châu + mattra", "20000"),
            BubbleTea(R.drawable.trasuadaumatra, "Kem tươi mattra", "15000"),
            BubbleTea(R.drawable.trasuadaumatra, "Trà sữa chân châu vải", "20000"),
            BubbleTea(R.drawable.trasuaduahau, "Trà sữa chân châu dưa hấu", "20000"),
            BubbleTea(R.drawable.sodabacha, "Sođa bạc hà ", "15000"),
            BubbleTea(R.drawable.sodavietquat, "Sođa việt quất ", "20000"),
            BubbleTea(R.drawable.suachuadautay, "sữa chua dâu tây ", "30000"),
            BubbleTea(R.drawable.suachuavietquat, "sữa chua việt quất ", "20000"),
        )
    }

    override fun addAmountBubbleTea(bubbleTea: BubbleTea) {
        if (bubbleTea.ingredientType?.type.isNullOrEmpty()) {
            view.showBottomSheet(bubbleTea)
        } else {
            bubbleTea.nameTea?.let { updateIngredientType(it, IngredientType("1", "M")) }
        }

    }

    override fun subAmountBubbleTea(bubbleTea: BubbleTea) {
        bubbleTea.nameTea?.let { updateIngredientType(it, IngredientType()) }
    }

    fun updateIngredientType(name: String, newIngredientType: IngredientType) {
        _listFood.value = _listFood.value?.map { item ->
            if (item.nameTea == name) {
                item.copy(ingredientType = newIngredientType)
            } else {
                item
            }
        }?.toMutableList()
    }
}