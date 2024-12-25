package com.example.trsahonghi.ui.home.listfood

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.repository.food.FoodRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.base.LocalBroadcastReceiver
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.FragmentListFoodBinding
import com.example.trsahonghi.ui.home.listfood.adapter.ListFoodAdapter
import com.example.trsahonghi.ui.home.listfood.bottomsheet.IngredientTypeBottomSheet
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.StringUtils
import com.google.gson.reflect.TypeToken

class ListFoodFragment :
    BaseDataBindFragment<FragmentListFoodBinding, ListFoodContract.Presenter>(),
    ListFoodContract.View {
    companion object {
        fun newInstance() = ListFoodFragment()
    }

    private val listFoodReceiver: LocalBroadcastReceiver by lazy {
        object : LocalBroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val listFoodJson =
                    intent.getStringExtra(Constants.BundleConstants.LIST_FOOD_CART)
                listFoodJson?.let {
                    val type = object : TypeToken<MutableList<BubbleTea>>() {}.type
                    val listFood: MutableList<BubbleTea> =
                        StringUtils.stringToObject(listFoodJson, type)
                    mPresenter?.updateListFood(listFood)
                }
            }

        }
    }

    private val adapter: ListFoodAdapter by lazy {
        ListFoodAdapter(onItemClickListener = { item, pos ->
            showBottomSheet(item)
        },
            onAddClickListener = { item, pos ->
                mPresenter?.addAmountBubbleTea(item)
                adapter.notifyItemChanged(pos)
            },
            onSubClickListener = { item, pos ->
                mPresenter?.subAmountBubbleTea(item)
                adapter.notifyItemChanged(pos)
            }
        )
    }

    override fun getLayoutId(): Int = R.layout.fragment_list_food

    override fun initView() {
        mBinding?.apply {
            rvListFood.adapter = adapter
        }

        OrderEnabledLocalBroadcastManager.getInstance(getBaseActivity()).registerReceiver(
            listFoodReceiver,
            IntentFilter(Constants.Actions.NOTIFY_LIST_FOOD)
        )

    }

    override fun initData() {
        mPresenter = ListFoodPresenter(
            this,
            FoodRepositoryImpl()
        ).apply {
            getListFood()
        }

        mPresenter?.listFood()?.observe(this) { listFood ->
            listFood?.let {
                notifyFoodCardState(it)
                adapter.submitList(it)
            }
        }
    }

    private fun notifyFoodCardState(listFood: MutableList<BubbleTea>) {
        mPresenter?.getBroadcastAction(listFood)?.let { (action, filteredList) ->
            val broadcastIntent = Intent(action).apply {
                putExtra(
                    Constants.BundleConstants.LIST_FOOD,
                    StringUtils.objectToString(filteredList)
                )
            }
            context?.let {
                OrderEnabledLocalBroadcastManager.getInstance(it).sendBroadcast(broadcastIntent)
            }
        }
    }

    override fun showBottomSheet(bubbleTea: BubbleTea) {
        val ingredientTypeBottomSheet =
            IngredientTypeBottomSheet.newInstance(bubbleTea) { bubbleTea ->
                bubbleTea?.let {
                    mPresenter?.updateIngredientType(it)
                }
            }


        ingredientTypeBottomSheet.show(parentFragmentManager, IngredientTypeBottomSheet.TAG)
    }

    override fun onDestroy() {
        try {
            OrderEnabledLocalBroadcastManager.getInstance(getBaseActivity())
                .unregisterReceiver(listFoodReceiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }
}