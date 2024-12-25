package com.example.trsahonghi.ui.home.homegroup

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.base.LocalBroadcastReceiver
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.FragmentHomeBinding
import com.example.trsahonghi.ui.home.homegroup.adapter.HomeGroupAdapter
import com.example.trsahonghi.ui.home.homegroup.adapter.MarginItemDecoration
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.StringUtils
import com.example.trsahonghi.widget.dialog.AlertDialogListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
                    Constants.Actions.NOTIFY_SHOW_CARD -> {
                        val listFoodJson =
                            intent.getStringExtra(Constants.BundleConstants.LIST_FOOD)
                        listFoodJson?.let {
                            val type = object : TypeToken<MutableList<BubbleTea>>() {}.type
                            val listFood: MutableList<BubbleTea> =
                                StringUtils.stringToObject(listFoodJson, type)
                            showLayoutCard(listFood)
                        }
                    }

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
        // back click
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                getBaseActivity().showAlertDialogNew(
                    icon = null,
                    title = getString(R.string.app_notify_title),
                    message = getString(R.string.log_out),
                    textTopButton = getString(R.string.common_success),
                    textBottomButton = getString(R.string.common_cancel),
                    listener = object : AlertDialogListener {
                        override fun onAccept() {
                            context?.let { TokenManager.saveToken(it, "") }
                            getBaseActivity().onBackFragment()
                        }

                        override fun onCancel() {

                        }
                    }
                )
            }
        })

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

    private fun showLayoutCard(listFood: List<BubbleTea>) {
        mPresenter?.setListFood(listFood)
        mPresenter?.setIsCartEmpty(true)
        mBinding?.nestedScrollViewHomeGroup?.apply {
            (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = 300
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