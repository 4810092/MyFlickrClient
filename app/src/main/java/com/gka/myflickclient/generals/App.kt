package com.gka.myflickclient.generals

import android.app.Application
import com.gka.myflickclient.dagger.AppModule
import com.gka.myflickclient.dagger.NetworkComponent

val prefs: Prefs by lazy {
    App.prefs
}

val const: C by lazy {
    App.const
}

class App : Application() {
    companion object {
        lateinit var prefs: Prefs
        lateinit var const: C
        lateinit var networkComponent: NetworkComponent
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
        const = C()

        val appModule = AppModule(this)

        networkComponent = com.gka.myflickclient.dagger.DaggerNetworkComponent
                .builder()
                .appModule(appModule)
                .build()
    }

}
