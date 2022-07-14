package ru.pavelkhromov.githubapp.ui.usersdetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import ru.pavelkhromov.githubapp.data.room.RoomUsersRepoImpl
import ru.pavelkhromov.githubapp.databinding.ActivityUsersDetailsBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.domain.repos.UsersRepo

class UsersDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersDetailsBinding
    private lateinit var viewModel: UsersDetailsContract.ViewModel
    private val viewModelDisposable = CompositeDisposable()
    private val usersRepo: UsersRepo  by inject()
    private val roomRepo: RoomUsersRepoImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUsersDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = extractViewModel()

        val user = intent.getParcelableExtra<UserEntity>("key")

        viewModel = extractViewModel()

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

    override fun onRetainCustomNonConfigurationInstance(): UsersDetailsContract.ViewModel =
        viewModel

    private fun initView(user: UserEntity?) {
        user?.let {
            viewModel.loadUser(it)
        }
    }

    private fun extractViewModel(): UsersDetailsContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersDetailsContract.ViewModel
            ?: UsersDetailsViewModel(usersRepo, roomRepo)
    }
}