package com.tricakrawala.batikpedia.presentation.model.wisata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.model.Wisata
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WisataViewModel @Inject constructor(private val useCase : BatikPediaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<WisataItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<WisataItem>>> get() = _uiState

    private val _uiStateWisataById: MutableStateFlow<UiState<Wisata>> = MutableStateFlow(
        UiState.Loading)
    val uiStateWisataById: StateFlow<UiState<Wisata>> get() = _uiStateWisataById

    fun getAllWisata() {
        viewModelScope.launch {
            useCase.getAllWisata()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uiState.value = wisata
                }
        }
    }


    fun getWisataById(idWisata : Long){
        viewModelScope.launch {
            _uiStateWisataById.value = UiState.Loading
            _uiStateWisataById.value = UiState.Success(useCase.getWisataById(idWisata))
        }
    }

}