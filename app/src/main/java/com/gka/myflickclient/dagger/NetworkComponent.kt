package com.gka.myflickclient.dagger

import com.gka.myflickclient.generals.App
import com.gka.myflickclient.network.NetworkService
import com.gka.myflickclient.ui.main.MainPresenter
import com.gka.myflickclient.ui.photoView.PhotoPresenter
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface NetworkComponent {

    fun inject(app: App)

    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun networkService(): NetworkService


    fun mainPresenter(): MainPresenter
    fun photoPresenter(): PhotoPresenter
}
