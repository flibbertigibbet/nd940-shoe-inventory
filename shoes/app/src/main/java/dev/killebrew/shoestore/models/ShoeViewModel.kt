package dev.killebrew.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel: ViewModel() {
    private val _shoes: MutableLiveData<List<Shoe>> = MutableLiveData<List<Shoe>>(
        mutableListOf(
            Shoe("basic kicks", 9.0, "Feetz", "These will do, trust us."),
            Shoe("dressy", 8.5, "FanSee", "Look sharp!"),
            Shoe("sandals", 10.0, "BreeZee", "It's hot out. Cool your heels in these.")
        )
    )

    val shoes: LiveData<List<Shoe>>
        get() = _shoes
}
