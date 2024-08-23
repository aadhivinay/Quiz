package com.example.splashscreen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.splashscreen.databinding.ActivityQuizBinding
import com.example.splashscreen.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(),View.OnClickListener {
    companion object{
        var questionModelList: List<QuestionModel> = listOf()
        var  time:String=""
    }

    private lateinit var binding: ActivityQuizBinding
    var currentQuestionIndex=0
    var selectedAnswer=""
    var score=0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestions()
        startTimer()
    }

    private fun startTimer() {
        val totalTimeInMillis= time.toInt()*60*1000L
        object :CountDownTimer(totalTimeInMillis,1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds=millisUntilFinished/1000
                val minutes=seconds/60
                val remainingSeconds=seconds%60
                binding.timerIndicator.text=String.format("%02d:%02d",minutes,remainingSeconds)
            }
            override fun onFinish() {
            }
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun loadQuestions(){
        selectedAnswer = ""
        resetButtonColors() // Reset button colors before loading new question
        enableOptionButtons() // Enable option buttons for new question

        if(currentQuestionIndex == questionModelList.size){
            finishQuiz()
            return
        }
        binding.apply {
            questionIndicator.text = "Question ${currentQuestionIndex+1} / ${questionModelList.size}"
            questionProgress.progress = (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            question.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].option[0]
            btn1.text = questionModelList[currentQuestionIndex].option[1]
            btn2.text = questionModelList[currentQuestionIndex].option[2]
            btn3.text = questionModelList[currentQuestionIndex].option[3]
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun resetButtonColors() {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.grey))
            btn1.setBackgroundColor(getColor(R.color.grey))
            btn2.setBackgroundColor(getColor(R.color.grey))
            btn3.setBackgroundColor(getColor(R.color.grey))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun enableOptionButtons() {
        binding.apply {
            btn0.isClickable = true
            btn1.isClickable = true
            btn2.isClickable = true
            btn3.isClickable = true

            // Reset button backgrounds to default
            btn0.setBackgroundColor(getColor(R.color.grey))
            btn1.setBackgroundColor(getColor(R.color.grey))
            btn2.setBackgroundColor(getColor(R.color.grey))
            btn3.setBackgroundColor(getColor(R.color.grey))
        }
    }




    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View) {
        val clickedBtn = view as Button

        if (clickedBtn.id == R.id.next_btn) {
            // Next button is clicked
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(applicationContext, "Please select an answer to continue..", Toast.LENGTH_SHORT).show()
                return
            }

            if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
                score++
                Log.i("score of quiz", score.toString())
            }

            // Highlight correct option after checking the answer
            highlightCorrectOption()

            currentQuestionIndex++
            loadQuestions()
        } else {
            // Options button is clicked
            selectedAnswer = clickedBtn.text.toString()

            if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
                clickedBtn.setBackgroundColor(getColor(R.color.lightgreen))
            } else {
                clickedBtn.setBackgroundColor(getColor(R.color.red))

                // Highlight correct option
                highlightCorrectOption()
            }

            // Disable all option buttons after selecting an option
            disableOptionButtons()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun highlightCorrectOption() {
        when (questionModelList[currentQuestionIndex].correct) {
            binding.btn0.text.toString() -> {
                binding.btn0.setBackgroundColor(getColor(R.color.lightgreen))
            }
            binding.btn1.text.toString() -> {
                binding.btn1.setBackgroundColor(getColor(R.color.lightgreen))
            }
            binding.btn2.text.toString() -> {
                binding.btn2.setBackgroundColor(getColor(R.color.lightgreen))
            }
            binding.btn3.text.toString() -> {
                binding.btn3.setBackgroundColor(getColor(R.color.lightgreen))
            }
        }
    }

    private fun disableOptionButtons() {
        binding.apply {
            btn0.isClickable = false
            btn1.isClickable = false
            btn2.isClickable = false
            btn3.isClickable = false
        }
    }

    private fun finishQuiz(){
        val totalQuestions= questionModelList.size
        val percentage=((score.toFloat()/totalQuestions.toFloat())*100).toInt()
        val dialogBinding=ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress=percentage
            scoreProgressText.text="$percentage%"
            if(percentage>60){
                scoreTitle.text="Congrats! Well played"
                scoreTitle.setTextColor(Color.BLUE)
           }else{
                scoreTitle.text="Try to improve !!"
                scoreTitle.setTextColor(Color.RED)
            }
            scoreSubtitle.text="$score out of $totalQuestions are correct "
            finishBtn.setOnClickListener{
                finish()
            }
        }
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

    }
}