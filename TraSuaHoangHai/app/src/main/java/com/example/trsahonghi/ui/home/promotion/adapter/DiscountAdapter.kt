package com.example.trsahonghi.ui.home.promotion.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.databinding.ItemVoucherBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class DiscountAdapter : BaseRecyclerViewAdapter<Coupon, ItemVoucherBinding>() {
    override val itemLayoutRes: Int
        get() = R.layout.item_voucher

    override fun onBindData(
        item: Coupon,
        position: Int,
        viewDataBinding: ItemVoucherBinding
    ) {
        viewDataBinding.apply {
            this.item = item
        }
    }
}