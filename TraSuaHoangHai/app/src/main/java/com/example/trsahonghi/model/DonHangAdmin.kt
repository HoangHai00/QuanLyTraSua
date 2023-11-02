package com.example.trsahonghi.model

class DonHangAdmin(
    var dsMon: List<TraSua>,
    var id: String? = null,
    var name: String = "",
    var ngayDat: String = "",
    var sdt: String = "",
    var trangThai: String = "",
    var tongTien: String = ""
) {
    // Hàm tạo không tham số
    constructor() : this(ArrayList(), "", "", "", "", "","")
}
