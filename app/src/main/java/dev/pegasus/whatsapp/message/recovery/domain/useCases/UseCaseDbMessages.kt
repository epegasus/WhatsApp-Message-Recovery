package dev.pegasus.whatsapp.message.recovery.domain.useCases

import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage
import dev.pegasus.whatsapp.message.recovery.data.repositories.RepositoryDbMessagesImpl
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class UseCaseDbMessages(private val repositoryDbMessagesImpl: RepositoryDbMessagesImpl) {

    suspend fun getAllList(): Flow<List<ItemDbMessage>> = repositoryDbMessagesImpl.getAllList()
    suspend fun insert(item: ItemDbMessage) = repositoryDbMessagesImpl.insert(item)
}