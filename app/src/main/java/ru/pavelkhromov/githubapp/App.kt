package ru.pavelkhromov.githubapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.pavelkhromov.githubapp.data.retrofit.RetrofitUsersRepoImpl
import ru.pavelkhromov.githubapp.data.room.GitHubDatabase
import ru.pavelkhromov.githubapp.data.room.RoomUsersRepoImpl
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl() }
    private val database by lazy { GitHubDatabase.getDatabase(this) }
    val repository by lazy { RoomUsersRepoImpl(database.gitHubDao()) }
    override fun onCreate() {
        super.onCreate()
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App