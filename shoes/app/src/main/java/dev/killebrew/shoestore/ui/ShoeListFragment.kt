package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
            adapter = ShoeRecyclerViewAdapter(viewModel.shoes.value ?: emptyList()
            ) { shoeId ->
                run {
                    val bundle = Bundle()
                    bundle.putInt(ARG_SHOE_ID, shoeId)
                    findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment, bundle)
                }
            }
        }

        viewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            run {
                // update shoe list when it changes
                val adapter = binding.shoeList.adapter
                if (adapter is ShoeRecyclerViewAdapter) {
                    adapter.updateShoes(shoeList)
                    adapter.notifyDataSetChanged()
                }
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
}