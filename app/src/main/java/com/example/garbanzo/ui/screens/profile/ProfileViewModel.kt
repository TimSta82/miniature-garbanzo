package com.example.garbanzo.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        stopLoading()
    }

    private fun stopLoading() {
        viewModelScope.launch {
            delay(3000L)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }
}
    data class UiState(
        val isLoading: Boolean = true
    )
