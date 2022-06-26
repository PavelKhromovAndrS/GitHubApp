package ru.pavelkhromov.githubapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.pavelkhromov.githubapp.R
import ru.pavelkhromov.githubapp.databinding.ItemUserBinding
import ru.pavelkhromov.githubapp.domain.entities.UserEntity

class UserViewHolder(parent: ViewGroup, private val listener:OnItemClickListener) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false),
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        binding.root.setOnClickListener {
            listener.onItemClick(userEntity)
        }
        binding.avatarImageView.load(userEntity.avatarUrl)
        binding.loginTextView.text = userEntity.login
        binding.uidTextView.text = userEntity.id.toString()
    }
}