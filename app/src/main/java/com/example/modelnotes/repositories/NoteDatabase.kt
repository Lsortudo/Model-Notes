package com.example.modelnotes.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.modelnotes.model.Note
import com.example.modelnotes.utils.DATABASE_NAME


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao
    // SINGLETON PATTERN ( Apenas um Objeto desta classe pode ser criado e se o Objeto já estiver criado retorna referencia para o mesmo Objeto )
    companion object {

        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context: Context) : NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    /* context = */ context.applicationContext,
                    /* klass = */ NoteDatabase::class.java,
                    /* name = */ DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}