package com.example.croomduadesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.croomduadesi.room.Constant
import com.example.croomduadesi.room.dbPerpustakaan
import com.example.croomduadesi.room.tbBuku
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { dbPerpustakaan(this) }
    private var tbBukuId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        tombolPerintah()
        tbBukuId = intent.getIntExtra("IntentId",tbBukuId)
        Toast.makeText(this,tbBukuId.toString(),Toast.LENGTH_SHORT).show()

    }

    fun setupView(){

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                btn_update.visibility = View.GONE

            }
            Constant.TYPE_READ -> {
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
                et_nomorAnggota.visibility = View.GONE
                tampilSemua()

            }
            Constant.TYPE_UPDATE -> {
                btn_save.visibility = View.GONE
                et_nomorAnggota.visibility = View.GONE
                tampilSemua()

            }
        }
    }

    fun tombolPerintah() {
        btn_save.setOnClickListener{
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
        btn_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.perpusDAO().updatetbbuku(
                    tbBuku(
                        tbBukuId,
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

    fun tampilSemua(){
        tbBukuId = intent.getIntExtra("IntentId",0)
        CoroutineScope(Dispatchers.IO).launch {
            val Perpus = db.perpusDAO().tampilsemua(tbBukuId)[0]
            val dataId : String = Perpus.id.toString()
            val datajml : String = Perpus.jmlBuku.toString()
            et_nomorAnggota.setText(dataId)
            et_namaSiswa.setText(Perpus.kategori)
            et_tglPinjam.setText(Perpus.judul)
            et_tglKembali.setText(Perpus.pengarang)
            et_judulBuku.setText(Perpus.penerbit)
            et_jmlPinjam.setText(datajml)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}