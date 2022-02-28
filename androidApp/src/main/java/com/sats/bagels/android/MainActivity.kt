package com.sats.bagels.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Surface(color = Color.Black, contentColor = Color.White) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Image(painterResource(R.drawable.bagel), contentDescription = null, Modifier.size(160.dp))

          Text("Bagel counter", Modifier.padding(top = 32.dp), fontSize = 56.sp)

          Row(
            Modifier
              .fillMaxWidth()
              .height(IntrinsicSize.Min)
              .padding(horizontal = 64.dp)
              .padding(top = 74.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = viewModel.counterValue.toString(),
              fontSize = 76.sp,
              fontWeight = FontWeight.Bold
            )

            Box(
              Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(color = Color(0xFFC4C4C4))
            )

            IconButton(
              onClick = viewModel::onDecrementClicked,
              enabled = viewModel.isDecrementEnabled
            ) {
              Icon(Icons.Outlined.ExpandMore, contentDescription = null, Modifier.size(64.dp))
            }

            IconButton(
              onClick = viewModel::onIncrementClicked,
              enabled = viewModel.isIncrementEnabled
            ) {
              Icon(Icons.Outlined.ExpandLess, contentDescription = null, Modifier.size(64.dp))
            }
          }
        }
      }
    }
  }
}
