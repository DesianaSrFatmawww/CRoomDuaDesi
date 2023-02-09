package com.example.croomduadesi.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbPeminjam (
    @PrimaryKey(autoGenerate = true)
    val nomorAnggota : Int,
    val namaSiswa : String,
    val tglPinjam : String,
    val tglKembali : String,
    val judulBuku : String,
    val jumlahPinjamBuku : Int
        )