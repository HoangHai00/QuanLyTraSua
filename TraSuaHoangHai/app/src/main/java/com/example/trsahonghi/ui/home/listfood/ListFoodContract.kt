package com.example.trsahonghi.ui.home.listfood

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface ListFoodContract {
    interface View : BaseView, AppBehaviorOnServiceError {
       fun showBottomSheet(bubbleTea: BubbleTea)
    }

    interface Presenter : BasePresenter {
        fun updateIngredientType(bubbleTea: BubbleTea)
        fun listFood(): LiveData<MutableList<BubbleTea>>
        fun getListFood()
        fun addAmountBubbleTea(bubbleTea: BubbleTea)
        fun subAmountBubbleTea(bubbleTea: BubbleTea)
    }
}
