package com.example.trsahonghi.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<VDB : ViewDataBinding>(val viewDataBinding: VDB) :
    RecyclerView.ViewHolder(viewDataBinding.root)