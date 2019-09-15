package com.syousa1982.catbeer

import android.app.Application
import com.syousa1982.catbeer.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(AppModule.instance)
        }
    }
}