package com.example.fastfoodtracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.fastfoodtracker.R
import com.example.fastfoodtracker.databinding.FragmentHomeBinding
import com.example.fastfoodtracker.ui.hydration.HydrationViewModel
import com.example.fastfoodtracker.ui.MainNavViewModel
import com.example.fastfoodtracker.util.MainNav

import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    private val hydrationViewModel: HydrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnAddWater.setOnClickListener {
            mainNavViewModel.loadState(MainNav.ADD)
        }
        binding.btnDrop.setOnClickListener {
            mainNavViewModel.loadState(MainNav.HYDRATION)
        }

        binding.btnHamburgerHome.setOnClickListener {
            hydrationViewModel.loadState(0, 300)
        }
        binding.btnFrenchFriesHome.setOnClickListener {
            hydrationViewModel.loadState(1, 200)
        }
        binding.btnShawarmaHome.setOnClickListener {
            hydrationViewModel.loadState(2, 600)
        }
        binding.btnColaHome.setOnClickListener {
            hydrationViewModel.loadState(3, 250)
        }
        lifecycleScope.launch {
            hydrationViewModel.stateHydration.collect {
                binding.tvCurrentDrink.text = "${it[0] + it[1] + it[2] + it[3]} " + getString(R.string.kcal)
                val percentage: Int = ((it[0] + it[1] + it[2] + it[3]) / 2600F * 100).toInt()
                binding.tvPercentageHome.text = "${percentage}%"
                binding.progressBar2.progress = it[0] + it[1] + it[2] + it[3]
            }
        }
        return binding.root
    }
}