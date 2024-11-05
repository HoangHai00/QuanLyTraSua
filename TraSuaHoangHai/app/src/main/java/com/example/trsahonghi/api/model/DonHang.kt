package com.example.trsahonghi.api.model

data class DonHang(
    var dsMon: List<TraSua>,
    var id: String? = null,
    var name: String = "",
    var ngayDat: String = "",
    var sdt: String = "",
    var trangThai: String = "",
    var tongTien: String = ""
) {

}