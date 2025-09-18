package com.semin.watlism.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.semin.watlism.data.database.dao.GenreDao
import com.semin.watlism.data.database.entity.GenreEntity

@Database(entities = [GenreEntity::class], version = 1, exportSchema = false)
abstract class GenreDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "genre_database"
        const val GENRE_TABLE_NAME = "genre"

        @Volatile
        private var instance: GenreDatabase? = null

        fun getInstance(context: Context): GenreDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    GenreDatabase::class.java,
                    DATABASE_NAME
                ).build().also { instance = it }
            }
        }
    }

    abstract fun getGenreDao(): GenreDao
}