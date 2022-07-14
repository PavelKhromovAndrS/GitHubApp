package ru.pavelkhromov.githubapp.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.pavelkhromov.githubapp.data.retrofit.GithubApi
import ru.pavelkhromov.githubapp.data.retrofit.RetrofitUsersRepoImpl
import ru.pavelkhromov.githubapp.data.room.GitHubDatabase
import ru.pavelkhromov.githubapp.data.room.RoomUsersRepoImpl
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

val appModule = module {
    val baseUrl = "https://api.github.com/"
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }
    single<UsersRepo> { RetrofitUsersRepoImpl(get()) }

    single { RoomUsersRepoImpl(get<GitHubDatabase>().gitHubDao()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            GitHubDatabase::class.java,
            "github_database"
        )
            .build()
    }

}