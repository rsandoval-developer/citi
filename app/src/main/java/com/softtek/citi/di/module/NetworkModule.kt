package com.softtek.citi.di.module

import android.annotation.SuppressLint
import android.app.Application
import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.softtek.citi.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(applicationContext: Application): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .readTimeout(180, TimeUnit.SECONDS)
        .connectTimeout(180, TimeUnit.SECONDS)
        .build()

    @SuppressLint("SimpleDateFormat")
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.annotations.none { it is SerializedName }
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        })
        .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            formatter.parse(json.asString)
        })
        .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            formatter.parse(json.asString)
        })
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
