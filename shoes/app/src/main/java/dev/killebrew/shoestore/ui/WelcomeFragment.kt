package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentWelcomeBinding

/**
 * A welcome screen for new users.
 */
class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        binding.goToInstructionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_instructionsFragment)
        }

        return binding.root
    }
}