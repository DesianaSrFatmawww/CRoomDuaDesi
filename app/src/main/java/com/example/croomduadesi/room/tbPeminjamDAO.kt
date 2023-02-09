package com.example.croomduadesi.room

import androidx.room.*
@Dao
interface tbPeminjamDAO {

    @Insert
    fun addTbPinjam(tbpinjam: tbPeminjam)

    @Update
    fun updateTbPinjam(tbpinjam: tbPeminjam)

    @Delete
    fun deleteTbPinjam(tbpinjam: tbPeminjam)

    @Query("SELECT * FROM tbPeminjam")
    fun getTbPinjam(): List<tbPeminjam>

    @Query("SELECT * FROM tbBuku WHERE id =:tbPinjam_id")
    fun tampilId(tbPinjam_id: Int):List<tbBuku>
}