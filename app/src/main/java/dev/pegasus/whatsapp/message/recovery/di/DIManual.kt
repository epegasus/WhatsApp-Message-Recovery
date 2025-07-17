package dev.pegasus.whatsapp.message.recovery.di

import android.content.Context
import dev.pegasus.whatsapp.message.recovery.data.dataSources.DataSourceDbMessages
import dev.pegasus.whatsapp.message.recovery.data.dataSources.room.DatabaseInstance
import dev.pegasus.whatsapp.message.recovery.data.repositories.RepositoryDbMessagesImpl
import dev.pegasus.whatsapp.message.recovery.domain.useCases.UseCaseDbMessages

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class DIManual(context: Context) {

    val useCase by lazy {
        val dao = DatabaseInstance.Companion.getDatabase(context).daoMessage()
        val dataSourceLocal = DataSourceDbMessages(dao)
        val repository = RepositoryDbMessagesImpl(dataSourceLocal)
        UseCaseDbMessages(repository)
    }
}