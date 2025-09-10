package com.example.zlecenia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zlecenia.data.Zlecenie
import com.example.zlecenia.databinding.ActivityMainBinding
import com.example.zlecenia.viewmodel.ZlecenieViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ZlecenieViewModel by viewModels()
    private val adapter = ZlecenieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.lista.observe(this) { lista ->
            adapter.submitList(lista)
        }

        viewModel.sumaMiesiaca.observe(this) { suma ->
            binding.sumaText.text = "Suma miesiąca: ${"%.2f".format(suma)} zł"
        }

        binding.dodajButton.setOnClickListener {
            val nazwa = binding.nazwaInput.text.toString()
            val kwota = binding.kwotaInput.text.toString().toDoubleOrNull() ?: 0.0
            val opis = binding.opisInput.text.toString()
            val now = System.currentTimeMillis()

            val z = Zlecenie(
                timestamp = now,
                nazwa = nazwa,
                kwota = kwota,
                opis = opis
            )
            viewModel.insert(z)
            viewModel.loadSumaMiesiaca(getCurrentMonth())

            binding.nazwaInput.text.clear()
            binding.kwotaInput.text.clear()
            binding.opisInput.text.clear()
        }

        viewModel.loadSumaMiesiaca(getCurrentMonth())
    }

    private fun getCurrentMonth(): String {
        val cal = Calendar.getInstance()
        val y = cal.get(Calendar.YEAR)
        val m = cal.get(Calendar.MONTH) + 1
        return String.format("%04d-%02d", y, m)
    }
}
