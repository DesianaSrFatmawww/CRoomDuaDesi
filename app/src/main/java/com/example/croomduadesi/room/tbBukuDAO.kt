package com.example.croomduadesi.room

import androidx.room.*
@Dao
interface tbBukuDAO {

    @Insert
    fun addtbbuku(tbBook : tbBuku)

    @Update
    fun updatetbbuku(tbBook: tbBuku)

    @Delete
    fun deletetbbuku(tbBook: tbBuku)

    @Query("SELECT * FROM tbBuku")
    fun tampil():List<tbBuku>

    @Query("SELECT * FROM tbBuku WHERE id =:buku_id")
    fun tampilsemua(buku_id: Int):List<tbBuku>

}