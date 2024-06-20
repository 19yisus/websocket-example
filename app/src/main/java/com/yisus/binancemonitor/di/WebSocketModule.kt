package com.yisus.binancemonitor.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

  @Provides
  @Singleton
  fun provideWebSocket(okHttpClient: OkHttpClient, request: Request): WebSocket {
    val webSocketListener = object : WebSocketListener() {
      override fun onOpen(webSocket: WebSocket, response: Response) {
        // Handle onOpen event
				Log.i("WEBSOCKET OPEN", "WebSocket connection opened")
      }

      override fun onMessage(webSocket: WebSocket, text: String) {
        // Handle onMessage event
	      Log.i("WEBSOCKET ON MESSAGE", "WebSocket Message")
	      Log.i("WEB SOCKET MESSAGE", text)
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

    return okHttpClient.newWebSocket(request, webSocketListener)
  }
}