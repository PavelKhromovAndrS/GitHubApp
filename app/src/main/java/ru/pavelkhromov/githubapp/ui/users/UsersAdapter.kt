package ru.pavelkhromov.githubapp.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.pavelkhromov.githubapp.domain.entities.UserEntity
interface OnItemClickListener {
    fun onItemClick(user: UserEntity)
}


class UsersAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)

    }

    override fun getItemId(position: Int) = getItem(position).id


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent,listener)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]


    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}