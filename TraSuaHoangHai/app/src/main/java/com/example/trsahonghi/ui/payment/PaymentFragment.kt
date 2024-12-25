package com.example.trsahonghi.ui.payment

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.FragmentPaymentBinding
import com.example.trsahonghi.ui.payment.adapter.PaymentAdapter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.StringUtils
import com.example.trsahonghi.util.SwipeToDeleteCallback
import com.example.trsahonghi.widget.dialog.AlertDialogListener


class PaymentFragment
    : BaseDataBindFragment<FragmentPaymentBinding, PaymentContract.Presenter>(),
    PaymentContract.View {
    companion object {
        private val ARG_LIST_FOOD = "ARG_LIST_FOOD"
        fun newInstance(
            listFood: List<BubbleTea>
        ): PaymentFragment {
            val fragment = PaymentFragment()
            val args = Bundle().apply {
                putParcelableArrayList(ARG_LIST_FOOD, ArrayList(listFood))

            }
            fragment.arguments = args
            return fragment

        }
    }

    private val adapter: PaymentAdapter by lazy {
        PaymentAdapter()
    }

    override fun getLayoutId() = R.layout.fragment_payment

    override fun initView() {
        mBinding?.apply {
            rvListFood.adapter = adapter

            toolbar.setOnBackClickListener {
                mPresenter?.listFood()?.value?.let {
                    sendListFoodBroadcast(it)
                }

            }

            SwipeToDeleteCallback.setupSwipeToDelete(rvListFood) { position ->
                presenter?.removeBubbleTea(position)
            }

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        mPresenter?.listFood()?.value?.let {
                            sendListFoodBroadcast(it)
                        }
                    }
                })
        }
    }

    override fun initData() {
        val list = arguments?.getParcelableArrayList<BubbleTea>(ARG_LIST_FOOD) ?: emptyList()
        mPresenter = PaymentPresenter(this, list).apply {
            listFood().observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            mBinding?.presenter = this
        }
    }

    private fun sendListFoodBroadcast(list: List<BubbleTea>) {

        val broadcastIntent = Intent(Constants.Actions.NOTIFY_LIST_FOOD).apply {
            putExtra(
                Constants.BundleConstants.LIST_FOOD_CART,
                StringUtils.objectToString(list)
            )
        }
        context?.let { context ->
            OrderEnabledLocalBroadcastManager.getInstance(context)
                .sendBroadcast(broadcastIntent)
        }
        getBaseActivity().onBackFragment()
    }
}