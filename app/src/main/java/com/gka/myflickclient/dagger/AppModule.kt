package com.gka.myflickclient.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var app: Application) {

    @Provides
    @Singleton
    internal fun provideApp(): Application {
        return app
    }

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return app
    }
}