/*
package com.yisus.binancemonitor.presentation.ui.monitor

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yisus.binancemonitor.data.manager.WebSocketManager
import com.yisus.binancemonitor.presentation.ui.monitor.MonitorState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocket
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MonitorViewModel @Inject constructor(
): ViewModel() {

*/
/*	private val _state: MutableStateFlow<MonitorState> = MutableStateFlow(MonitorState())
	val state: StateFlow<MonitorState> = _state.asStateFlow()*//*


*/
/*	init {
*//*
*/
/*		webSocketManager.monitorBnbbtc.observeForever {data ->
			_state.update {
				it.copy(data_api = data)
			}
		}*//*
*/
/*


	}*//*

	
	fun sendMessage(message: String) {
		webSocketManager.sendMessage(message)
	}
	
	fun closeConnection(code: Int, reason: String) {
		webSocketManager.closeConnection(code, reason)
	}
}*/
