package com.lucenyo.neighbour.community.domain

import java.util.UUID

data class Community(
  val id: UUID,
  val name: String,
  val deleted: Boolean = false,

  val advisersOffice: String? = null,

  val neighbors: Set<PersonRole> = setOf()
)

data class PersonRole(
  val personId: UUID,
  val role: NeighborRole
)

enum class NeighborRole{
  PRESIDENT,
  NEIGHBOUR,
  RENTER,
}