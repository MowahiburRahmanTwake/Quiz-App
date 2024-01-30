package com.example.quizapp.activities.activities.models

data class Quiz(
    var id: String ="",
    var title:String = "",
    var question: MutableMap<String, Question> = mutableMapOf()
)
