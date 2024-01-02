package com.example.roomexample.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomexample.R
import com.example.roomexample.data.User
import com.example.roomexample.data.UserViewModel
import com.example.roomexample.databinding.FragmentUpdateBinding
import com.google.android.material.snackbar.Snackbar

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UserViewModel
    val bundle: UpdateFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)


        binding.editTextNameUpdate.setText(bundle.user.name)
        binding.editTextSurnameUpdate.setText(bundle.user.surname)
        binding.editTextEmailUpdate.setText(bundle.user.mail)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.buttonUpdate.setOnClickListener {
            val name = binding.editTextNameUpdate.text.toString()
            val surname = binding.editTextSurnameUpdate.text.toString()
            val email = binding.editTextEmailUpdate.text.toString()
            val updateUser = User(bundle.user.id,name,surname,email)
            viewModel.updateUser(updateUser)
            Snackbar.make(it,"User Updated", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        binding.toolbar.title = "Update"

        // Toolbar'ı ActionBar olarak ayarla
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)

        // Fragment'e setHasOptionsMenu(true) diyerek onCreateOptionsMenu metodunun çağrılmasını sağlıyoruz.
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteItem ->{
                val alert = AlertDialog.Builder(requireContext())
                alert.setTitle("DELETE")
                alert.setMessage("Do you wan to delete ${binding.editTextNameUpdate.text} ?")
                alert.setPositiveButton("Yes"){_,_->
                    viewModel.deleteUser(bundle.user)
                    Snackbar.make(binding.buttonUpdate, "${bundle.user.name} deleted!",Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }.setNegativeButton("No"){_,_-> }
                alert.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}