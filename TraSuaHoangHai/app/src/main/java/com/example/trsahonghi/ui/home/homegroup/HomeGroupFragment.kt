package com.example.trsahonghi.ui.home.homegroup

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.ViewGroup
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.base.LocalBroadcastReceiver
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.FragmentHomeBinding
import com.example.trsahonghi.ui.home.homegroup.adapter.HomeGroupAdapter
import com.example.trsahonghi.ui.home.homegroup.adapter.MarginItemDecoration
import com.example.trsahonghi.util.Constants

class HomeGroupFragment :
    BaseDataBindFragment<FragmentHomeBinding, HomeGroupContract.Presenter>(),
    HomeGroupContract.View {
    companion object {
        fun newInstance() = HomeGroupFragment()
    }

    private val adapter by lazy {
        HomeGroupAdapter(getBaseActivity())
    }
    private val cardReceiver: LocalBroadcastReceiver by lazy {
        object : LocalBroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Constants.Actions.NOTIFY_SHOW_CARD -> showLayoutCard()
                    Constants.Actions.NOTIFY_HIDE_CARD -> hideLayoutCard()
                }
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        mBinding?.apply {
            rvHomeGroup.adapter = adapter
            rvHomeGroup.addItemDecoration(
                MarginItemDecoration(
                    requireContext(),
                    R.dimen.d_8,
                    R.dimen.d_4
                )
            )
            val intentFilter = IntentFilter().apply {
                addAction(Constants.Actions.NOTIFY_SHOW_CARD)
                addAction(Constants.Actions.NOTIFY_HIDE_CARD)
            }
            OrderEnabledLocalBroadcastManager.getInstance(getBaseActivity()).registerReceiver(
                cardReceiver,
                intentFilter
            )
        }

    }

    override fun initData() {
        mPresenter = HomeGroupPresenter(this).apply {
            mBinding?.presenter = this
            getListFragmentHome()
        }
        mPresenter?.listFragment()?.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun showLayoutCard() {
        mPresenter?.setIsCartEmpty(true)
        mBinding?.nestedScrollViewHomeGroup?.apply {
            (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = 80
        }
    }

    private fun hideLayoutCard() {
        mPresenter?.setIsCartEmpty(false)
        mBinding?.nestedScrollViewHomeGroup?.apply {
            (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = 0
        }
    }

    override fun onDestroy() {
        try {
            OrderEnabledLocalBroadcastManager.getInstance(getBaseActivity())
                .unregisterReceiver(cardReceiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }
}