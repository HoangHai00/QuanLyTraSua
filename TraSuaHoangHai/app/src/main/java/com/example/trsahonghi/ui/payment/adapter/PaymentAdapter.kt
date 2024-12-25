package com.example.trsahonghi.ui.payment.adapter

import com.bumptech.glide.Glide
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AbsListAdapter
import com.example.trsahonghi.databinding.ItemMilkTeaPayMentBinding

class PaymentAdapter(
) : AbsListAdapter<BubbleTea, ItemMilkTeaPayMentBinding>({ item1, item2 ->
    item1 == item2
}) {

    override val itemLayoutRes: Int = R.layout.item_milk_tea_pay_ment

    override fun onBindData(
        item: BubbleTea,
        position: Int,
        viewDataBinding: ItemMilkTeaPayMentBinding
    ) {
        viewDataBinding.apply {

            bubbleTea = item

            Glide.with(ivTea.context)
                .load(item.id?.let {
                    Config.getImageUrl(it)
                })
                .placeholder(R.drawable.ic_null)
                .error(R.drawable.ic_null)
                .into(ivTea)
        }
    }

}