package com.example.trsahonghi.ui.home.homegroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.CommonPresenter

class HomeGroupPresenter(
    private val view: HomeGroupContract.View
):CommonPresenter(view,view), HomeGroupContract.Presenter {
    private val listFragment = MutableLiveData<List<Fragment>>()

    private fun getListFragmentHome(){

    }

}