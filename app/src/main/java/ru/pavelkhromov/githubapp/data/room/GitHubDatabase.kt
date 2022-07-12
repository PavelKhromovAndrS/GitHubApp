package ru.pavelkhromov.githubapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pavelkhromov.githubapp.App
import ru.pavelkhromov.githubapp.domain.entities.RoomUserEntity

@Database(
    version = 1,
    entities = [
        RoomUserEntity::class
    ]
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun gitHubDao(): GitHubDao

    companion object {

        @Volatile
        private var INSTANCE: GitHubDatabase? = null

        fun getDatabase(context: Context): GitHubDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GitHubDatabase::class.java,
                    "github_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}