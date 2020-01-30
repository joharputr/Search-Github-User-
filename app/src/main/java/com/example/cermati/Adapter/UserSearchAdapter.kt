package com.example.cermati.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cermati.Network.Model.ItemsItem
import com.example.cermati.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_search_list.view.*

class UserSearchAdapter(val item: MutableList<ItemsItem>) :
    RecyclerView.Adapter<UserSearchAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(itemsItem: ItemsItem) = itemView.run {
            nameUser.text = itemsItem.login
            Picasso.get().load(itemsItem.avatarUrl).into(imageUser)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserSearchAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_search_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: UserSearchAdapter.ViewHolder, position: Int) {
        holder.bind(item[position])
    }

    fun refreshAdapter(result: List<ItemsItem>) {
        this.item.addAll(result)
        notifyItemRangeChanged(0, this.item.size)
    }
}
