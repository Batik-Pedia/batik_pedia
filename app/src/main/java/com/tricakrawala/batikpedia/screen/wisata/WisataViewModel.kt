package com.tricakrawala.batikpedia.screen.wisata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.BatikRepository
import com.tricakrawala.batikpedia.model.Wisata
import com.tricakrawala.batikpedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WisataViewModel(private val repository: BatikRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Wisata>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Wisata>>> get() = _uiState

    fun getAllWisata() {
        viewModelScope.launch {
            repository.getAllWisata()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uiState.value = UiState.Success(wisata)
                }
        }
    }

}