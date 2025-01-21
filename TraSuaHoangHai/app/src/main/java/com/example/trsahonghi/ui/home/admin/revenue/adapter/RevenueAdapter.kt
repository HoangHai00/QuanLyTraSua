package com.example.trsahonghi.ui.home.admin.revenue.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.response.RevenueResponse
import com.example.trsahonghi.databinding.ItemRevenueBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class RevenueAdapter : BaseRecyclerViewAdapter<RevenueResponse, ItemRevenueBinding>() {
    override val itemLayoutRes: Int
        get() = R.layout.item_revenue

    override fun onBindData(
        item: RevenueResponse,
        position: Int,
        viewDataBinding: ItemRevenueBinding
    ) {
        viewDataBinding.apply {
            this.item = item
        }
    }
}