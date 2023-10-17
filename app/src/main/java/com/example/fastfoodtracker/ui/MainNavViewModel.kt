package com.example.fastfoodtracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfoodtracker.util.MainNav
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainNavViewModel: ViewModel() {
    private val _stateMainNav = MutableStateFlow<MainNav>(MainNav.ONBOARDING)
    val stateMainNav: StateFlow<MainNav> = _stateMainNav
    fun loadState(mainNav: MainNav) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMainNav.emit(mainNav)
        }
    }
}