package com.example.trsahonghi.ui.home.homegroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface HomeGroupContract {
    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {
        fun totalAmount(): LiveData<String>
        fun quantity(): LiveData<String>
        fun listFood(): LiveData<List<BubbleTea>>
        fun setListFood(listFood: List<BubbleTea>)
        fun listFragment(): LiveData<List<Fragment>>
        fun isCartEmpty (): LiveData<Boolean>
        fun setIsCartEmpty (boolean: Boolean)
        fun getListFragmentHome()
    }
}