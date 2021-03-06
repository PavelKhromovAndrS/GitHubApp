package ru.pavelkhromov.githubapp.data

import android.os.Handler
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

private const val DATA_LOADING_FAKE_DELAY = 1_000L

class FakeUsersRepoImpl(
    private val uiLooper: Handler
) : UsersRepo {

    val data: List<UserEntity> = listOf(
        UserEntity("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        uiLooper.postDelayed({
            onSuccess(data)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getUsers(): Single<List<UserEntity>> = Single.just(data)
    override fun saveUsers(users: List<UserEntity>): Completable = Completable.complete()
}