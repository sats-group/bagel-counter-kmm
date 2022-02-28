package com.sats.bagels

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class CounterRepository {
  private val counterValue = MutableStateFlow(0)

  fun getCounterValue(): Flow<Int> = counterValue

  fun incrementCounter() {
    counterValue.value++
  }

  fun decrementCounter() {
    counterValue.value--
  }
}
