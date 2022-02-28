package com.sats.bagels.android

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sats.bagels.CounterApiService
import com.sats.bagels.CounterRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
  private val repository = CounterRepository(CounterApiService())

  var counterValue: Int by mutableStateOf(0)
    private set

  val isDecrementEnabled: Boolean by derivedStateOf { counterValue > 0 }
  val isIncrementEnabled: Boolean by derivedStateOf { counterValue < 8 }

  init {
    viewModelScope.launch {
      repository.getCounterValue().collect {
        counterValue = it
      }
    }
  }

  fun onDecrementClicked() {
    repository.decrementCounter()
  }

  fun onIncrementClicked() {
    repository.incrementCounter()
  }
}
