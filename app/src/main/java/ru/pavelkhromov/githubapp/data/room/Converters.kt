package ru.pavelkhromov.githubapp.data.room

import ru.pavelkhromov.githubapp.domain.entities.RoomUserEntity
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

class Converters {
    fun convertUsersToEntity(users: List<UserEntity>): List<RoomUserEntity> {
        return users.map {
            RoomUserEntity(
                it.login,
                it.id,
                it.avatarUrl
            )
        }
    }

    fun convertUserEntityToUser(entityList: List<RoomUserEntity>): List<UserEntity> {
        return entityList.map {
            UserEntity(
                it.login,
                it.id,
                it.avatarUrl
            )
        }
    }
}