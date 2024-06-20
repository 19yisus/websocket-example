package com.yisus.binancemonitor.domain.models

data class Bnbbtc(
	val e: String = "", // Event type
	val E: Long = 0, // Event time
	val s: String = "", // Symbol
	val a: Long = 0, // Aggregate trade ID
	val p: String = "", // Price
	val q: String = "", // Quantity
	val f: Long = 0, // First trade ID
	val l: Long = 0, // Last trade ID
	val T: Long = 0, // Trade time
	val isMarketMaker: Boolean = false, // Is the buyer the market maker?
	val M: Boolean = false// Ignore
)
