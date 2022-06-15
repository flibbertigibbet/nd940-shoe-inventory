package dev.killebrew.shoestore.models

import android.os.Parcelable
import dev.killebrew.shoestore.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(var name: String? = "", var size: Double? = 0.0, var company: String? = "",
                var description: String? = "", val image: Int? = R.drawable.ic_shoe_outline
) : Parcelable
