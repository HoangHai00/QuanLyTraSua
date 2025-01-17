package com.example.trsahonghi.ui.home.listfood.adapter

import com.bumptech.glide.Glide
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AbsListAdapter
import com.example.trsahonghi.databinding.ItemListFoodBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class ListFoodAdapter(
    val onItemClickListener: ((BubbleTea, Int) -> Unit)? = null,
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

            Glide.with(ivTea.context)
                .load(item.id?.let { Config.getImageUrl(it) })
                .placeholder(R.drawable.ic_null)
                .error(R.drawable.ic_null)
                .into(ivTea)
            ctlItem.setOnClickListener {
                onItemClickListener?.invoke(item, position)
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