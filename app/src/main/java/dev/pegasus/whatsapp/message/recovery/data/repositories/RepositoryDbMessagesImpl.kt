package dev.pegasus.whatsapp.message.recovery.data.repositories

import dev.pegasus.whatsapp.message.recovery.data.dataSources.DataSourceDbMessages
import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage
import dev.pegasus.whatsapp.message.recovery.domain.repositories.RepositoryDbMessages
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class RepositoryDbMessagesImpl(private val dataSourceDb: DataSourceDbMessages) : RepositoryDbMessages {

    override suspend fun getAllList(): Flow<List<ItemDbMessage>> = dataSourceDb.getAllList()
    override suspend fun insert(item: ItemDbMessage) = dataSourceDb.insert(item)
}