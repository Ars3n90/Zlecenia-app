package com.example.zlecenia.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zlecenia")
data class Zlecenie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val nazwa: String,
    val kwota: Double,
    val opis: String?
)
