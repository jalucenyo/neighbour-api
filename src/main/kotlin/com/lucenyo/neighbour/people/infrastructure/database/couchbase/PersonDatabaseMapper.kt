package com.lucenyo.neighbour.people.infrastructure.database.couchbase

import com.lucenyo.neighbour.people.domain.Person
import com.lucenyo.neighbour.people.infrastructure.database.couchbase.documents.PersonDocument

fun PersonDocument.toDomain() = Person(
  id = id,
  firstName = firstName,
  lastName = lastName,
  userId = userId,
)

fun Person.toDocument() = PersonDocument(
  id = id,
  firstName = firstName,
  lastName = lastName,
  userId = userId,
)