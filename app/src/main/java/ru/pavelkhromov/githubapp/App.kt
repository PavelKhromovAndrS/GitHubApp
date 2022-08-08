package ru.pavelkhromov.githubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.pavelkhromov.githubapp.di.AppComponent
import ru.pavelkhromov.githubapp.di.AppModule
import ru.pavelkhromov.githubapp.di.DaggerAppComponent


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

    }

}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App