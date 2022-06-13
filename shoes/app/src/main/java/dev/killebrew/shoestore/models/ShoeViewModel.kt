package dev.killebrew.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.killebrew.shoestore.R

class ShoeViewModel: ViewModel() {
    private val _shoes: MutableLiveData<List<Shoe>> = MutableLiveData<List<Shoe>>(
        mutableListOf(
            Shoe("basic kicks", 9.0, "Feetz",
                "These will do, trust us.", R.drawable.ic_high_tops),
            Shoe("dressy", 8.5, "FanSee",
                "Look sharp!", R.drawable.ic_blue_striped_heels),
            Shoe("sandals", 6.0, "BreeZee",
                "It's hot out. Cool your heels in these.", R.drawable.ic_flip_flops)
        )
    )

    val shoes: LiveData<List<Shoe>>
        get() = _shoes
}
