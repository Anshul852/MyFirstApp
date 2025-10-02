package com.instamobile.firebaseStarterKit.model

data class CareerRecommendationModel(
    val title: String,
    val description: String,
    val matchPercentage: Int,
    val requiredSkills: List<String>,
    val industry: String? = null,
    val salaryRange: String? = null,
    val experienceLevel: String? = null
)
