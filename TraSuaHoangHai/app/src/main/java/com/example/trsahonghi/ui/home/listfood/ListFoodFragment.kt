package com.example.trsahonghi.ui.home.listfood

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentListFoodBinding
import com.example.trsahonghi.ui.home.listfood.adapter.ListFoodAdapter
import com.example.trsahonghi.ui.home.listfood.bottomsheet.IngredientTypeBottomSheet

class ListFoodFragment :
    BaseDataBindFragment<FragmentListFoodBinding, ListFoodContract.Presenter>(),
    ListFoodContract.View {
    companion object {
        fun newInstance() = ListFoodFragment()
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
    }

    override fun initData() {
        mPresenter = ListFoodPresenter(this).apply {
            getListFood()
        }
        mPresenter?.listFood()?.observe(this) {
            adapter.submitList(it)
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
}