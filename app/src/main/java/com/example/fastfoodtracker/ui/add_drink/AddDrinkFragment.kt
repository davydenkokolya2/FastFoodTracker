package com.example.fastfoodtracker.ui.add_drink

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.fastfoodtracker.R
import com.example.fastfoodtracker.databinding.FragmentAddDrinkBinding
import com.example.fastfoodtracker.ui.MainNavViewModel
import com.example.fastfoodtracker.ui.hydration.HydrationViewModel

import com.example.fastfoodtracker.util.Drink
import com.example.fastfoodtracker.util.MainNav
import kotlinx.coroutines.launch

class AddDrinkFragment : Fragment() {

    private lateinit var binding: FragmentAddDrinkBinding
    private val addDrinkViewModel: AddDrinkViewModel by activityViewModels()
    private val mainNavViewModel: MainNavViewModel by activityViewModels()
    private val hydrationViewModel: HydrationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDrinkBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            addDrinkViewModel.stateAddDrink.collect {
                binding.btnHamburger.setImageResource(R.drawable.icon_hamburger_button_false)
                binding.btnFrenchFries.setImageResource(R.drawable.icon_french_fries_button_false)
                binding.btnShawarma.setImageResource(R.drawable.icon_shawarma_button_false)
                binding.btnCola.setImageResource(R.drawable.icon_cola_button_false)
                binding.tvHasburgerAdd.setTextColor(Color.GRAY)
                binding.tvFrenchFriesAdd.setTextColor(Color.GRAY)
                binding.tvShawarmaAdd.setTextColor(Color.GRAY)
                binding.tvColaAdd.setTextColor(Color.GRAY)

                when (it) {
                    Drink.HAMBURGER -> {
                        binding.btnHamburger.setImageResource(R.drawable.icon_hasburger_button_true)
                        binding.tvHasburgerAdd.setTextColor(Color.WHITE)
                    }

                    Drink.FRENCH_FRIES -> {
                        binding.btnFrenchFries.setImageResource(R.drawable.icon_french_fries_button_true)
                        binding.tvFrenchFriesAdd.setTextColor(Color.WHITE)
                    }

                    Drink.SHAWARMA -> {
                        binding.btnShawarma.setImageResource(R.drawable.icon_shawarma_button_true)
                        binding.tvShawarmaAdd.setTextColor(Color.WHITE)
                    }

                    Drink.COLA -> {
                        binding.btnCola.setImageResource(R.drawable.icon_cola_button_true)
                        binding.tvColaAdd.setTextColor(Color.WHITE)
                    }

                    else -> {

                    }
                }
            }
        }

        binding.seekBar2.max = 10
        binding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvCurrentML.text = "${progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        binding.btnHamburger.setOnClickListener {
            addDrinkViewModel.loadState(Drink.HAMBURGER)
        }
        binding.btnFrenchFries.setOnClickListener {
            addDrinkViewModel.loadState(Drink.FRENCH_FRIES)
        }
        binding.btnShawarma.setOnClickListener {
            addDrinkViewModel.loadState(Drink.SHAWARMA)
        }
        binding.btnCola.setOnClickListener {
            addDrinkViewModel.loadState(Drink.COLA)
        }
        binding.btnAdd.setOnClickListener {
            when (addDrinkViewModel.stateAddDrink.value) {
                Drink.HAMBURGER -> hydrationViewModel.loadState(0, binding.seekBar2.progress * 300)
                Drink.FRENCH_FRIES -> hydrationViewModel.loadState(1, binding.seekBar2.progress * 200)
                Drink.SHAWARMA -> hydrationViewModel.loadState(2, binding.seekBar2.progress * 600)
                Drink.COLA -> hydrationViewModel.loadState(3, binding.seekBar2.progress * 250)
                else -> {}
            }
            mainNavViewModel.loadState(MainNav.HOME)
        }

        return binding.root
    }
}