package com.example.trsahonghi.ui.home.bestselling.adapter

import android.graphics.drawable.BitmapDrawable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.PopularItem
import com.example.trsahonghi.databinding.ItemBestSellingBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class BestSellingAdapter(
    val onClickListener: ((PopularItem, Int) -> Unit)? = null
) : BaseRecyclerViewAdapter<PopularItem, ItemBestSellingBinding>() {

    override val itemLayoutRes: Int = R.layout.item_best_selling

    override fun onBindData(
        item: PopularItem,
        position: Int,
        viewDataBinding: ItemBestSellingBinding
    ) {
        viewDataBinding.apply {
            item.image?.let {
                ivBestSelling.setImageResource(it)
            }
            txtName.text = item.name
        }
    }
}