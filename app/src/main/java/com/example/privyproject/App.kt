package com.example.privyproject

import android.app.Application
import com.example.privyproject.di.interactorModules
import com.example.privyproject.di.networkModules
import com.example.privyproject.di.repositoryModules
import com.example.privyproject.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModules, viewModelModules, repositoryModules, interactorModules)
        }
    }
}