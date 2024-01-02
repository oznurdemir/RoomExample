package com.example.roomexample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//Database object
//Database işlemlerini burada gerçekleştiriyoruz.

@Dao
interface UserDao {

    //Adapter için tüm kayıtlrı okuma fonksiyonu
    @Query(value = "SELECT * FROM users ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user : User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user : User)
}