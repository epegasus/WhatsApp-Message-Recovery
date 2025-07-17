package dev.pegasus.whatsapp.message.recovery.data.dataSources

import dev.pegasus.whatsapp.message.recovery.data.dataSources.room.dao.DaoMessage
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

class DataSourceDbMessages(private val daoMessage: DaoMessage) {

    fun getAllList(): Flow<List<ItemDbMessage>> = daoMessage.getAllList()
    suspend fun insert(item: ItemDbMessage) = daoMessage.insert(item)
}