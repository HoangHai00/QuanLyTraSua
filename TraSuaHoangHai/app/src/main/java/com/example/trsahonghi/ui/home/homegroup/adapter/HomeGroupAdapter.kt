package com.example.trsahonghi.ui.home.homegroup.adapter

import android.view.View
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseActivity
import com.example.trsahonghi.databinding.ItemFragmentHomeBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter


class HomeGroupAdapter(
    val baseActivity: BaseActivity
) : BaseRecyclerViewAdapter<Fragment, ItemFragmentHomeBinding>() {

    override val itemLayoutRes: Int = R.layout.item_fragment_home

    override fun onBindData(
        item: Fragment,
        position: Int,
        viewDataBinding: ItemFragmentHomeBinding
    ) {
        val frameLayout = viewDataBinding.frItemFragment
        val uniqueId = View.generateViewId()
        frameLayout.id = uniqueId

       baseActivity.replaceFragment(item, uniqueId, false)
    }
}