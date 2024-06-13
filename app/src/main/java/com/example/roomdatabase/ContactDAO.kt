package com.example.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {
    @Insert
    fun insartContact(contactEntity: ContactEntity)

    @Update
    fun updateContact(contactEntity: ContactEntity)

    @Delete
    fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts")
    fun getAllContact(): List<ContactEntity>
}