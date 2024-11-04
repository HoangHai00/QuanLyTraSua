package com.example.trsahonghi.base


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob



open class CommonPresenter @JvmOverloads constructor(
    private val appBehaviorOnServiceError: AppBehaviorOnServiceError? = null,
    private val baseView: BaseView? = null,
    protected val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
) : BasePresenter {
    override fun subscribe() {

    }

    override fun unSubscribe() {

    }


}