package com.yisus.binancemonitor.presentation.ui.notes

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange

@Composable
fun NotesScreen(
		viewModel: NotesViewModel = hiltViewModel()
) {
	
	val options = PusherOptions()
	options.setCluster("sa1");
	
	val pusher = Pusher("5f2b07fb40642688b466", options)
	
	pusher.connect(object : ConnectionEventListener {
		override fun onConnectionStateChange(change: ConnectionStateChange) {
			Log.i("Pusher", "State changed from ${change.previousState} to ${change.currentState}")
		}
		
		override fun onError(
			message: String,
			code: String,
			e: Exception
		) {
			Log.i("Pusher", "There was a problem connecting! code ($code), message ($message), exception($e)")
		}
	}, ConnectionState.ALL)
	
	val channel = pusher.subscribe("websockets-exmaple")
	channel.bind("my-event") { event ->
		Log.i("Pusher","Received event with data: $event")
	}
	
	NotesContent()
}

@Composable
fun NotesContent() {
	Text(text = "Hola")
}
