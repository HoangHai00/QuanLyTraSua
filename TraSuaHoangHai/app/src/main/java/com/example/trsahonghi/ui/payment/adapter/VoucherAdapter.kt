package com.example.trsahonghi.ui.payment.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Selectable
import com.example.trsahonghi.base.SelectableAdapter
import com.example.trsahonghi.databinding.ItemVoucherBinding

class VoucherAdapter(onClickListener: ((Selectable<Coupon>, Int) -> Unit)? = null) :
    SelectableAdapter<Coupon, ItemVoucherBinding>({ item1, item2 ->
        item1 == item2
    }, onClickListener = onClickListener) {
    override val itemLayoutRes: Int
        get() = R.layout.item_voucher

    override fun onBindData(
        item: Selectable<Coupon>,
        position: Int,
        viewDataBinding: ItemVoucherBinding
    ) {
        viewDataBinding.apply {
            this.item = item.data

            cbSelected.isSelected = item.selected == true

        }
    }
}