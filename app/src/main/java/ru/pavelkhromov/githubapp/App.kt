package ru.pavelkhromov.githubapp

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.pavelkhromov.githubapp.data.retrofit.GithubApi
import ru.pavelkhromov.githubapp.data.retrofit.RetrofitUsersRepoImpl
import ru.pavelkhromov.githubapp.data.room.GitHubDatabase
import ru.pavelkhromov.githubapp.data.room.RoomUsersRepoImpl
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }

    val roomRepo by lazy { RoomUsersRepoImpl(database.gitHubDao()) }
    val uiLooper = Handler(Looper.getMainLooper())

    private val api: GithubApi by lazy {
        retrofit.create(GithubApi::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            GitHubDatabase::class.java,
            "github_database"
        )
            .build()
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App