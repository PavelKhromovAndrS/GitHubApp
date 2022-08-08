package ru.pavelkhromov.githubapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.pavelkhromov.githubapp.data.retrofit.GithubApi
import ru.pavelkhromov.githubapp.data.retrofit.RetrofitUsersRepoImpl
import ru.pavelkhromov.githubapp.data.room.GitHubDao
import ru.pavelkhromov.githubapp.data.room.GitHubDatabase
import ru.pavelkhromov.githubapp.data.room.RoomUsersRepoImpl
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    private val baseUrl = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideUsersRepo(
        api: GithubApi
    ): UsersRepo {
        return RetrofitUsersRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideGithubApi(
        retrofit: Retrofit
    ): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRoom(): GitHubDatabase {

        return Room.databaseBuilder(
            context.applicationContext,
            GitHubDatabase::class.java,
            "github_database"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubDao(db: GitHubDatabase): GitHubDao {
        return db.gitHubDao()
    }

    @Provides
    @Singleton
    fun provideRoomUsersRepo(
        gitHubDao: GitHubDao
    ): RoomUsersRepoImpl {
        return RoomUsersRepoImpl(gitHubDao)
    }


}