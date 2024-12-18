package com.example.trsahonghi.ui.home.bestselling

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentBestSellingBinding
import com.example.trsahonghi.ui.home.bestselling.adapter.BestSellingAdapter

class BestSellingFragment :
    BaseDataBindFragment<FragmentBestSellingBinding, BestSellingContract.Presenter>(),
    BestSellingContract.View {
    companion object {
        fun newInstance() = BestSellingFragment()
    }

    private val adapter by lazy {
        BestSellingAdapter(
            onClickListener = { item, prev ->

            })
    }

    override fun getLayoutId(): Int = R.layout.fragment_best_selling

    override fun initView() {
        mBinding?.apply {
            viewPager.adapter = adapter
            // tải 3 item
            viewPager.offscreenPageLimit = 3
            val pageMargin = resources.getDimensionPixelOffset(R.dimen.d_32).toFloat()
            val pageOffset = resources.getDimensionPixelOffset(R.dimen.d_32).toFloat()

            // sét hiển thị 2 item phụ
            viewPager.setPageTransformer { page, position ->
                val myOffset = position * -(2 * pageOffset + pageMargin)
                when {
                    position < -1 -> {
                        page.translationX = -myOffset
                        page.alpha = 1f
                    }

                    position <= 1 -> {
                        val scaleFactor = maxOf(0.85f, 1 - Math.abs(position))
                        page.translationX = myOffset
                        page.scaleY = scaleFactor
                        page.alpha = 1f
                    }

                    else -> {
                        page.translationX = -myOffset
                        page.alpha = 1f
                    }
                }
            }
            indicator.setViewPager2(viewPager)
        }

    }

    override fun initData() {
        mPresenter = BestSellingPresenter(this).apply {
            getListBestSelling()
        }

        mPresenter?.listBestSelling()?.observe(this) {
            adapter.setData(it)
        }
    }
}