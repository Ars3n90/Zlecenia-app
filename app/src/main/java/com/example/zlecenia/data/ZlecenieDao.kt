package com.example.zlecenia.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ZlecenieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zlecenie: Zlecenie)

    @Query("SELECT * FROM zlecenia ORDER BY timestamp DESC")
    fun getAll(): LiveData<List<Zlecenie>>

    @Query("""
        SELECT SUM(kwota) FROM zlecenia 
        WHERE strftime('%Y-%m', timestamp / 1000, 'unixepoch') = :month
    """)
    fun getSumForMonth(month: String): LiveData<Double?>
}
