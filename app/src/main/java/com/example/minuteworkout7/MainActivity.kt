package com.example.minuteworkout7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.minuteworkout7.databinding.ActivityExerciseBinding
import com.example.minuteworkout7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var biding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding?.root)

       // var flstart :FrameLayout= findViewById(R.id.flstart)
        biding?.flstart?.setOnClickListener {
           val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
    }
// here on destroy because you might run into problems and this just a clean way to unassigned the binding to avoid memory leak.
    override fun onDestroy() {
        super.onDestroy()
        biding = null
    }
}