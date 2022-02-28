package com.sats.bagels

import kotlinx.coroutines.flow.Flow

expect class CounterApiService {
  fun getCounterValue(): Flow<Int>

  fun incrementCounter()

  fun decrementCounter()
}
