package com.example.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    var ageFromPreferences: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SharedPreferences Initialize

        sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", MODE_PRIVATE)

        ageFromPreferences = sharedPreferences.getInt("age",-1)

        if(ageFromPreferences!! < 0){
            binding.tvAge.text = "Your Age: "
        } else {
            binding.tvAge.text = "Your Age: $ageFromPreferences"
        }
    }

    fun save(view : View){
        val age = binding.etvAge.text.toString().toIntOrNull()

        if(age != null) {
            binding.tvAge.text = "Your age: $age"
            sharedPreferences.edit().putInt("age", age).apply()
        }
    }
    
    fun delete(view : View){
        ageFromPreferences = sharedPreferences.getInt("age",-1)

        if(ageFromPreferences!! >= 0){
            sharedPreferences.edit().remove("age").apply()
            binding.tvAge.text = "Your Age:"
        }
    }
}