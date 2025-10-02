package com.instamobile.firebaseStarterKit.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.instamobile.firebaseStarterKit.model.UserModel

object FirestoreUtils {
    private val firestore = FirebaseFirestore.getInstance()
    
    // Career profile operations
    fun saveCareerAssessment(
        userId: String, 
        userModel: UserModel,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("career_profiles")
            .document(userId)
            .set(userModel)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
    
    fun getCareerRecommendations(
        skills: List<String>,
        interests: List<String>,
        onSuccess: (List<CareerRecommendationModel>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Query career recommendations based on skills and interests
        firestore.collection("career_recommendations")
            .whereArrayContainsAny("requiredSkills", skills)
            .get()
            .addOnSuccessListener { documents ->
                val recommendations = documents.toObjects(CareerRecommendationModel::class.java)
                onSuccess(recommendations)
            }
            .addOnFailureListener { onFailure(it) }
    }
    
    fun saveUserProgress(
        userId: String,
        progressData: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firestore.collection("career_profiles")
            .document(userId)
            .update(progressData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}
