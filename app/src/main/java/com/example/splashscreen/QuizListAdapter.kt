package com.example.splashscreen

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.splashscreen.databinding.QuizItemRecyclerRowBinding

class QuizListAdapter(private val quizModelList: List<QuizModel>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    companion object {
        private const val TAG = "QuizListAdapter"
    }

    class MyViewHolder(private val binding: QuizItemRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: QuizModel) {
            binding.apply {
                quizTitle.text = model.title
                quizSubtitle.text = model.subtitle
                quizTime.text = model.time+" mins"
                root.setOnClickListener {
                    Log.d(TAG, "Item clicked at position $adapterPosition")
                    val intent = Intent(root.context,QuizActivity::class.java)
                    QuizActivity.questionModelList=model.questionList
                    QuizActivity.time=model.time
                    root.context.startActivity(intent)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "Quiz Model List Size: ${quizModelList.size}")
        return quizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}
