package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentShoeListBinding
import dev.killebrew.shoestore.databinding.FragmentShoeListItemBinding
import dev.killebrew.shoestore.models.Shoe
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

        populateShoeList(viewModel.shoes.value ?: emptyList())
        viewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            run {
                populateShoeList(shoeList)
            }
        }

        binding.addShoeFab.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_item -> {
                findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = getString(R.string.app_name)
    }

    private fun populateShoeList(shoes: List<Shoe>) {
        // clear list first
        binding.shoeList.removeAllViews()

        for (i in shoes.indices) {
            val shoeBinding = FragmentShoeListItemBinding.inflate(
                LayoutInflater.from(context),
                binding.shoeList,
                false
            )

            shoeBinding.shoe = shoes[i]
            val shoeItemView = shoeBinding.root
            binding.shoeList.addView(shoeItemView)
            shoeBinding.executePendingBindings()

            shoeItemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt(ARG_SHOE_ID, i)
                findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment, bundle)
            }
        }
    }
}