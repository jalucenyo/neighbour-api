package com.lucenyo.neighbour.community.infrastructure.database.couchbase.mapper

import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.domain.PersonRole
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents.CommunityDocument
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents.PersonRoleDocument
import java.util.UUID

fun Community.toDocument() = CommunityDocument(
  id = id,
  name = name,
  neighbors = neighbors.map { it.toDocument() }.toSet(),
  deleted = deleted
)

fun CommunityDocument.toDomain() = Community(
  id = id,
  name = name,
  neighbors = neighbors.map { it.toDomain() }.toSet(),
  deleted = deleted
)

fun PersonRole.toDocument() = PersonRoleDocument(
  personId = personId,
  role = role
)

fun PersonRoleDocument.toDomain() = PersonRole(
  personId = personId,
  role = role
)
