package com.example.trsahonghi.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.trsahonghi.R


@BindingAdapter("isSelectedBackground")
fun setSelectedBackground(view: TextView, isSelected: Boolean) {
    view.setBackgroundResource(
        if (isSelected) R.drawable.bg_border_corner_lucky_money_view
        else R.drawable.bg_gray_no_radius
    )
}
