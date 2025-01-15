package com.example.trsahonghi.ui.payment

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Item
import com.example.trsahonghi.api.repository.account.AccountRepository
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils
import com.example.trsahonghi.util.StringUtils

class PaymentPresenter(
    private val view: PaymentContract.View,
    listFood: List<BubbleTea>,
    private val accountRepository: AccountRepository,
    private val foodRepository: FoodRepository
) : CommonPresenter(view, view), PaymentContract.Presenter {
    private val _isQrSelected = MutableLiveData<Boolean>()
    private val _listFood = MutableLiveData<List<BubbleTea>>(listFood)
    private val _listItem = MutableLiveData<List<Item>>()
    private val _address = MutableLiveData<String>().apply {
        value = SharedPreferencesUtils.get(Constants.KEY.KEY_ADDRESS, "")
    }
    private val _info = MutableLiveData<String>()
    private val _phoneNumber = MutableLiveData<String>()
    private val _coupon = MutableLiveData<Coupon>()
    private val _placeOrder = MediatorLiveData<String>().apply {
        value = view.getStringRes(R.string.place_order)
    }
    private val _totalAmount = MediatorLiveData<String>()
    private val _shippingFee = MutableLiveData<String>("15.000")
    private val _discountFee = MutableLiveData<String>("0")
    private val _totalItemAmount = MediatorLiveData<String>()
    private val _listVoucher = MutableLiveData<List<Coupon>>()


    init {
        _totalAmount.addSource(_shippingFee) { updateTotalAmount() }
        _totalAmount.addSource(_discountFee) { updateTotalAmount() }
        _totalAmount.addSource(_totalItemAmount) { updateTotalAmount() }
        _totalItemAmount.addSource(_listFood) { setTotalItemAmount() }
        updateTotalAmount()
    }


    override fun listFood() = _listFood
    override fun listItem() = _listItem
    override fun isQrSelected() = _isQrSelected
    override fun address() = _address
    override fun phoneNumber() = _phoneNumber
    override fun coupon() = _coupon
    override fun setCoupon(value: Coupon) {
        _coupon.value = value
    }

    override fun placeOrder() = _placeOrder
    override fun setPlaceOrder(value: String) {
        _placeOrder.value = value
    }

    override fun totalAmount() = _totalAmount
    override fun shippingFee() = _shippingFee
    override fun discountFee() = _discountFee
    override fun setDiscountFee(value: String) {
        _discountFee.value = value
    }

    override fun totalItemAmount() = _totalItemAmount
    override fun info() = _info
    override fun listVoucher() = _listVoucher


    override fun getInfo() {
        baseCallApi(accountRepository.getAccount(), onSuccess = { response ->
            _info.value = "${response?.name} | ${response?.phoneNumber}"
            _phoneNumber.value = response?.phoneNumber


        }, onError = {

        })

    }


    override fun setTotalItemAmount() {
        _totalItemAmount.value = _listFood.value?.let {
            StringUtils.calculateTotalPrice(it)
        } ?: "0"
    }


    override fun setIsQrSelected(value: Boolean) {
        _isQrSelected.value = value
    }

    private fun updateTotalAmount() {
        val shippingFee = StringUtils.parseMoney(_shippingFee.value)
        val discountFee = StringUtils.parseMoney(_discountFee.value)
        val totalItemAmount = StringUtils.parseMoney(_totalItemAmount.value)

        val totalAmount = shippingFee - discountFee + totalItemAmount
        _totalAmount.value = StringUtils.formatMoney(totalAmount)
    }


    override fun removeBubbleTea(position: Int) {
        val currentList = _listFood.value?.toMutableList() ?: return
        if (position in currentList.indices) {
            currentList.removeAt(position)
            _listFood.value = currentList
        }
    }

    override fun submitOrder() {
        baseCallApi(
            foodRepository.submitOrder(
                _phoneNumber.value,
                1,
                _address.value,
                _coupon.value?.id,
                _listItem.value

            ), onSuccess = {
                _listFood.value = emptyList()
                view.showDiaLog(view.getStringRes(R.string.order_success))
            },
            onError = {

            }
        )
    }

    override fun getListVoucher() {
        baseCallApi(foodRepository.getListVoucher(),
            onSuccess = { response ->
                _listVoucher.value = response
            },
            onError = {

            })
    }


    override fun setListItem(listFood: List<BubbleTea>) {
        _listItem.value = listFood.map { bubbleTea ->
            Item(
                soLuong = bubbleTea.ingredientType?.quantity?.toIntOrNull() ?: 0,
                size = bubbleTea.ingredientType?.type ?: "M",
                idMonAn = bubbleTea.id ?: ""
            )
        }
    }

}