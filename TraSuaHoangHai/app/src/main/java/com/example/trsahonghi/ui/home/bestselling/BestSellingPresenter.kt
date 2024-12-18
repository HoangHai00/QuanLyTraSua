package com.example.trsahonghi.ui.home.bestselling

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.PopularItem
import com.example.trsahonghi.base.CommonPresenter

class BestSellingPresenter(
    private val view: BestSellingContract.View
) : CommonPresenter(view, view), BestSellingContract.Presenter {
    private var _listBestSelling = MutableLiveData<List<PopularItem>>()

    override fun listBestSelling() = _listBestSelling

    override fun getListBestSelling() {
        _listBestSelling.value = listOf(
            PopularItem(R.drawable.ic_best_sell_1, "Bán chạy nhất"),
            PopularItem(R.drawable.trasuachanchauthachzaizai, "Trà sữa trân châu thạch"),
            PopularItem(R.drawable.suachuavietquat, "Sữa chua việt quất"),
            PopularItem(R.drawable.ic_best_sell_1, "Bán chạy nhất"),
            PopularItem(R.drawable.trasuachanchauthachzaizai, "Trà sữa trân châu thạch"),
            PopularItem(R.drawable.suachuavietquat, "Sữa chua việt quất")
        )
    }
}