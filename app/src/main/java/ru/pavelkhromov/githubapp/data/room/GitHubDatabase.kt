package ru.pavelkhromov.githubapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.pavelkhromov.githubapp.domain.entities.RoomUserEntity

@Database(
    version = 1,
    entities = [
        RoomUserEntity::class
    ]
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun gitHubDao(): GitHubDao

}