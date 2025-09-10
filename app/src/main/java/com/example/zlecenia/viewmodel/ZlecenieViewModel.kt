package com.example.zlecenia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zlecenia.data.Zlecenie
import com.example.zlecenia.data.ZlecenieRepository
import kotlinx.coroutines.launch

class ZlecenieViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = ZlecenieRepository(application)

    val lista: LiveData<List<Zlecenie>> = repo.wszystkieZlecenia

    private val _sumaMiesiaca = MutableLiveData<Double>()
    val sumaMiesiaca: LiveData<Double> get() = _sumaMiesiaca

    fun insert(z: Zlecenie) = viewModelScope.launch {
        repo.insert(z)
    }

    fun loadSumaMiesiaca(month: String) {
        repo.sumaMiesiaca(month).observeForever { suma ->
            _sumaMiesiaca.value = suma ?: 0.0
        }
    }
}
