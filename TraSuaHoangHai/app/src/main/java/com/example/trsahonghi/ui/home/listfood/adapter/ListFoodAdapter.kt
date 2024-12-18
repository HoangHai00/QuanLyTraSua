package com.example.trsahonghi.ui.home.listfood.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AbsListAdapter
import com.example.trsahonghi.databinding.ItemListFoodBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class ListFoodAdapter(
    val onAddClickListener: ((BubbleTea, Int) -> Unit)? = null,
    val onSubClickListener: ((BubbleTea, Int) -> Unit)? = null
) : AbsListAdapter<BubbleTea, ItemListFoodBinding>({ item1, item2 ->
    item1 == item2
}) {

    override val itemLayoutRes: Int = R.layout.item_list_food

    override fun onBindData(
        item: BubbleTea,
        position: Int,
        viewDataBinding: ItemListFoodBinding
    ) {
        viewDataBinding.apply {
            bubbleTea = item
            item.image?.let {
                ivTea.setImageResource(it)
            }
            btnAdd.setOnClickListener {
                onAddClickListener?.invoke(item, position)
            }
            btnSub.setOnClickListener {
                onSubClickListener?.invoke(item, position)
            }
        }
    }

}