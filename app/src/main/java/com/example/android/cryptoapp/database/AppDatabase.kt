package com.example.android.cryptoapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "main.db"
        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            db?.let { return it }
            val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            db = instance
            return instance
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}