package com.example.trsahonghi.model

import android.media.Image

class TraSua(
    var image:Int = 0,
    var tenMon: String = "",
    var giaMon: Int = 0,
    var soLuong: Int=0,
    var loatTP: Int=0
) {
    constructor() : this(0, "", 0, 0, 0)
}