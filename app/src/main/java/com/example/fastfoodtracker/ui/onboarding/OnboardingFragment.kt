package com.example.fastfoodtracker.ui.onboarding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fastfoodtracker.databinding.FragmentOnboardingBinding
import com.example.fastfoodtracker.ui.MainNavViewModel
import com.example.fastfoodtracker.util.MainNav

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)


        binding.btnStart.setOnClickListener {
            mainNavViewModel.loadState(MainNav.HOME)
        }

        return binding.root
    }
}