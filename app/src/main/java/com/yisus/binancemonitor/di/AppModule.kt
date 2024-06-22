package com.yisus.binancemonitor.di

import com.yisus.binancemonitor.data.common.consts.BASE_URL_SOCKET
import com.yisus.binancemonitor.data.common.consts.BASE_URL_SOCKET_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	
	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY
		
		return OkHttpClient.Builder()
			.addInterceptor(logging)
			.retryOnConnectionFailure(false)
			.connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(20, TimeUnit.SECONDS)
			.callTimeout(30, TimeUnit.SECONDS)
			.build()
	}
	
/*	@Provides
	@Singleton
	fun provideRequest(): Request {
		return Request.Builder()
			.url(BASE_URL_SOCKET_API)
			.addHeader("Authorization", "Bearer 9166838c232311f7da78ac0451b5db7578515445689d43bf6d95288eefc0e5d6")
			.build()
	}*/
}