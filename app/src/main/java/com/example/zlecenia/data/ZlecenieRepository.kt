package com.example.zlecenia.data

import android.app.Application
import androidx.lifecycle.LiveData

class ZlecenieRepository(application: Application) {
    private val dao: ZlecenieDao = AppDatabase.getDatabase(application).zlecenieDao()

    val wszystkieZlecenia: LiveData<List<Zlecenie>> = dao.getAll()

    suspend fun insert(zlecenie: Zlecenie) {
        dao.insert(zlecenie)
    }

    fun sumaMiesiaca(month: String): LiveData<Double?> {
        return dao.getSumForMonth(month)
    }
}
