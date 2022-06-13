package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentShoeDetailBinding
import dev.killebrew.shoestore.models.Shoe
import dev.killebrew.shoestore.models.ShoeViewModel

const val ARG_SHOE_ID = "shoe_id"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {
    private var shoeId: Int = -1
    private lateinit var binding: FragmentShoeDetailBinding

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param shoeOffset Offset in shoe list of shoe to show details for
         * @return A new instance of fragment ShoeDetailFragment.
         */
        @JvmStatic
        fun newInstance(shoeOffset: Int) =
            ShoeDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SHOE_ID, shoeOffset)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shoeId = it.getInt(ARG_SHOE_ID, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        val viewModel: ShoeViewModel by activityViewModels()
        viewModel.setEditingShoe(shoeId)

        binding.vm = viewModel

        // ensure current values are saved on configuration change
        binding.executePendingBindings()

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        binding.saveButton.setOnClickListener {
            try {
                val newShoe = Shoe(
                    name = binding.shoeNameEdit.text.toString(),
                    description = binding.shoeDescriptionEdit.text.toString(),
                    company = binding.shoeManufacturerEdit.text.toString(),
                    size = binding.shoeSizeEdit.text.toString().toDouble(),
                    image = viewModel.editingShoe.value?.image
                )

                viewModel.saveShoe(shoeId, newShoe)
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)

            } catch (ex: Exception) {
                val toast = Toast.makeText(
                    context,
                    getString(R.string.save_error_message),
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = if (shoeId < 0)
            getString(R.string.add_shoe_title) else getString(R.string.edit_shoe_title)
    }
}