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
                "It's hot out. Cool your heels in these.", R.drawable.ic_flip_flops),
            Shoe("thongs", 11.0, "BreeZee",
            "These thongs are as cool as you are.", R.drawable.ic_thongs),
            Shoe("pretty in pink", 7.5, "Feetz",
                "They're so cute, you know you want them!", R.drawable.ic_pink_sneakers)
        )
    )

    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _newCustomer: MutableLiveData<Boolean> = MutableLiveData(true)

    val newCustomer: LiveData<Boolean>
        get() = _newCustomer

    fun handleLogin() = _newCustomer.postValue(false)

    fun getShoe(offset: Int) = if (offset > -1 &&
        offset < ((shoes.value?.size ?: 0)
    )) shoes.value?.get(offset) else null

    fun saveShoe(offset: Int, shoe: Shoe) {
        // copy existing shoe list
        val shoeList = shoes.value?.toMutableList()
        if (shoeList.isNullOrEmpty()) {
            // add new shoe to an empty shoe list (shouldn't happen)
            _shoes.postValue(mutableListOf(shoe))
        }
        else if (offset >= 0 && offset < (shoeList.size)) {
            // edit existing shoe
            shoeList[offset] = shoe
            _shoes.postValue(shoeList)
        } else {
            // add new shoe
            shoeList.add(shoe)
            _shoes.postValue(shoeList)
        }
    }
}
