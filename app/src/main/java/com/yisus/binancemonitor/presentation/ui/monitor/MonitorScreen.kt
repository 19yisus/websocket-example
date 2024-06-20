package com.yisus.binancemonitor.presentation.ui.monitor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.yisus.binancemonitor.domain.models.Bnbbtc

@Composable
fun MonitorScreen(
	viewModel: MonitorViewModel = hiltViewModel()
) {
	
	MonitorContent(viewModel.state.collectAsState().value)
}

@Composable
fun MonitorContent(state: MonitorState){
	
	Scaffold {
		Column(modifier = Modifier
			.fillMaxSize()
			.padding(it),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(text = "Price: ${state.data.p}")
			Text(text = "Quantity: ${state.data.q}")
			Text(text = "Trade time: ${state.data.T}")
			Text(text = "Event type: ${state.data.e}")
			Text(text = "Symbol: ${state.data.s}")
			Text(text = "")
		}
	}
}