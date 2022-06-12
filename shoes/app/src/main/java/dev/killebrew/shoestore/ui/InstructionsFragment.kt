package dev.killebrew.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentInstructionsBinding

/**
 * Screen with instructions for new users.
 */
class InstructionsFragment : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        binding.goToShoeListButton.setOnClickListener {
            findNavController().navigate(R.id.action_instructionsFragment_to_shoeListFragment)
        }

        return binding.root
    }
}
