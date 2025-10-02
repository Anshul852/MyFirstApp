package com.instamobile.firebaseStarterKit.ui.fragment.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.instamobile.firebaseStarterKit.adapter.SliderAdapter
import com.instamobile.firebaseStarterKit.utils.Prefs
import com.instamobile.ui.fragment.onBoarding.walkthroughactivity.databinding.FragmentOnBoardingBinding
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class CareerAssessmentFragment : Fragment() {
    // Change adapter to handle assessment questions
    private var assessmentAdapter: CareerAssessmentAdapter = CareerAssessmentAdapter()
    private lateinit var viewModel: CareerAssessmentViewModel
    private lateinit var binding: FragmentCareerAssessmentBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CareerAssessmentViewModel::class.java)
        binding = FragmentCareerAssessmentBinding.inflate(inflater, container, false)
        binding.careerAssessmentViewModel = viewModel
        binding.lifecycleOwner = this
        
        // Load career assessment questions instead of onboarding slides
        viewModel.assessmentQuestions.observe(this, Observer {
            binding.viewPager2.adapter = assessmentAdapter
            assessmentAdapter.setQuestions(it)
            TabLayoutMediator(
                binding.indicator,
                binding.viewPager2,
                object : TabLayoutMediator.TabConfigurationStrategy {
                    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                        binding.viewPager2.setCurrentItem(tab.position, true)
                    }
                }).attach()
        })
        
        // Navigate to results screen after assessment completion
        viewModel.assessmentComplete.observe(this, Observer {
            if (it) {
                this.findNavController()
                    .navigate(CareerAssessmentDirections.actionAssessmentToResults())
                Prefs.getInstance(context!!)!!.hasCompletedCareerAssessment = true
                viewModel.doneNavigation()
            }
        })
        
        binding.viewPager2.registerOnPageChangeCallback(viewModel.pagerCallBack)
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager2.unregisterOnPageChangeCallback(viewModel.pagerCallBack)
    }
}
