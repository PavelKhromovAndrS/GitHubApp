package ru.pavelkhromov.githubapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUserEntity(
    val login: String,
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "avatar_url")val avatarUrl: String
)
