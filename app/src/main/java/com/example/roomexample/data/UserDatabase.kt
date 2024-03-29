package com.example.roomexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Bu class'ın amacı hem veritabanı hem de KisilerDao interface'ine erişimi sağlamak
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    //Database oluşturulduktan sonra buraya gelecek
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
                tempInstance = instance
                return instance
            }
        }
    }
}