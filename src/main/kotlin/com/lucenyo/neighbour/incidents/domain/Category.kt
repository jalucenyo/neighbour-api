package com.lucenyo.neighbour.incidents.domain

import java.util.UUID

data class Category(
  val id: UUID,
  val description: String,
  val icon: String
)
