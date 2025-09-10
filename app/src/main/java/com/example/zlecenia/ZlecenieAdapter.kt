package com.example.zlecenia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zlecenia.data.Zlecenie
import com.example.zlecenia.databinding.ItemZlecenieBinding
import java.text.SimpleDateFormat
import java.util.*

class ZlecenieAdapter : RecyclerView.Adapter<ZlecenieAdapter.ViewHolder>() {
    private var lista: List<Zlecenie> = emptyList()

    fun submitList(newList: List<Zlecenie>) {
        lista = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemZlecenieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemZlecenieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val z = lista[position]
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        holder.binding.dataText.text = df.format(Date(z.timestamp))
        holder.binding.nazwaText.text = z.nazwa
        holder.binding.kwotaText.text = "${z.kwota} zł"
        holder.binding.opisText.text = z.opis ?: ""
    }
}
