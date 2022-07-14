package ru.pavelkhromov.githubapp.data.retrofit

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class RetrofitUsersRepoImpl(
    private val api: GithubApi
) : UsersRepo {

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getUsers().subscribeBy(
            onSuccess = {
                onSuccess.invoke(it)
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUsers(): Single<List<UserEntity>> = api.getUsers()
    override fun saveUsers(users: List<UserEntity>): Completable = Completable.complete()

}