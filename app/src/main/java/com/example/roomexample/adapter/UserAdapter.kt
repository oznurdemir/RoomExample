package com.example.roomexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.example.roomexample.databinding.UserItemBinding
import com.example.roomexample.fragment.ListFragmentDirections
import com.google.android.material.snackbar.Snackbar

class UserAdapter(val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        val t = holder.binding

        t.txtName.text = user.name
        t.txtSurname.text = user.surname
        t.txtMail.text = user.mail
        t.root.setOnClickListener {
            Snackbar.make(it,"Clicked",Snackbar.LENGTH_SHORT).show()
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user = user)
            Navigation.findNavController(it).navigate(action)
        }

    }
}