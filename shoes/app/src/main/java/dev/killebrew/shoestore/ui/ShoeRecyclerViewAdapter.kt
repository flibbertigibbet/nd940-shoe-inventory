package dev.killebrew.shoestore.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.killebrew.shoestore.databinding.FragmentShoeListItemBinding
import dev.killebrew.shoestore.models.Shoe

/**
 * [RecyclerView.Adapter] that can display a [.Shoe].
 */
class ShoeRecyclerViewAdapter(
    private var shoes: List<Shoe>
) : RecyclerView.Adapter<ShoeRecyclerViewAdapter.ViewHolder>() {

    fun updateShoes(newShoes: List<Shoe>) {
        shoes = newShoes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentShoeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = shoes[position]
        holder.bind(shoe)
    }

    override fun getItemCount(): Int = shoes.size


    inner class ViewHolder(binding: FragmentShoeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val _binding = binding

        fun bind(shoe: Shoe) {
            _binding.shoe = shoe
            _binding.executePendingBindings()
        }

        override fun toString(): String {
            return super.toString() + " '" + _binding.itemName.text + "'"
        }
    }
}