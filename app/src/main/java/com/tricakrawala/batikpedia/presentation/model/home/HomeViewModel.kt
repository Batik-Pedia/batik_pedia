package com.tricakrawala.batikpedia.presentation.model.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.restapibatikpedia.data.remote.response.BeritaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase : BatikPediaUseCase) : ViewModel() {

    private val _uiStateNusantara: MutableStateFlow<UiState<List<ProvinsiItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateNusantara: StateFlow<UiState<List<ProvinsiItem>>> get() = _uiStateNusantara


    private val _uiStateRekomendasi: MutableStateFlow<UiState<List<Rekomendasi>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateRekomendasi: StateFlow<UiState<List<Rekomendasi>>> get() = _uiStateRekomendasi

    private val _uiState = MutableStateFlow<UiState<List<BeritaItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<BeritaItem>>> get() = _uiState

    fun getAllNusantara() {
        viewModelScope.launch {
            useCase.getAllNusantara()
                .catch {
                    _uiStateNusantara.value = UiState.Error(it.message.toString())
                }
                .collect { nusantara ->
                    _uiStateNusantara.value = nusantara
                }
        }
    }


    fun getAllRekomendasi() {
        viewModelScope.launch {
            useCase.getAllRekomendasi()
                .catch {
                    _uiStateRekomendasi.value = UiState.Error(it.message.toString())
                }
                .collect { rekomendasi ->
                    _uiStateRekomendasi.value = UiState.Success(rekomendasi)
                }
        }
    }

    fun getAllBerita() {
        viewModelScope.launch {
            useCase.getAllBerita()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = it
                }
        }
    }


}