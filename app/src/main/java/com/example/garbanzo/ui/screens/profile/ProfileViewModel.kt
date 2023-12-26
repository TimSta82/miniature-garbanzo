package com.example.garbanzo.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbanzo.data.websocket.FinanceWebSocketClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URI

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var wsClient : FinanceWebSocketClient? = null

    init {
        stopLoading()
        establishWebSocket()
    }

    private fun establishWebSocket() {
        val serverUri = URI("wss://ws.finnhub.io?token=cm5k74pr01qjc6l4h020cm5k74pr01qjc6l4h02g")
        wsClient = FinanceWebSocketClient(
            serverUri = serverUri,
            messageListener = { message ->
                val _item = HashMap<String, Any>()
                _item["message"] = message
                val m = _uiState.value.messages
                m.add(_item)
                _uiState.value = _uiState.value.copy(messages = m)
            },
            onError = {
                _uiState.value = _uiState.value.copy(errorMessage = it?.message ?: "Iwas falsch")
            }
        )
        wsClient?.connect()
    }

    private fun stopLoading() {
        viewModelScope.launch {
            delay(1000L)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    fun closeWebSocketConnection() {
        wsClient?.close()
    }
}

data class UiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val messages: ArrayList<HashMap<String, Any>> = ArrayList()
)
