package dev.pegasus.whatsapp.message.recovery.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage
import dev.pegasus.whatsapp.message.recovery.domain.useCases.UseCaseDbMessages
import kotlinx.coroutines.launch

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class ViewModelDbMessages(private val useCase: UseCaseDbMessages) : ViewModel() {

    private val _listLiveData = MutableLiveData<List<ItemDbMessage>>()
    val listLiveData: LiveData<List<ItemDbMessage>> = _listLiveData

    init {
        fetchList()
    }

    private fun fetchList() = viewModelScope.launch {
        useCase
            .getAllList()
            .collect {
                _listLiveData.value = it
            }
    }
}