package com.example.trsahonghi.ui.home.promotion.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.databinding.ItemCouponBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class DiscountAdapter : BaseRecyclerViewAdapter<Coupon, ItemCouponBinding>() {
    override val itemLayoutRes: Int
        get() = R.layout.item_coupon

    override fun onBindData(
        item: Coupon,
        position: Int,
        viewDataBinding: ItemCouponBinding
    ) {
        viewDataBinding.apply {
            this.item = item
        }
    }
}