package com.example.trsahonghi.ui.home.location.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.databinding.ItemSelectAddressBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class SelectAddressAdapter(
    val onClickListener: ((String, Int) -> Unit)? = null
) : BaseRecyclerViewAdapter<String, ItemSelectAddressBinding>() {
    override val itemLayoutRes: Int
        get() = R.layout.item_select_address

    override fun onBindData(
        item: String,
        position: Int,
        viewDataBinding: ItemSelectAddressBinding
    ) {
        viewDataBinding.apply {
            tvAddress.text = item
            tvAddress.setOnClickListener {
                onClickListener?.invoke(item, position)
            }
        }
    }
}