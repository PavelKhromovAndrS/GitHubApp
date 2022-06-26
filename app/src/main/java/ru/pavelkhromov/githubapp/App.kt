package ru.pavelkhromov.githubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.pavelkhromov.githubapp.data.FakeUsersRepoImpl
import ru.pavelkhromov.githubapp.data.retrofit.RetrofitUsersRepoImpl
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class App: Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App