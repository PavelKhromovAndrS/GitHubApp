package ru.pavelkhromov.githubapp.di

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.pavelkhromov.dil.Module
import ru.pavelkhromov.dil.get
import ru.pavelkhromov.githubapp.data.retrofit.GithubApi
import ru.pavelkhromov.githubapp.data.retrofit.RetrofitUsersRepoImpl
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

val appModule = Module {
    val baseUrl = "https://api.github.com/"

    singleton<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    singleton<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }

    singleton<UsersRepo> { RetrofitUsersRepoImpl(get()) }

}