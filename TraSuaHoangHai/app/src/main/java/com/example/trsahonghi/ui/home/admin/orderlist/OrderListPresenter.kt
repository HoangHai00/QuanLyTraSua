package com.example.trsahonghi.ui.home.admin.orderlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.response.BillAdminResponse
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.base.CommonPresenter

class OrderListPresenter(
    private val view: OrderListContract.View,
    private val foodRepository: FoodRepository
) : CommonPresenter(view, view), OrderListContract.Presenter {
    private val _listOrder = MutableLiveData<List<BillAdminResponse>>()
    override fun getListOrderAdmin(status: Int) {
        baseCallApi(
            foodRepository.getListOrderAdmin(
                null,
                status
            ),
            onSuccess = {
                _listOrder.value = it

            },
            onError = {

            })
    }

    override fun listOrder() = _listOrder

}