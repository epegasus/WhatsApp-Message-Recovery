package dev.pegasus.whatsapp.message.recovery.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.pegasus.whatsapp.message.recovery.domain.useCases.UseCaseDbMessages

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class ViewModelDbProvider(private val useCaseDbMessages: UseCaseDbMessages) : ViewModelProvider.Factory {

    @Suppress("CAST_NEVER_SUCCEEDS")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDbProvider::class.java)) {
            return ViewModelDbProvider(useCaseDbMessages) as T
        }
        return super.create(modelClass)
    }
}