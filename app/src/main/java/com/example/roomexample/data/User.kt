package com.example.roomexample.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//Entitylerimizin okunacağı class (Bir tablomuzun olduğunu ve ve bunun fieldlarının class'ın propertylerinden oluştuğunu belirtiiyoruz,burada.)

@Entity("users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val surname : String,
    val mail : String):Serializable