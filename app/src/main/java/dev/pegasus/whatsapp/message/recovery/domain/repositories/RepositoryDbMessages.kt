package dev.pegasus.whatsapp.message.recovery.domain.repositories

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

interface RepositoryDbMessages {
    suspend fun getAllList(): Flow<List<ItemDbMessage>>
    suspend fun insert(item: ItemDbMessage)
}