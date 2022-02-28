package com.sats.bagels.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.sats.bagels.Greeting

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Text(
        modifier = Modifier
          .fillMaxSize()
          .wrapContentSize(),
        text = greet()
      )
    }
  }
}

private fun greet(): String {
  return Greeting().greeting()
}
