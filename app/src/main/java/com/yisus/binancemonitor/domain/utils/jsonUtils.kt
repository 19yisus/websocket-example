package com.yisus.binancemonitor.domain.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object JsonUtils {
	
	fun getJson(obj: Any?) : String? {
		return obj?.let {
			GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create().toJson(obj)
		}
	}
	
	inline fun <reified T> fromJson(string: String?): T? {
		return Gson().fromJson(string, object: TypeToken<T>(){}.type)
	}
}