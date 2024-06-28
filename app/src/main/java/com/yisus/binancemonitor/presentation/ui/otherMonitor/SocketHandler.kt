package com.yisus.binancemonitor.presentation.ui.otherMonitor

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

	lateinit var mSocket: Socket

	@Synchronized
	fun setSocket() {
		try {
// "http://10.0.2.2:3000" is the network your Android emulator must use to join the localhost network on your computer
// "http://localhost:3000/" will not work
// If you want to use your physical phone you could use the your ip address plus :3000
// This will allow your Android Emulator and physical device at your home to connect to the server
			mSocket = IO.socket("http://10.0.2.2:3010")
			mSocket.connect()
			mSocket.open()
			Log.d("Socket", "Socket set")
			if(mSocket.connected()) Log.i("Socket", "Connected 2")
			else Log.i("Socket", "Not connected 2")
		} catch (e: URISyntaxException) {
			Log.d("Socket", "Error: ${e.message}")
		}
	}

	@Synchronized
	fun getSocket(): Socket {
		return mSocket
	}

	@Synchronized
	fun closeConnection() {
		mSocket.disconnect()
	}
}