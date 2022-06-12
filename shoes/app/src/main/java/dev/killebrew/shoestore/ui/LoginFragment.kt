package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentLoginBinding

/**
 * Log in to the shoe app.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.createAcctButton.setOnClickListener {
            navigateNext()
        }

        binding.loginButton.setOnClickListener {
            navigateNext()
        }

        return binding.root
    }

    private fun navigateNext() {
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
    }
}