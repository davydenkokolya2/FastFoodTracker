package com.example.fastfoodtracker.ui.add_drink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfoodtracker.util.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDrinkViewModel @Inject constructor(): ViewModel() {
    private val _stateAddDrink = MutableStateFlow<Drink?>(null)
    val stateAddDrink: StateFlow<Drink?> = _stateAddDrink
    fun loadState(drink: Drink) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateAddDrink.emit(drink)
        }
    }

}