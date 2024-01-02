package com.example.roomexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.R
import com.example.roomexample.adapter.UserAdapter
import com.example.roomexample.data.UserViewModel
import com.example.roomexample.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.toolbar.title = "USER LIST"

        // Initialize
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //Go Add User Page
        binding.fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        userViewModel.readAllData.observe(viewLifecycleOwner, Observer {userList->
            userAdapter = UserAdapter(userList)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = userAdapter
            if(userList.size == 0){
                Snackbar.make(binding.recyclerView, "empty list", Snackbar.LENGTH_SHORT).show()
            }

        })
        return binding.root
    }

}