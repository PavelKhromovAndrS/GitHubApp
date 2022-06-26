package ru.pavelkhromov.githubapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

interface  GithubApi {
    @GET("users")
    fun getUsers() : Call<List<UserEntity>>
}