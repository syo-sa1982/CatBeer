package com.syousa1982.catbeer.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.syousa1982.catbeer.BuildConfig
import com.syousa1982.catbeer.data.api.ChatBotApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {
    val instance = module {

        // region Data Layer API
        single { NetworkModule.getMoshi() }
        single { NetworkModule.getOkHttpClient(androidContext()) }
        single { NetworkModule.getChatBotApi(get(), get()) }
        // endregion
    }
}

object NetworkModule {

    fun getMoshi(): Moshi = Moshi.Builder().build()

    fun getChatBotApi(okHttpClient: OkHttpClient, moshi: Moshi) = createApiClient(okHttpClient, moshi)
        .create(ChatBotApi::class.java)

    /**
     * OkHttpClientを取得
     *
     * @param context Context
     * @return OkHttpClient
     */
    fun getOkHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    private fun createApiClient(okHttpClient: OkHttpClient, moshi: Moshi) = Retrofit
        .Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.API_ENDPOINT)
        .build()
}