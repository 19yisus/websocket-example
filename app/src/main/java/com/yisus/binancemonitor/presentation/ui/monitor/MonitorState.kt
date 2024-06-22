package com.yisus.binancemonitor.presentation.ui.monitor

import com.yisus.binancemonitor.domain.models.Bnbbtc

data class MonitorState(
	val data: Bnbbtc = Bnbbtc(),
	val data_api: String? = ""
)
