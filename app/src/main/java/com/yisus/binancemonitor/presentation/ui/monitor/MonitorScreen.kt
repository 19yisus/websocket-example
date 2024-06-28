package com.yisus.binancemonitor.presentation.ui.monitor

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit


@Composable
fun MonitorScreen(
) {

	val subscribeMessage = "{\"channel\":\"chofer.viajes.disponibles\",\"event\":\"ViajeCreadoEvent\"}"
	val token = "1ddeb4ceb22fa36c6abf0f7d7bc0efb2426249bbad811d041e6d4de6e8ccdf4a"

/*	val request = Request.Builder()
		.url("wss://devgg.duckdns.org")
		.build()


	LaunchedEffect(Unit) {
		try {
			val options = IO.Options().apply {
				this.query = "protocol=7&client=js&version=7.0.3&flash=false"
				this.extraHeaders = mapOf("Authorization" to listOf("Bearer $token"))
			}
			//val mSocket = IO.socket("wss://devgg.duckdns.org", options)
			val mSocket = IO.socket(request.url.toUri(), options)

			mSocket.connect()

			mSocket.on(Socket.EVENT_CONNECT) {
				Log.e("AAAAAAAAAAAAAAAAAA", "Connected to WebSocket!")
			}

			mSocket.on("pusher:connection_established") { args ->
				Log.e("AAAAAAAAAAAAAAAAAA", args[0].toString())
			}

			mSocket.on("ViajeCreadoEvent") { args ->
				Log.e("AAAAAAAAAAAAAAAAAA", args[0].toString())
			}

			mSocket.on(Socket.EVENT_CONNECT_ERROR) { args ->
				Log.e("WEBSOCKET ERROR", "Error connecting to WebSocket: ${args[0].toString()}")
			}

			mSocket.on(Socket.EVENT_DISCONNECT) {
				Log.e("WEBSOCKET ERROR", "Disconnected from WebSocket!")
			}

			mSocket.emit("subscribe", "chofer.viajes.disponibles")
		} catch (e: URISyntaxException) {
			Log.e("WEBSOCKET ERROR", e.message.toString())
		}
	}*/

	val client = OkHttpClient.Builder()
		.readTimeout(0, TimeUnit.MILLISECONDS)
		.addNetworkInterceptor(object : Interceptor {
			override fun intercept(chain: Interceptor.Chain): Response {
				val original = chain.request()
				val request = original.newBuilder()
					.header("Authorization", "Bearer $token")
					.method(original.method, original.body)
					.build()
				return chain.proceed(request)
			}
		})
		.dispatcher(okhttp3.Dispatcher().apply {
			maxRequests = 1
		})
		.authenticator(object : okhttp3.Authenticator {
			override fun authenticate(route: okhttp3.Route?, response: Response): Request {
				return response.request.newBuilder()
					.header("Authorization", "Bearer $token")
					.build()
			}
		})
		.build()
	//?protocol=7&client=js&version=7.0.3&flash=false
	val channel = "chofer.viajes.disponibles"
	val request = Request.Builder()
		.url("wss://devgg.duckdns.org/$channel/api_key=$token")
		.header("Authorization", "Bearer $token")
		.build()

	val socketListener = object: WebSocketListener(){
		override fun onOpen(webSocket: WebSocket, response: Response) {
			super.onOpen(webSocket, response)
			Log.d("WEBSOCKET OPEN", "WebSocket connection opened")
		}

		override fun onMessage(webSocket: WebSocket, text: String) {
			super.onMessage(webSocket, text)
			Log.d("WEBSOCKET MESSAGE", text)

			// Parse the message
/*			val json = JSONObject(text)
			val event = json.getString("event")
			if (event == "pusher:connection_established") {
				val dataString = json.getString("data")
				val data = JSONObject(dataString)
				val socketId = data.getString("socket_id")

				Log.d("SOCKET ID", "Socket ID: $socketId")

				// Subscribe to the channel after connection is established
				val subscribeMessage = """
                {
                    "event": "pusher:subscribe",
                    "data": {
                        "channel": "chofer.viajes.disponibles"
                    }
                }
            """.trimIndent()
				webSocket.send(subscribeMessage)
				Log.e("AAAAAAAAAAAAAAAAAA", event)

			} else if (event == "pusher_internal:subscription_succeeded") {
				Log.d("SUBSCRIPTION", "Subscription to channel succeeded")
				Log.e("AAAAAAAAAAAAAAAAAA", event)
			} else if (event == "ViajeCreadoEvent") {
				// Handle the custom event
				Log.d("EVENT RECEIVED", "Received event data: ${json.getJSONObject("data")}")
				Log.e("AAAAAAAAAAAAAAAAAA", event)
			}else{
				Log.d("AAAAAAAAAAAA", "Received event data: ${json.getJSONObject("data")}")
				Log.e("AAAAAAAAAAAAAAAAAA", event)
			}*/
		}

		override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
			super.onClosing(webSocket, code, reason)
			Log.d("WEBSOCKET CLOSED", "Code: $code, Reason: $reason")
		}

		override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
			super.onFailure(webSocket, t, response)
			Log.d("WEBSOCKET ERROR", t.message.toString())
		}
	}

	LaunchedEffect(Unit) {
		client.newWebSocket(request, socketListener)
	}


	/*LaunchedEffect(Unit) {
		val client = HttpClient(CIO) {
			install(WebSockets) {
				pingInterval = 20_000
			}
		}

		client.webSocket(
			method = HttpMethod.Get,
			host = "devgg.duckdns.org",
			request = {
				this.headers.append("Authorization", "Bearer 9166838c232311f7da78ac0451b5db7578515445689d43bf6d95288eefc0e5d6")
			}
		) {
			this.send(subscribeMessage)
			try{
				for (message in incoming) {
					when (message) {
						is Frame.Text -> {
							val text = message.readText()
							Log.e("WEBSOCKET MESSAGE", text)
						}
						is Frame.Binary -> Log.e("WEBSOCKET MESSAGE", "NADA BINARY")
						is Frame.Close -> Log.e("WEBSOCKET MESSAGE", "NADA CLOSE")
						is Frame.Ping -> Log.e("WEBSOCKET MESSAGE", "NADA PING")
						is Frame.Pong -> Log.e("WEBSOCKET MESSAGE", "NADA PONG")
					}
				}
			}catch(e: Exception){
				Log.e("WEBSOCKET ERRORRRRRR", e.message.toString())
			}
			// Handle WebSocket session here
		}
	}*/

	MonitorContent()
}

@Composable
fun MonitorContent(){
	
	Scaffold {
		Column(modifier = Modifier
			.fillMaxSize()
			.padding(it),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
/*			Text(text = "Price: ${state.data.p}")
			Text(text = "Quantity: ${state.data.q}")
			Text(text = "Trade time: ${state.data.T}")
			Text(text = "Event type: ${state.data.e}")
			Text(text = "Symbol: ${state.data.s}")*/
		}
	}
}