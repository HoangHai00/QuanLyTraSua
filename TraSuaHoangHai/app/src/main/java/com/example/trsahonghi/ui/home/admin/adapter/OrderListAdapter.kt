package com.example.trsahonghi.ui.home.admin.adapter

import com.bumptech.glide.Glide
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.IngredientType
import com.example.trsahonghi.api.model.response.BillAdminResponse
import com.example.trsahonghi.base.AbsListAdapter
import com.example.trsahonghi.databinding.ItemOrderListBinding
import com.example.trsahonghi.util.StringUtils

class OrderListAdapter(
    val onClickListener: ((BillAdminResponse, Int) -> Unit)? = null
) : AbsListAdapter<BillAdminResponse, ItemOrderListBinding>({ item1, item2 ->
    item1 == item2
}) {
    override val itemLayoutRes: Int
        get() = R.layout.item_order_list

    override fun onBindData(
        item: BillAdminResponse,
        position: Int,
        viewDataBinding: ItemOrderListBinding
    ) {
        viewDataBinding.apply {
            this.item = item
            Glide.with(ivMon.context)
                .load(item.items.firstOrNull()?.monAn?.id?.let { Config.getImageUrl(it) })
                .placeholder(R.drawable.ic_null)
                .error(R.drawable.ic_null)
                .into(ivMon)
            llItem.setOnClickListener {
                onClickListener?.invoke(item, position)
            }
            txtQuantity.text =
                item?.items?.map { item ->
                    BubbleTea(
                        id = item.monAn.id,
                        nameTea = item.monAn.tenMon,
                        price = item.monAn.gia.toString(),
                        description = item.monAn.moTa,
                        ingredientType = IngredientType(
                            quantity = item.soLuong.toString(),
                            type = item.size
                        ),
                    )
                }?.let { StringUtils.calculateTotalPrice(it) }
        }


    }
}