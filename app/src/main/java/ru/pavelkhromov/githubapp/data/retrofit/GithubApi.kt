package ru.pavelkhromov.githubapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface  GithubApi {
    @GET("users")
    fun getUsers() : Single<List<UserEntity>>
}