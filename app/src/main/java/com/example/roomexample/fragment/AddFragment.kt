package com.example.roomexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomexample.R
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.example.roomexample.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        //ViewModel Tanımlama
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]



        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val surname = binding.editTextSurname.text.toString()
            val email = binding.editTextTextEmailAddress.text.toString()
            val user = User(0,name,surname,email)
            userViewModel.addUser(user)
            Snackbar.make(it,"User Added!",Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        return binding.root
    }

}