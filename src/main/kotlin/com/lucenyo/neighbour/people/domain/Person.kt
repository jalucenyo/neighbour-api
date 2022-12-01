package com.lucenyo.neighbour.people.domain

import java.util.UUID

data class Person (

  val id: UUID,
  val firstName: String,
  val lastName: String,
  val imageUrl: String? = null,
  val userId: String,

)
