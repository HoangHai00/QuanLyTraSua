package com.example.trsahonghi.ui.home.homegroup.bottomsheet

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface CartContract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        fun listBubbleTea(): LiveData<List<BubbleTea>>
        fun addAmountBubbleTea(bubbleTea: BubbleTea)
        fun subAmountBubbleTea(bubbleTea: BubbleTea)
        fun updateIngredientType(bubbleTea: BubbleTea)
        fun removeBubbleTea(position: Int)

    }
}