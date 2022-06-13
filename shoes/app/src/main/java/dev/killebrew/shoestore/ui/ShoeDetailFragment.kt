package dev.killebrew.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.databinding.FragmentShoeDetailBinding
import dev.killebrew.shoestore.models.Shoe
import dev.killebrew.shoestore.models.ShoeViewModel

const val ARG_SHOE = "shoe"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {
    private var shoeId: Int = -1
    private var shoe: Shoe? = null
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shoeId = it.getInt(ARG_SHOE, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        val viewModel: ShoeViewModel by activityViewModels()
        shoe = viewModel.getShoe(shoeId)
        binding.shoe = shoe


        requireActivity().actionBar?.title = if (shoe == null)
            getString(R.string.add_shoe_title) else getString(R.string.edit_shoe_title)

        return binding.root
    }

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
                    putInt(ARG_SHOE, shoeOffset)
                }
            }
    }
}