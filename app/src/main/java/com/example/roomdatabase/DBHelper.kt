package com.example.roomdatabase

import android.content.Context
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1)
abstract class DBHelper : RoomDatabase() {

    //DAO return
    abstract fun contactDao(): ContactDAO

    //db return
    companion object {

        val db: DBHelper? = null
//        @Volatile
//        private var INSTANCE: DBHelper? = null
//
//        fun checkDB(context: Context): DBHelper {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DBHelper::class.java,
//                    "contact.db"
//                ).allowMainThreadQueries().build()
//                INSTANCE = instance
//                instance
//            }
        //       }

        fun checkDB(context: Context): DBHelper {
            if (db != null) {
                return db
            } else {
                return initDBHelper(context)
            }
        }

        private fun initDBHelper(context: Context): DBHelper {
            return Room.databaseBuilder(context, DBHelper::class.java, "contact.db")
                .allowMainThreadQueries().build()
        }

    }
}
