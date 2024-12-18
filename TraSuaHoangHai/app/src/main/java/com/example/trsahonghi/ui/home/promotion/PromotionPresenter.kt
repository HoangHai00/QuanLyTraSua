package com.example.trsahonghi.ui.home.promotion

import com.example.trsahonghi.base.CommonPresenter

class PromotionPresenter(
    private val view: PromotionContract.View
) : CommonPresenter(view, view), PromotionContract.Presenter {
}