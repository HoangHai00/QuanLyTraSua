package com.example.trsahonghi.ui.home.listfood.bottomsheet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.bumptech.glide.Glide
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.databinding.BottomSheetIngredientTypeBinding
import com.example.trsahonghi.util.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lpb.lienviet24h.ui.basedatabind.BaseDataBindBottomSheet

class IngredientTypeBottomSheet(
    private val resultIngredientType: ((BubbleTea?) -> Unit)? = null
) : BaseDataBindBottomSheet<BottomSheetIngredientTypeBinding, IngredientTypeContract.Presenter>(),
    IngredientTypeContract.View {

    companion object {
        const val TAG = "IngredientTypeBottomSheet"
        private const val ARG_BUBBLE_TEA = "ARG_BUBBLE_TEA"
        fun newInstance(
            bubbleTea: BubbleTea,
            resultIngredientType: ((BubbleTea?) -> Unit)?
        ): IngredientTypeBottomSheet {
            val args = Bundle().apply {
                putParcelable(ARG_BUBBLE_TEA, bubbleTea)
            }
            val fragment = IngredientTypeBottomSheet(resultIngredientType)
            fragment.arguments = args
            return fragment
        }
    }

    private val bubbleTea by lazy {
        arguments?.getParcelable<BubbleTea>(ARG_BUBBLE_TEA)
    }

    override val layoutRes: Int = R.layout.bottom_sheet_ingredient_type


    override fun initView() {
        val behaviorBottoSheet =
            (this@IngredientTypeBottomSheet.dialog as BottomSheetDialog).behavior
        behaviorBottoSheet.state =
            BottomSheetBehavior.STATE_EXPANDED

        behaviorBottoSheet.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    behaviorBottoSheet.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        binding.apply {
            bubbleTea?.image?.let {
                imgBubbleTea.setImageResource(it)
            }
            Glide.with(imgBubbleTea.context)
                .load(bubbleTea?.id?.let {
                    Config.getImageUrl(it)
                })
                .placeholder(R.drawable.ic_null)
                .error(R.drawable.ic_null)
                .into(imgBubbleTea)
            cbGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.cbSizeM -> {
                        presenter?.setType(Constants.Type.SIZE_M)
                    }

                    R.id.cbSizeL -> {
                        presenter?.setType(Constants.Type.SIZE_L)
                    }
                }
            }
            when (bubbleTea?.ingredientType?.type) {
                Constants.Type.SIZE_M -> binding.cbSizeM.isChecked = true
                Constants.Type.SIZE_L -> binding.cbSizeL.isChecked = true
                else -> binding.cbSizeM.isChecked = true
            }
            btnSub.setOnClickListener {
                presenter?.subQuantity()
            }
            btnAdd.setOnClickListener {
                presenter?.addQuantity()
            }

            imgClose.setOnClickListener {
                dismiss()
            }

            btnContinue.setOnClickListener {
                presenter?.updateBubbleTea()
                resultIngredientType?.invoke(
                    presenter?.bubbleTea()?.value
                )
                dismiss()
            }
        }

    }

    override fun initData() {
        presenter = IngredientTypePresenter(this, bubbleTea).apply {
            binding.presenter = this
        }
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMessage(message: String) {}

}