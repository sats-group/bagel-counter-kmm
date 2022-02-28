package com.sats.bagels

import kotlinx.coroutines.flow.Flow

class CounterRepository(private val apiService: CounterApiService) {
  fun getCounterValue(): Flow<Int> = apiService.getCounterValue()

  fun incrementCounter() {
    apiService.incrementCounter()
  }

  fun decrementCounter() {
    apiService.decrementCounter()
  }
}
