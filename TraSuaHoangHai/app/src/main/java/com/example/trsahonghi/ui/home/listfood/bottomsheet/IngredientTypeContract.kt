package com.example.trsahonghi.ui.home.listfood.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface IngredientTypeContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        fun updateBubbleTea()
        fun subQuantity()
        fun addQuantity()
        fun setType(type: String)
        fun bubbleTea(): LiveData<BubbleTea>
        fun type(): LiveData<String>
        fun quantity(): MutableLiveData<String>
    }
}