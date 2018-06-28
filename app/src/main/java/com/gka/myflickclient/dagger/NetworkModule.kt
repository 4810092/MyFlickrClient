package com.gka.myflickclient.dagger

import android.app.Application
import com.gka.myflickclient.generals.const
import com.gka.myflickclient.network.NetworkInterceptor
import com.gka.myflickclient.network.NetworkService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideNetwork(retrofit: Retrofit): NetworkService {
        return retrofit.create<NetworkService>(NetworkService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpCache(app: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(NetworkInterceptor())
        builder.addInterceptor(interceptor)
        builder.cache(cache)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(const.baseUrl)
                .client(okHttpClient)
                .build()
    }
}