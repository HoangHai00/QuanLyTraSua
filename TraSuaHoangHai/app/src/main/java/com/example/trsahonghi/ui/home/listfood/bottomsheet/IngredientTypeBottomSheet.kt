package com.example.trsahonghi.ui.home.listfood.bottomsheet

import android.view.View
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.IngredientType
import com.example.trsahonghi.databinding.BottomSheetIngredientTypeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lpb.lienviet24h.ui.basedatabind.BaseDataBindBottomSheet

class IngredientTypeBottomSheet(
    private val resultIngredientType: ((IngredientType?) -> Unit)? = null
) :
    BaseDataBindBottomSheet<BottomSheetIngredientTypeBinding, IngredientTypeContract.Presenter>(),
    IngredientTypeContract.View {
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
            imgClose.setOnClickListener {
                dismiss()
            }
            btnContinue.setOnClickListener {
                resultIngredientType?.invoke(IngredientType("", ""))
                dismiss()
            }
        }

    }

    override fun initData() {

    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMessage(message: String) {}

}