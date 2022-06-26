package ru.pavelkhromov.githubapp.ui.usersdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import ru.pavelkhromov.githubapp.R
import ru.pavelkhromov.githubapp.app
import ru.pavelkhromov.githubapp.data.FakeUsersRepoImpl
import ru.pavelkhromov.githubapp.databinding.ActivityMainBinding
import ru.pavelkhromov.githubapp.databinding.ActivityUsersDetailsBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
import ru.pavelkhromov.githubapp.ui.users.UsersContract
import ru.pavelkhromov.githubapp.ui.users.UsersPresenter

class UsersDetailsActivity : AppCompatActivity(), UsersDetailsContract.View {

    private lateinit var binding: ActivityUsersDetailsBinding
    private lateinit var presenter: UsersDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUsersDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = extractPresenter()

        val user = intent.getParcelableExtra<UserEntity>("key")

        presenter.attach(this)

        initView(user)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun showUser(user: UserEntity) {
        binding.userDetailsImageView.load(user.avatarUrl)
        binding.userNameTextView.text = user.login
        binding.userUidTextView.text = user.id.toString()

    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersDetailsContract.Presenter {
        return presenter
    }

    private fun initView(user: UserEntity?) {

        user?.let {
            presenter.loadUser(it)
        }
    }

    private fun extractPresenter(): UsersDetailsContract.Presenter {
        return lastCustomNonConfigurationInstance as? UsersDetailsContract.Presenter
            ?: UsersDetailsPresenter()
    }
}