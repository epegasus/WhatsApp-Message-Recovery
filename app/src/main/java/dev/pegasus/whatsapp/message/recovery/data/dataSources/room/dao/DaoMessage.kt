package dev.pegasus.whatsapp.message.recovery.data.dataSources.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

@Dao
interface DaoMessage {

    @Query("SELECT * FROM table_message ORDER BY id DESC")
    fun getAllList(): Flow<List<ItemDbMessage>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(item: ItemDbMessage)
}