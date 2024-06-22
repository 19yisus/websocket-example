/*
package com.yisus.binancemonitor.data.manager

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yisus.binancemonitor.domain.models.Bnbbtc
import com.yisus.binancemonitor.domain.utils.JsonUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class WebSocketManager @Inject constructor(okHttpClient: OkHttpClient, request: Request) {
  val monitorBnbbtc = MutableLiveData<String>()
  private var webSocket: WebSocket
  
  init {
    val webSocketListener = object : WebSocketListener() {
      override fun onOpen(webSocket: WebSocket, response: Response) {
        // Handle onOpen event
        val subscribeMessage = "{\"channel\":\"chofer.viajes.disponibles\",\"event\":\"ViajeCreadoEvent\"}"

        Log.i("WEBSOCKET OPEN", "WebSocket connection opened")
        Log.i("WEBSOCKET OPEN", subscribeMessage)
        Log.d("RESPONSE WEBSOCKET", response.message)
        Log.d("RESPONSE WEBSOCKET BODY", response.body.toString())
        val d = webSocket.send(subscribeMessage)
        Log.d("WEBSOCKET", d.toString())

      }
      
      override fun onMessage(webSocket: WebSocket, text: String) {
        // Handle onMessage event
        Log.i("WEBSOCKET ON MESSAGE", "WebSocket message received")

        monitorBnbbtc.postValue(text)
      }
      
      override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        // Handle onClosing event
        Log.i("WEBSOCKET ON CLOSING", "WebSocket connection closed")
        Log.i("WEB SOCKET CLOSING", "Code: $code, Reason: $reason")
      }
      
      override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        // Handle onFailure event
        Log.i("WEBSOCKET ON FAILURE", "WebSocket connection fail")
        Log.i("WEB SOCKET FAILURE", t.message.toString())
        Log.i("WEB SOCKET FAILURE", response?.message.toString())
      }
    }
	  
	  webSocket = okHttpClient.newWebSocket(request, webSocketListener)
    webSocket.send("{\"channel\":\"chofer.viajes.disponibles\",\"event\":\"ViajeCreadoEvent\"}")
  }

  fun sendMessage(message: String) {
    webSocket.send(message)
  }

  fun closeConnection(code: Int, reason: String) {
    webSocket.close(code, reason)
  }
}*/
