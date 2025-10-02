package com.instamobile.firebaseStarterKit.ui.fragment.careerQuiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.instamobile.firebaseStarterKit.R

class CareerQuizFragment : Fragment(){
    override fun onCreateView(
        inflater:LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View?{
        return inflater.inflate(R.layout.fragment_career_quiz, container, false)
    }  
}