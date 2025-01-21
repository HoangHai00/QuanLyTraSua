package com.example.trsahonghi.ui.home.admin.adapter

import android.view.View
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseActivity
import com.example.trsahonghi.databinding.ItemHomeAdminGroupBinding
import com.lpb.lienviet24h.common.adapter.BaseRecyclerViewAdapter

class HomeAdminGroupAdapter(
    val baseActivity: BaseActivity
) : BaseRecyclerViewAdapter<Fragment, ItemHomeAdminGroupBinding>() {

    override val itemLayoutRes: Int = R.layout.item_home_admin_group

    override fun onBindData(
        item: Fragment,
        position: Int,
        viewDataBinding: ItemHomeAdminGroupBinding
    ) {
        val frameLayout = viewDataBinding.frItemFragment
        val uniqueId = View.generateViewId()
        frameLayout.id = uniqueId

        baseActivity.replaceFragment(item, uniqueId, false)
    }
}