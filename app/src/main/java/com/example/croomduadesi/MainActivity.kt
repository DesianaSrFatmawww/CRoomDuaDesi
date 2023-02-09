package com.example.croomduadesi

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.croomduadesi.room.Constant
import com.example.croomduadesi.room.dbPerpustakaan
import com.example.croomduadesi.room.tbBuku
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { dbPerpustakaan(this) }
    lateinit var bukuAdapter: AdapterBuku

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpListener()
        setUpRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val perpustakaan = db.perpusDAO().tampil()
            Log.d("MainActivity","dbResponse:$perpustakaan")
            withContext(Dispatchers.Main){
                bukuAdapter.setData(perpustakaan)
            }
        }
    }

    fun setUpListener() {
        bt_input.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    private fun intentEdit(tbBukuId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("IntentId",tbBukuId)
                .putExtra("intent_type",intentType)
        )

    }
    fun setUpRecyclerView(){
        bukuAdapter = AdapterBuku(arrayListOf(), object : AdapterBuku.OnAdapterListener {
            override fun onClick(tbBook: tbBuku) {
                    intentEdit(tbBook.id,Constant.TYPE_READ)
            }
            override fun onUpdate(tbBook: tbBuku) {
                intentEdit(tbBook.id,Constant.TYPE_UPDATE)
           }

            override fun onDelete(tbBook: tbBuku) {
                hapusBuku(tbBook)
            }
        })

        rv_databuku.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bukuAdapter
        }
    }
    fun hapusBuku(tbBook : tbBuku){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin Hapus ${tbBook.judul}")
            setNegativeButton("Cancel") {dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Ya") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.perpusDAO().deletetbbuku(tbBook)
                    dialogInterface.dismiss()
                    loadData()
                    }
                }
            }
            alertDialog.show()
        }
    }