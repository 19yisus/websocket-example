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
  val monitorBnbbtc = MutableLiveData<Bnbbtc>()
  private var webSocket: WebSocket
  
  init {
    val webSocketListener = object : WebSocketListener() {
      override fun onOpen(webSocket: WebSocket, response: Response) {
        // Handle onOpen event
        Log.i("WEBSOCKET OPEN", "WebSocket connection opened")
      }
      
      override fun onMessage(webSocket: WebSocket, text: String) {
        // Handle onMessage event
        monitorBnbbtc.postValue(JsonUtils.fromJson<Bnbbtc>(text))
      }
      
      override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        // Handle onClosing event
        Log.i("WEBSOCKET ON CLOSING", "WebSocket connection opened")
        Log.i("WEB SOCKET CLOSING", "Code: $code, Reason: $reason")
      }
      
      override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        // Handle onFailure event
        Log.i("WEBSOCKET ON FAILURE", "WebSocket connection opened")
        Log.i("WEB SOCKET FAILURE", t.message.toString())
      }
    }
	  
	  webSocket = okHttpClient.newWebSocket(request, webSocketListener)
  }

  fun sendMessage(message: String) {
    webSocket.send(message)
  }

  fun closeConnection(code: Int, reason: String) {
    webSocket.close(code, reason)
  }
}