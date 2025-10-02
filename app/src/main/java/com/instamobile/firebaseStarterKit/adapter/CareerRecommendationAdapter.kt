package com.instamobile.firebaseStarterKit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.instamobile.firebaseStarterKit.model.SlideContent
import com.instamobile.ui.fragment.onBoarding.walkthroughactivity.databinding.SlideBinding

class CareerRecommendationAdapter(
    private val recommendations: List<CareerRecommendationModel>
) : RecyclerView.Adapter<CareerRecommendationAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        val descriptionText: TextView = view.findViewById(R.id.descriptionText)
        val matchPercentage: TextView = view.findViewById(R.id.matchPercentage)
        val requiredSkillsText: TextView = view.findViewById(R.id.requiredSkillsText)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_career_recommendation, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recommendation = recommendations[position]
        
        holder.titleText.text = recommendation.title
        holder.descriptionText.text = recommendation.description
        holder.matchPercentage.text = "${recommendation.matchPercentage}% Match"
        holder.requiredSkillsText.text = "Skills: ${recommendation.requiredSkills.joinToString(", ")}"
    }
    
    override fun getItemCount() = recommendations.size
}
