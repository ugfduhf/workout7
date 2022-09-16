package com.example.minuteworkout7

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.minuteworkout7.databinding.ActivityExerciseBinding
import org.w3c.dom.Text
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var binding : ActivityExerciseBinding?=null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var timerExercise: CountDownTimer? = null
    private var progressExercise = 0

    private var exerciseList : ArrayList<exerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts : TextToSpeech? = null
    //vare
    private var player:MediaPlayer?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //Here are the toolbar show or display
        setSupportActionBar(binding?.toolbarExercise)
        // Here are get up the back button on the screen in this case.
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = Constants.defaultExercise()

        // tts text to speech.
        tts = TextToSpeech(this,this)

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
    }

    private fun setupRestView(){

        try{
            val soundURI = Uri.parse("android.resource://com.example.minuteworkout7/" + R.raw.p)
            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping = false
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }
        binding?.frameLayout?.visibility = View.VISIBLE
        binding?.getReadyFor?.visibility = View.VISIBLE
        binding?.tvUpcomingExercise?.visibility = View.VISIBLE
        binding?.tvNextExercise?.visibility=View.VISIBLE
        // binding?.getReadyFor?.text = "Exercise Name"
        binding?.frameLayoutExercise?.visibility = View.INVISIBLE
        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE


        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0

        }
        binding?.tvNextExercise?.text = exerciseList!![currentExercisePosition +1].getName()
        setRestProgressBar()
    }
    private fun setUpExerciseView(){

        binding?.getReadyFor?.visibility = View.INVISIBLE
        binding?.frameLayout?.visibility = View.INVISIBLE
        binding?.tvUpcomingExercise?.visibility = View.INVISIBLE
        binding?.tvNextExercise?.visibility= View.INVISIBLE
       // binding?.getReadyFor?.text = "Exercise Name"
        binding?.frameLayoutExercise?.visibility = View.VISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE

        if(timerExercise !=  null){
            timerExercise?.cancel()
            progressExercise = 0
        }
        speakOut(exerciseList!![currentExercisePosition].getName())


        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
               restProgress++
                //
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()

            }

            override fun onFinish() {
                currentExercisePosition++

                setUpExerciseView()
            }

        }.start()
    }
    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = progressExercise

        timerExercise = object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                progressExercise++
                //
                binding?.progressBarExercise?.progress = 30 - progressExercise
                binding?.tvTimerExercise?.text = (30 - progressExercise).toString()

            }

            override fun onFinish() {
                //When you have more exercise it jump back to setRest.
                if (currentExercisePosition < exerciseList?.size!!-1){
                    setupRestView()
                }else
                Toast.makeText(this@ExerciseActivity,"Congratulations!, You have completed all.",Toast.LENGTH_LONG).show()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        if(timerExercise !=  null){
            timerExercise?.cancel()
            progressExercise = 0
        }
        // Shutting down the Text to speech feature when activity is destroyed
        // start
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if (player != null){
            player!!.stop()


        }
        binding =null
    }

    //override of the text to speech.
    override fun onInit(status: Int) {
    if (status == TextToSpeech.SUCCESS){
        val result = tts?.setLanguage(Locale.US)

        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
            Log.e("TTS","The Language specified is not supported!")
        }
    }else{
        Log.e("TTS","Initialization Failed!")
    }
    }
    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_ADD,null,"")
    }
}
