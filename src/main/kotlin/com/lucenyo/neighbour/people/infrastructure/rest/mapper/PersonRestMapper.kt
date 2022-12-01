package com.lucenyo.neighbour.people.infrastructure.rest.mapper

import com.lucenyo.neighbour.people.domain.Person
import com.lucenyo.neighbour.people.domain.PersonFilter
import com.lucenyo.neighbour.people.infrastructure.rest.model.PersonFilterRequest
import com.lucenyo.neighbour.people.infrastructure.rest.model.PersonRequest
import com.lucenyo.neighbour.people.infrastructure.rest.model.PersonResponse
import java.util.UUID

fun PersonRequest.toDomain(id: UUID, userId: String) = Person(
  id = id,
  firstName = firstName!!,
  lastName = lastName!!,
  userId = userId,
)

fun Person.toResponse() = PersonResponse(
  id = id,
  firstName = firstName,
  lastName = lastName,
  imageUrl = imageUrl,
)

fun PersonFilterRequest.toDomain() = PersonFilter(
  userId = userId
)