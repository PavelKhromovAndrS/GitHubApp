package ru.pavelkhromov.githubapp.data.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.pavelkhromov.githubapp.App
import ru.pavelkhromov.githubapp.domain.entities.RoomUserEntity
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class RoomUsersRepoImpl(
    private val gitHubDao: GitHubDao
) : UsersRepo {
    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?){}

    override fun getUsers(): Single<List<UserEntity>> {
        return gitHubDao.getUsers().map {
            Converters().convertUserEntityToUser(it)
        }
    }

    override fun saveUsers(users: List<UserEntity>): Completable{
        return gitHubDao.insertUsers(Converters().convertUsersToEntity(users))
    }

}