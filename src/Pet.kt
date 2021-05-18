package com.petstore

import kotlinx.serialization.Serializable

@Serializable
data class Pet(
  val name: String,
  val type: String,
  val price: Double
) {
  val id: Int = name.hashCode()
}
