package com.example.pab_praktikum01

object DataHolder {
    var nama: String = ""
    var gender: String = ""
    var kota: String = ""
    var isSubmitted: Boolean = false

    fun getFormattedData(): String {
        return if (isSubmitted) {
            "Nama: $nama\nJenis Kelamin: $gender\nKota Asal: $kota"
        } else {
            "Belum Ada Data Pendaftaran.\nSilakan isi form pendaftaran terlebih dahulu."
        }
    }
}
