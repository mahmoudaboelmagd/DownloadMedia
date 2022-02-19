package com.downloadmedia.di

import com.downloadmedia.apis.ServicesInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

  @Singleton
  @Provides
  fun createRetrofitClient(): ServicesInterface {
    val httpClient = OkHttpClient.Builder()
      .addInterceptor { chain ->
        val ongoing = chain.request().newBuilder()
        ongoing.addHeader("Accept", "application/json")
        ongoing.addHeader("Content-Type", "application/json")
        chain.proceed(ongoing.build())
      }
      .build()

    val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://nagwa.free.beeceptor.com/")
      .client(httpClient)
      .build()

    return retrofit.create(ServicesInterface::class.java)
  }


}