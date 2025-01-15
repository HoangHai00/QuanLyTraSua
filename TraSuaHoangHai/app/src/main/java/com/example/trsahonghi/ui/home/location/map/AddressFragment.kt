package com.example.trsahonghi.ui.home.location.map

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentAddressBinding
import com.example.trsahonghi.ui.home.listfood.bottomsheet.IngredientTypeBottomSheet
import com.example.trsahonghi.ui.home.location.bottomsheet.SelectAddressBottomSheet
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils

class AddressFragment : BaseDataBindFragment<FragmentAddressBinding, AddressContract.Presenter>(),
    AddressContract.View {
    companion object {
        fun newInstance() = AddressFragment()
    }

    override fun getLayoutId() = R.layout.fragment_address


    override fun initView() {
        mBinding?.apply {
            toolbar.setOnBackClickListener{
                onBackClick()
            }
            btnConfirm.setOnClickListener {
                mPresenter?.updateAddress()
                mPresenter?.address()?.value?.let { it1 ->
                    SharedPreferencesUtils.put(
                        Constants.KEY.KEY_ADDRESS,
                        it1
                    )
                }
                onBackClick()

            }
            tvAddress.setOnClickListener {
                showBottomSheet()
            }
            ivAddress.setOnClickListener {
                showBottomSheet()
            }

        }
    }

    override fun initData() {
        mPresenter = AddressPresenter(this).apply {
            mBinding?.presenter = this

        }

    }

    private fun showBottomSheet() {
        val selectAddressBottomSheet =
            SelectAddressBottomSheet.newInstance() { address ->
                mPresenter?.setChooseLocation(address.toString())
            }

        selectAddressBottomSheet.show(parentFragmentManager, IngredientTypeBottomSheet.TAG)

    }

    override fun getStringRes(id: Int): String {
        return getString(id)
    }

    override fun onBackClick() {
        getBaseActivity().onBackFragment()

    }


}