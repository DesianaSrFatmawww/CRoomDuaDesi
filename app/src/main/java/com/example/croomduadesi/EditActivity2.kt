package com.example.croomduadesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.croomduadesi.room.dbPerpustakaan
import com.example.croomduadesi.room.tbBuku
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity2 : AppCompatActivity() {

    val db by lazy { dbPerpustakaan(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        SimpanData()
    }
        fun SimpanData() {
            btn_save.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.perpusDAO().addtbbuku(
                        tbBuku(
                            et_nomorAnggota.text.toString().toInt(),
                            et_namaSiswa.text.toString(),
                            et_tglPinjam.text.toString(),
                            et_tglKembali.text.toString(),
                            et_judulBuku.text.toString(),
                            et_jmlPinjam.text.toString().toInt()
                        )
                    )
                    finish()
                }
            }
        }
}