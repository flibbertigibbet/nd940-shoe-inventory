package dev.killebrew.shoestore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dev.killebrew.shoestore.R
import dev.killebrew.shoestore.models.ShoeViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shoeViewModel: ShoeViewModel by viewModels()
    }
}
