package com.example.trsahonghi.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.CommonPresenter

class PaymentPresenter(
    private val view: PaymentContract.View,
    listFood: List<BubbleTea>
) : CommonPresenter(), PaymentContract.Presenter {
    private val _isQrSelected = MutableLiveData<Boolean>()
    private val _listFood = MutableLiveData<List<BubbleTea>>(listFood)
    override fun listFood() = _listFood
    override fun isQrSelected() = _isQrSelected
    override fun setIsQrSelected(value: Boolean) {
        _isQrSelected.value = value
    }


    override fun removeBubbleTea(position: Int) {
        val currentList = _listFood.value?.toMutableList() ?: return
        if (position in currentList.indices) {
            currentList.removeAt(position)
            _listFood.value = currentList
        }
    }
}