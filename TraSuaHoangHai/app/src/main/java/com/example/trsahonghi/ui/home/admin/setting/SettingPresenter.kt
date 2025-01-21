package com.example.trsahonghi.ui.home.admin.setting

import com.example.trsahonghi.base.CommonPresenter

class SettingPresenter (
    private val view: SettingContract.View
):CommonPresenter(view,view),SettingContract.Presenter{
}