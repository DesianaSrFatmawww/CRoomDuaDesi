package com.example.croomduadesi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.croomduadesi.room.tbBuku
import kotlinx.android.synthetic.main.activity_adapter_buku.view.*

class AdapterBuku (private val perpustakaan : ArrayList<tbBuku>,private val listener:OnAdapterListener) :
    RecyclerView.Adapter<AdapterBuku.BukuViewHolder>(){

    class BukuViewHolder (val view: View) :
        RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        return BukuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter_buku,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val perpus = perpustakaan[position]
        holder.view.tv_kategori.text = perpus.kategori
        holder.view.tv_judul.text = perpus.judul
        holder.view.tv_kategori.setOnClickListener{
            listener.onClick(perpus)
        }
        holder.view.ic_edit.setOnClickListener{
            listener.onUpdate(perpus)
        }
        holder.view.ic_delete.setOnClickListener{
            listener.onDelete(perpus)
        }

    }
    override fun getItemCount() = perpustakaan.size

    fun setData(list: List<tbBuku>){
        perpustakaan.clear()
        perpustakaan.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun onClick(tbBook : tbBuku)
        fun onUpdate(tbBook: tbBuku)
        fun onDelete(tbBook: tbBuku)
    }
}