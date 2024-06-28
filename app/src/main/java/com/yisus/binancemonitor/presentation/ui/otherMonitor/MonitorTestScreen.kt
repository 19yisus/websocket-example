package com.yisus.binancemonitor.presentation.ui.otherMonitor

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yisus.binancemonitor.presentation.theme.BinanceMonitorTheme
import kotlinx.coroutines.runBlocking

@Composable
fun MonitorTestScreen() {
	SocketHandler.setSocket()

	val (counter, setCounter) = rememberSaveable {
		mutableIntStateOf(0)
	}

	val mSocket = SocketHandler.getSocket()

	mSocket.on("chofer.viaje.pendiente") { args ->
		Log.d("Socket", args[0].toString())
	}

	MonitorTestContent(
		counter = counter,
		setCounter = {
			//setCounter( counter + 1)
			mSocket.emit("counter", counter + 1)
		}
	)
}

@Composable
fun MonitorTestContent(
	counter: Int,
	setCounter: () -> Unit
) {
	Scaffold {
		Box(modifier = Modifier
			.padding(it)
			.fillMaxSize()){
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(20.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				Text("Monitor Test Content")
				Text("Counter: $counter")
				Button(onClick = setCounter) {
					Text(text = "Increment Counter")
				}
			}
		}
	}
}

@Composable
@Preview
fun MonitorTestContentPreview(){
	BinanceMonitorTheme {
		MonitorTestContent(
			counter = 0,
			setCounter = {}
		)
	}
}