package com.example.croomduadesi.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbBuku (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val kategori : String,
    val judul : String,
    val pengarang : String,
    val penerbit : String,
    val jmlBuku : Int
)