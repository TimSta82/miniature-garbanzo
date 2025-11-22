package com.example.garbanzo.data.websocket

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class FinanceWebSocketClient(serverUri: URI, private val messageListener: (String) -> Unit, private val onError: (Exception?) -> Unit): WebSocketClient(serverUri) {
    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.d("FinanceWebSocketClient", "onOpen: ${handshakedata?.httpStatusMessage}")
        // When WebSocket connection opened
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.d("FinanceWebSocketClient", "onClose: code = ${code}, reason = $reason, remote = $remote")
        // When WebSocket connection closed
    }

    override fun onMessage(message: String?) {
        // When Receive a message
        Log.d("FinanceWebSocketClient", "onMessage: $message")

        messageListener.invoke(message ?: "")
    }

    override fun onError(ex: Exception?) {
        // When An error occurred
        Log.d("FinanceWebSocketClient", "onError: ${ex?.message}")

        onError.invoke(ex)
    }

    fun sendMessage(message: String) {
        send(message)
    }
}
