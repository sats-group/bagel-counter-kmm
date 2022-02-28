package com.sats.bagels

class Greeting {
  fun greeting(): String {
    return "Hello, ${Platform().platform}!"
  }
}
