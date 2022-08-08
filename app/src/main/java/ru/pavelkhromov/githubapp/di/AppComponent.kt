package ru.pavelkhromov.githubapp.di

import dagger.Component
import ru.pavelkhromov.githubapp.ui.users.MainActivity
import ru.pavelkhromov.githubapp.ui.usersdetails.UsersDetailsActivity
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
    fun injectUsersDetailsActivity(usersDetailsActivity: UsersDetailsActivity)
}