package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentShoeListBinding
import dev.killebrew.shoestore.models.ShoeViewModel

/**
 * A fragment representing a list of [.models.Shoe].
 */
class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        val viewModel: ShoeViewModel by activityViewModels()

        // Set the adapter
        with(binding.shoeList) {
            layoutManager = LinearLayoutManager(context)
            adapter = ShoeRecyclerViewAdapter(viewModel.shoes.value ?: emptyList())
        }

        viewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            run {
                val adapter = binding.shoeList.adapter
                if (adapter is ShoeRecyclerViewAdapter) {
                    adapter.updateShoes(shoeList)
                }
            }
        }

        return binding.root
    }
}