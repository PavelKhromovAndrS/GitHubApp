package ru.pavelkhromov.githubapp.ui.usersdetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.pavelkhromov.githubapp.app
import ru.pavelkhromov.githubapp.data.room.RoomUsersRepoImpl
import ru.pavelkhromov.githubapp.databinding.ActivityUsersDetailsBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo
import javax.inject.Inject

class UsersDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersDetailsBinding

    private val viewModelDisposable = CompositeDisposable()

    @Inject
    lateinit var usersRepo: UsersRepo

    @Inject
    lateinit var roomUsersRepo: RoomUsersRepoImpl

    private val viewModel:UsersDetailsViewModel by lazy { UsersDetailsViewModel(usersRepo,roomUsersRepo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUsersDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val user = intent.getParcelableExtra<UserEntity>("key")

        app.appComponent.injectUsersDetailsActivity(this)



        viewModelDisposable.addAll(
            viewModel.userLiveData.subscribe { showUser(it) },
            viewModel.errorLiveData.subscribe { showError(it) }
        )

        initView(user)
    }


    private fun showUser(user: UserEntity) {
        binding.userDetailsImageView.load(user.avatarUrl)
        binding.userNameTextView.text = user.login
        binding.userUidTextView.text = user.id.toString()

    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun initView(user: UserEntity?) {
        user?.let {
            viewModel.loadUser(it)
        }
    }


}