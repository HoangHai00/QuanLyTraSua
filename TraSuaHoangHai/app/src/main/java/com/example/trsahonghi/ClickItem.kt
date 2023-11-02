package com.example.trsahonghi

import android.icu.text.Transliterator.Position

interface ClickItem {
    fun onItemClickTang(position: Int)
    fun onItemClickGiam(position: Int)
}