package com.yisus.binancemonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yisus.binancemonitor.presentation.theme.BinanceMonitorTheme
import com.yisus.binancemonitor.presentation.ui.otherMonitor.MonitorTestScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BinanceMonitorTheme {
				MonitorTestScreen()
			}
		}
	}
}