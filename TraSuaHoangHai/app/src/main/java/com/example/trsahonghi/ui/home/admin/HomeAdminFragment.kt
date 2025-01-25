package com.example.trsahonghi.ui.home.admin

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentHomeAdminBinding
import com.example.trsahonghi.ui.home.admin.adapter.HomeAdminGroupAdapter
import com.example.trsahonghi.ui.home.admin.orderlist.OrderListFragment
import com.example.trsahonghi.ui.home.admin.revenue.RevenueFragment
import com.example.trsahonghi.ui.home.admin.setting.SettingFragment
import com.example.trsahonghi.ui.home.bestselling.BestSellingFragment
import com.example.trsahonghi.ui.home.listfood.ListFoodFragment
import com.example.trsahonghi.ui.home.location.LocationFragment
import com.example.trsahonghi.ui.home.promotion.DiscountFragment
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils
import com.example.trsahonghi.widget.dialog.AlertDialogListener

class HomeAdminFragment :
    BaseDataBindFragment<FragmentHomeAdminBinding, HomeAdminContract.Presenter>(),
    HomeAdminContract.View {
    companion object {
        fun newInstance() = HomeAdminFragment()

    }

    override fun getLayoutId() = R.layout.fragment_home_admin

    override fun initView() {
        mBinding?.apply {
            toolbar.setOnClickListener {
                logOut()
            }

            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.list_food -> {
                        toolbar.setTitle(getString(R.string.order_list))
                        openFragment(OrderListFragment.newInstance())
                        true
                    }

                    R.id.statistics -> {
                        toolbar.setTitle(getString(R.string.statistics))
                        openFragment(RevenueFragment.newInstance())
                        true
                    }

                    R.id.setting -> {
                        toolbar.setTitle(getString(R.string.settings))
                        openFragment(SettingFragment.newInstance())
                        true
                    }

                    else -> false
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    logOut()
                }
            })
    }

    override fun initData() {
        mPresenter = HomeAdminPresenter(this).apply {
                openFragment(OrderListFragment.newInstance())
        }
    }

    private fun openFragment(fragment: Fragment) {
        getBaseActivity().replaceFragment(fragment, R.id.flHomeAdmin, false)
    }

    private fun logOut() {
        getBaseActivity().showAlertDialogNew(
            icon = null,
            title = getString(R.string.app_notify_title),
            message = getString(R.string.log_out),
            textTopButton = getString(R.string.common_success),
            textBottomButton = getString(R.string.common_cancel),
            listener = object : AlertDialogListener {
                override fun onAccept() {
                    context?.let { TokenManager.saveToken(it, "") }
                    SharedPreferencesUtils.put(Constants.KEY.KEY_PHONE_NUMBER, "")
                    getBaseActivity().onBackFragment()
                }

                override fun onCancel() {

                }
            }
        )
    }
}