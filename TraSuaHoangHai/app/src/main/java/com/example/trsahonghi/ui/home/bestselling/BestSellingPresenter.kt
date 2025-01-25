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
            PopularItem(R.drawable.suachuavietquat, "Bán chạy nhất"),
            PopularItem(R.drawable.sodabacha, "Soda bạc hà"),
        )
    }
}