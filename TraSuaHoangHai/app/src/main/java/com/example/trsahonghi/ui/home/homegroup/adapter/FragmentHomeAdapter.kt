package com.example.trsahonghi.ui.home.homegroup.adapter

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseActivity
import com.example.trsahonghi.databinding.ItemFragmentHomeBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter


class FragmentHomeAdapter(
    val context: Context,
    val onClickListener: ((Fragment, Int) -> Unit)? = null
) :
    BaseRecyclerViewAdapter<Fragment, ItemFragmentHomeBinding>() {

    override val itemLayoutRes: Int = R.layout.item_fragment_home

    override fun onBindData(
        item: Fragment,
        position: Int,
        viewDataBinding: ItemFragmentHomeBinding
    ) {
        val frameLayout = viewDataBinding.frItemFragment
        val uniqueId = View.generateViewId()
        frameLayout.id = uniqueId

        (context as BaseActivity).replaceFragment(item, uniqueId, false)
    }
}