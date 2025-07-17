package dev.pegasus.whatsapp.message.recovery.data.dataSources.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.pegasus.whatsapp.message.recovery.data.dataSources.room.dao.DaoMessage
import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

@Database(entities = [ItemDbMessage::class], version = 1, exportSchema = false)
abstract class DatabaseInstance : RoomDatabase() {

    abstract fun daoMessage(): DaoMessage

    companion object {

        @Volatile
        private var INSTANCE: DatabaseInstance? = null
        fun getDatabase(context: Context): DatabaseInstance {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context.applicationContext, DatabaseInstance::class.java, "db_messages")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}