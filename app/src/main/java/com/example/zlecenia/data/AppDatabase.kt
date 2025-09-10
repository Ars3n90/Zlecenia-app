package com.example.zlecenia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Zlecenie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun zlecenieDao(): ZlecenieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "zlecenia_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
