package com.sats.bagels

import com.microsoft.signalr.HubConnectionBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

actual class CounterApiService {
  private val hubConnection = HubConnectionBuilder.create("https://sats-test-no-bagel.azurewebsites.net/api").build()
  private val counterValue = MutableStateFlow(0)

  init {
    hubConnection.on("UpdatedBagelCount", { newValue: Int -> counterValue.value = newValue }, Int::class.java)
    hubConnection.start().blockingAwait()
  }

  actual fun getCounterValue(): Flow<Int> = counterValue

  actual fun incrementCounter() {
    hubConnection.send("IncrementBagelCount")
  }

  actual fun decrementCounter() {
    hubConnection.send("DecrementBagelCount")
  }
}
