package ru.pavelkhromov.githubapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.pavelkhromov.githubapp.domain.entities.RoomUserEntity

@Dao
interface GitHubDao {
    @Query("SELECT * FROM RoomUserEntity")
    fun getUsers(): Single<List<RoomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(users: List<RoomUserEntity>):Completable
}