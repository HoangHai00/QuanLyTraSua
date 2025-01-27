package com.example.trsahonghi.ui.payment.bottomsheet

import android.os.Bundle
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.databinding.BottomSheetVoucherBinding
import com.example.trsahonghi.ui.payment.adapter.VoucherAdapter
import com.lpb.lienviet24h.ui.basedatabind.BaseDataBindBottomSheet
import java.util.ArrayList

class VoucherBottomSheet(
    private val resultCoupon: ((Coupon?) -> Unit)? = null
) :
    BaseDataBindBottomSheet<BottomSheetVoucherBinding, VoucherContract.Presenter>(),
    VoucherContract.View {

    companion object {
        const val TAG = "VoucherBottomSheet"
        private const val ARG_LIST_COUPON = "ARG_LIST_COUPON"
        fun newInstance(
            listCoupon: List<Coupon>?,
            resultCoupon: ((Coupon?) -> Unit)?
        ): VoucherBottomSheet {
            val arg = Bundle().apply {
                putParcelableArrayList(ARG_LIST_COUPON, ArrayList(listCoupon))
            }
            val fragment = VoucherBottomSheet(resultCoupon)
            fragment.arguments = arg
            return fragment

        }
    }

    private val adapter: VoucherAdapter by lazy {
        VoucherAdapter { item, pos ->
            presenter.changeInterest(pos)
            adapter.notifyItemChanged(pos)
            adapter.notifyItemChanged(presenter.getPrevPosition())
            presenter.setCoupon(item.data)

        }
    }
    override val layoutRes: Int
        get() = R.layout.bottom_sheet_voucher

    override fun initView() {
        binding.apply {
            rvVoucher.adapter = adapter
            imgClose.setOnClickListener {
                dismiss()
            }
            btnContinue.setOnClickListener {
                presenter?.coupon()?.value?.let {
                    resultCoupon?.invoke(it)
                    dismiss()
                }
                dismiss()

            }

        }
    }

    override fun initData() {
        val list = arguments?.getParcelableArrayList<Coupon>(ARG_LIST_COUPON)
        presenter = VoucherPresenter(this, list).apply {
            binding.presenter = this
            listCoupon().observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }

    override fun showLoading() {}
    override fun hideLoading() {}
    override fun showMessage(message: String) {}
}