package com.example.fastfoodtracker.ui.hydration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HydrationViewModel : ViewModel() {

    private val _stateHydration = MutableStateFlow<MutableList<Int>>(mutableListOf(0, 0, 0, 0))
    val stateHydration: StateFlow<MutableList<Int>> = _stateHydration
    fun loadState(position: Int, value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Int> = ArrayList()
            list.addAll(_stateHydration.value)
            list[position] += value
            _stateHydration.emit(list)
        }
    }

}