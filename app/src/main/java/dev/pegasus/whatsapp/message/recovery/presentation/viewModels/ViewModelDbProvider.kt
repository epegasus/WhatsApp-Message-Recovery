package dev.pegasus.whatsapp.message.recovery.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import dev.pegasus.whatsapp.message.recovery.domain.useCases.UseCaseDbMessages

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class ViewModelDbProvider(private val useCase: UseCaseDbMessages) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ViewModelDbMessages::class.java) ->
                ViewModelDbMessages(useCase) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return create(modelClass)
    }
}
